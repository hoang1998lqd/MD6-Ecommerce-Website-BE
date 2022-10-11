package com.example.md6be.controller;

import com.example.md6be.model.Category;
import com.example.md6be.model.DTO.DTOCategoryBrand;
import com.example.md6be.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/api/categories")
public class CategoryController {
    @Autowired
    private ICategoryService iCategoryService;

    @GetMapping
    private ResponseEntity<List<Category>> findAll() {
        return new ResponseEntity<>(iCategoryService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    private ResponseEntity<Category> getCategory(@PathVariable Long id) {
        Optional<Category> category = iCategoryService.findById(id);
        if (category.isPresent()) {
            return new ResponseEntity<>(category.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/brands")
    private ResponseEntity<List<DTOCategoryBrand>> findBrandByCategoryId() {
        return new ResponseEntity<>(iCategoryService.findBrandByCategoryId(), HttpStatus.OK);
    }
}
