package com.codegym.config;

import com.codegym.service.implement.user.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@Configuration
@EnableWebSecurity
public class WebConfigSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailServiceImpl userDetailService;


    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.authorizeRequests().antMatchers("/home","/sign-in","/sign-up","/logout","/service/list").permitAll();

        http.authorizeRequests().antMatchers("/customer/detail","/service/booking/*","/service/booking", "/employee/detail","/customer/save","/employee/save","/service/history").access("hasAnyRole('ROLE_GUEST','ROLE_EMPLOYEE','ROLE_MANAGE','ROLE_ADMIN','ROLE_POSTER')");

        http.authorizeRequests().antMatchers("/service/save", "/service/register", "service/history", "/service/my-service").access("hasAnyRole('ROLE_EMPLOYEE','ROLE_MANAGE','ROLE_ADMIN','ROLE_POSTER')");

        http.authorizeRequests().antMatchers("/manage/service/delete/*").access("hasAnyRole('ROLE_POSTER','ROLE_ADMIN','ROLE_MANAGE')");

        http.authorizeRequests().antMatchers("/manage/service/list","/manage/customer/list","/manage/contract/list","/manage/contract/payment/*","/manage/attach-service/list","/manage/user/list","/manage/user/update-role","/manage/contract/update","/manage/customer/update").access("hasAnyRole('ROLE_EMPLOYEE','ROLE_MANAGE','ROLE_ADMIN')");

        http.authorizeRequests().antMatchers("/manage/user/*","/manage/employee/list","/manage/employee/update","/manage/employee/delete/*").access("hasAnyRole('ROLE_MANAGE','ROLE_ADMIN')");

        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");

        http.authorizeRequests().and().formLogin()
                .loginProcessingUrl("/j_spring_security_check")
                .loginPage("/sign-in")
                .defaultSuccessUrl("/home")
                .failureUrl("/sign-in?error=true")
                .usernameParameter("username")
                .passwordParameter("password")

                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/home");

        http.authorizeRequests().and()
                .rememberMe().tokenRepository(this.persistentTokenRepository())
                .tokenValiditySeconds(24 * 60 * 60);

    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        return new InMemoryTokenRepositoryImpl();
    }
}
