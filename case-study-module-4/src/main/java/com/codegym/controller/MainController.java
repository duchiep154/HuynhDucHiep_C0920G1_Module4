package com.codegym.controller;

import com.codegym.entity.service.ServiceType;
import com.codegym.entity.user.User;
import com.codegym.service.service.ServiceTypeDao;
import com.codegym.service.user.UserService;
import com.codegym.validatior.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@SessionAttributes({"serviceTypeList"})
public class MainController {


    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ServiceTypeDao serviceTypeDao;


    @ModelAttribute("serviceTypeList")
    public List<ServiceType> returnListServiceType() {
        return serviceTypeDao.findAll();
    }


    @GetMapping(value = {"/","/home"})
    public String goHome() {
        return "view/home";
    }

    @GetMapping("/sign-in")
    public String goSignIn() {
        return "view/user/sign-in";
    }

    @GetMapping("/sign-up")
    public String goSignUp(Model model) {
        model.addAttribute("user", new User());
        return "view/user/sign-up";
    }

    @PostMapping("/sign-up")
    public String signUp(@Valid @ModelAttribute User user,
                         BindingResult bindingResult,
                         HttpServletRequest request) {

        userValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()) {
            return "view/user/sign-up";
        }

        userService.registerNewUser(user);
        //
//        authenticateUserAndSetSession(userService.findByUsername(user.getUsername()), request);

        return "redirect:/home";
    }


    //
//    private void authenticateUserAndSetSession(User user, HttpServletRequest request) {
//        String username = user.getUsername();
//        String password = user.getPassword();
//        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
//
//        // generate session if one doesn't exist
//        request.getSession();
//
//        token.setDetails(new WebAuthenticationDetails(request));
//        Authentication authenticatedUser = authenticationManager.authenticate(token);
//
//        SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
//    }




    @GetMapping("/403")
    public String goError() {
        return "view/403";
    }


}
