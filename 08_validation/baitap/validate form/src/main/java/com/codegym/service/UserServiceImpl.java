package com.codegym.service;

import com.codegym.entity.User;
import com.codegym.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;


    @Override
    public Page<User> findByfirtNameOrlastNameContaining(Pageable pageable, String firstname, String lastName) {
        return this.userRepository.findAllByFirstNameContainingOrLastNameContaining(pageable,firstname,lastName);
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return this.userRepository.findAll(pageable);
    }

    @Override
    public User findById(int id) {
        return this.userRepository.findById(id).orElse(null);
    }

    @Override
    public void save(User user) {
        this.userRepository.save(user);
    }

    @Override
    public void update(User user) {
        this.userRepository.save(user);
    }

    @Override
    public void remove(int id) {
        this.userRepository.deleteById(id);
    }
}
