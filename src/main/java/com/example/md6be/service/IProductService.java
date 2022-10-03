package com.example.md6be.service;

import com.example.md6be.model.DTO.DTOProduct;
import com.example.md6be.model.Product;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;
import java.util.Optional;

public interface IProductService extends IGeneralService<Product> {
    Long findIdNewProduct();
    List<DTOProduct> getAllDTO();
    Optional<Product> findAllByNameContaining(@RequestParam String name);
    List<Product> findAllByCategoryId(long id);
    List<Product> findProductByPriceBetween ( Double priceMin, Double priceMax);
}
