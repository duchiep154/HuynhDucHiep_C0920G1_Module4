package com.codegym.service.implement.user;

import com.codegym.entity.user.User;
import com.codegym.entity.user.UserRole;
import com.codegym.repository.user.UserRepository;
import com.codegym.repository.user.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRoleRepository userRoleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("Not found");
        }

        List<UserRole> userRoleList = userRoleRepository.findAllByUser(user);

        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        if (userRoleList != null) {
            for (UserRole userRole: userRoleList) {
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(userRole.getRole().getName());
                grantedAuthorityList.add(grantedAuthority);
            }
        }

        UserDetails userDetails =
                new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorityList);

        return userDetails;
    }
}
