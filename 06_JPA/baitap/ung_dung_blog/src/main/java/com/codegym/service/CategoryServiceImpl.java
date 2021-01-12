package com.codegym.service;

import com.codegym.entity.Category;
import com.codegym.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public Category findById(Long catId) {
        return categoryRepository.findById(catId).orElse(null);
    }

    @Override
    public void create(Category categoryDetails) {
        this.categoryRepository.save(categoryDetails);

    }



    @Override
    public void update( Category categoryDetails) {
        this.categoryRepository.save(categoryDetails);

    }

    @Override
    public void delete(Long catId) {
        this.categoryRepository.deleteById(catId);

    }

    @Override
    public List<Category> getAll() {
        return this.categoryRepository.findAll();
    }
}
