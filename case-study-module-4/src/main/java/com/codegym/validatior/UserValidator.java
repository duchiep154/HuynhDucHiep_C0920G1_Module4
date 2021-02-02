package com.codegym.validatior;

import com.codegym.entity.user.User;
import com.codegym.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

@Component
public class UserValidator implements Validator {

    @Autowired
    private UserService userService;


    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        if (userService.findByUsername(user.getUsername()) != null) {
            errors.rejectValue("username","username.duplicate");
        }

        if (!Pattern.compile("^\\w{6,32}$").matcher(user.getUsername()).find()) {
            errors.rejectValue("username","username.size.format");
        }


        if (!Pattern.compile("^\\w{6,32}$").matcher(user.getPassword()).find()) {
            errors.rejectValue("password","password.size.format");
        }


    }
}
