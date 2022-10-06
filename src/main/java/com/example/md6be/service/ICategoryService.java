package com.example.md6be.service;

import com.example.md6be.model.Category;
<<<<<<< HEAD
import com.example.md6be.model.Product;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
=======
import com.example.md6be.model.DTO.DTOCategoryBrand;
>>>>>>> b45a89f4c7ffbca8fcb9374db4a19643c2bdae71

import java.util.List;


public interface ICategoryService extends IGeneralService<Category>{
<<<<<<< HEAD

=======
    List<DTOCategoryBrand> findBrandByCategoryId();
>>>>>>> b45a89f4c7ffbca8fcb9374db4a19643c2bdae71
}
