package com.example.md6be.model.DTO;

import com.example.md6be.model.Brand;
import com.example.md6be.model.Category;

import java.util.List;

public class DTOCategoryBrand {
    private Category category;
    private List<Brand> brands;

    public DTOCategoryBrand() {
    }

    public DTOCategoryBrand(Category category, List<Brand> brands) {
        this.category = category;
        this.brands = brands;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Brand> getBrands() {
        return brands;
    }

    public void setBrands(List<Brand> brands) {
        this.brands = brands;
    }
}
