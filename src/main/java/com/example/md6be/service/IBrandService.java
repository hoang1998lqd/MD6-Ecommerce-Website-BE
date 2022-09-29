package com.example.md6be.service;

import com.example.md6be.model.Brand;


import java.util.List;

public interface IBrandService extends IGeneralService<Brand> {
    List<Brand> findBrandsByCategoryId(Long id);
}
