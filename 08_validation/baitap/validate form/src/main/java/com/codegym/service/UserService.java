package com.codegym.service;

import com.codegym.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    Page<User> findByfirtNameOrlastNameContaining(Pageable pageable,String firstname,String lastName);
    Page<User> findAll(Pageable pageable);

    User findById(int id);

    void save (User user);

    void update (User user);

    void remove (int id);
}
