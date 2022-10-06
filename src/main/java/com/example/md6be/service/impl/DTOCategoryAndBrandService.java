package com.example.md6be.service.impl;

import com.example.md6be.model.Brand;
import com.example.md6be.model.Category;
import com.example.md6be.model.DTO.DTOCategoryBrand;
import com.example.md6be.service.IBrandService;
import com.example.md6be.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DTOCategoryAndBrandService {
    @Autowired
    IBrandService iBrandService;
    @Autowired
    ICategoryService iCategoryService;

    private List<Category> findAllCategory(){
        return iCategoryService.findAll();
    }
    private List<Brand> findBrandByCategoryId(Long idCategory){
        return iBrandService.findBrandsByCategoryId(idCategory);
    }

    public List<DTOCategoryBrand> findBrandByCategoryId(){
        List<DTOCategoryBrand> dtoCategoryBrands = new ArrayList<>();
        List<Brand> brands ;
        ArrayList<Category> categories = (ArrayList<Category>) findAllCategory();
        for (Category category: categories){
            DTOCategoryBrand dtoCategoryBrand ;
            brands = findBrandByCategoryId(category.getId());
            dtoCategoryBrand = new DTOCategoryBrand(category, brands);
            dtoCategoryBrands.add(dtoCategoryBrand);
        }
        return dtoCategoryBrands;
    }

}
