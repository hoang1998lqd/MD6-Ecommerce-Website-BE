package com.example.md6be.service;

import com.example.md6be.model.Category;

import com.example.md6be.model.DTO.DTOCategoryBrand;


import java.util.List;


public interface ICategoryService extends IGeneralService<Category>{

    List<DTOCategoryBrand> findBrandByCategoryId();

}
