package com.example.md6be.service;

import com.example.md6be.model.DTO.DTOProduct;
import com.example.md6be.model.Product;


import java.util.List;

public interface IProductService extends IGeneralService<Product> {
    Long findIdNewProduct();
    List<DTOProduct> getAllDTO();
    List<Product> findProductByCustomerId(Long id);
    List<DTOProduct> findAllProductByCustomerId(Long id);

}
