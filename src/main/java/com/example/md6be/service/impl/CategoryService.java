package com.example.md6be.service.impl;

import com.example.md6be.model.Category;


import com.example.md6be.model.DTO.DTOCategoryBrand;

import com.example.md6be.repository.CategoryRepository;
import com.example.md6be.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private DTOCategoryAndBrandService dtoCategoryAndBrandService;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Category save(Category Category) {
        return categoryRepository.save(Category);
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public List<DTOCategoryBrand> findBrandByCategoryId() {
        return dtoCategoryAndBrandService.findBrandByCategoryId();
    }

}

