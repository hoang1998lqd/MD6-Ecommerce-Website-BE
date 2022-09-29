package com.example.md6be.service.impl;

import com.example.md6be.repository.BrandRepository;
import com.example.md6be.service.IBrandService;
import com.example.md6be.model.Brand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class BrandService implements IBrandService {
    @Autowired
    private BrandRepository brandRepository;
    @Override
    public List<Brand> findAll() {
        return brandRepository.findAll();
    }


    @Override
    public Optional<Brand> findById(Long id) {
        return brandRepository.findById(id);
    }

    @Override
    public Brand save(Brand brand) {
        return brandRepository.save(brand);
    }

    @Override
    public void delete(Long id) {
        brandRepository.deleteById(id);
    }
    @Override
    public List<Brand> findBrandsByCategoryId(Long id) {
        return brandRepository.findBrandsByCategoryId(id);
    }
}
