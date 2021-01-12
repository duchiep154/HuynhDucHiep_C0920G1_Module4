package com.codegym.service;

import com.codegym.entity.Category;

import java.util.List;

public interface CategoryService {

    Category findById(Long catId);
    void create(Category categoryDetails);
    void update(Category categoryDetails);
    void delete(Long catId);

    List<Category> getAll();
}
