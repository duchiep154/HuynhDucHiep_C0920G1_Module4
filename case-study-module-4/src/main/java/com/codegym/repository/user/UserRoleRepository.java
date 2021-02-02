package com.codegym.repository.user;

import com.codegym.entity.user.User;
import com.codegym.entity.user.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    List<UserRole> findAllByUser(User user);
}
