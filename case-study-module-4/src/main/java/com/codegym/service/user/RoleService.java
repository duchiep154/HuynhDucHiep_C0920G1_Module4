package com.codegym.service.user;

import com.codegym.entity.user.Role;

import java.util.List;

public interface RoleService {
    List<Role> findAll();

    Role findById(Long roleId);
}
