package com.example.md6be.service;

import com.example.md6be.model.DTO.DTOProduct;
import com.example.md6be.model.Product;


import java.util.List;

public interface IProductService extends IGeneralService<Product> {
    Long findIdNewProduct();
    List<DTOProduct> getAllDTO();


    List<Product> findAllByNameContaining(String name);
    List<DTOProduct> findDTOAllByNameContaining(String name);
    List<Product> findAllByCategoryId(long id);
    List<DTOProduct> findDTOAllByCategoryId(long id);
    List<Product> findProductByPriceBetween ( Double priceMin, Double priceMax);
    List<DTOProduct> findDTOProductByPriceBetween ( Double priceMin, Double priceMax);
}
