package com.codegym.rest_controller;


import com.codegym.entity.user.User;
import com.codegym.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api-user")
public class UserRestController {

    @Autowired
    UserService userService;

    @PostMapping("/change-password/{idUser}/{newPassword}")
    public ResponseEntity<User> changePassword (@PathVariable Long idUser,
                                          @PathVariable String newPassword,
                                          Principal principal) {
        User user = userService.findById(idUser);

        user.setPassword(new BCryptPasswordEncoder().encode(newPassword));

        userService.save(user);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
