package com.example.md6be.controller;

import com.example.md6be.model.Brand;
import com.example.md6be.service.IBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/api/brands")
public class BrandController {
    @Autowired
    private IBrandService iBrandService;
    @GetMapping
    private ResponseEntity<List<Brand>> findAll() {
        return new ResponseEntity<>(iBrandService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    private ResponseEntity<List<?>> findBrandByCategory(@PathVariable Long id) {
        List<Brand> brands = iBrandService.findBrandsByCategoryId(id);
        return new ResponseEntity<>(brands, HttpStatus.OK);
    }
}
