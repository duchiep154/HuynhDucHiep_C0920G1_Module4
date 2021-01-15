package com.codegym.repository;

import com.codegym.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    Page<User> findAllByFirstNameContainingOrLastNameContaining(Pageable pageable, String firstName,String lastName);
}
