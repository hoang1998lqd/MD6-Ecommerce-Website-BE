package com.example.md6be.service;

import com.example.md6be.model.DTO.DTOProduct;
import com.example.md6be.model.DTO.DTOProductSold;
import com.example.md6be.model.Product;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface IProductService extends IGeneralService<Product> {
    Long findIdNewProduct();
    List<DTOProduct> getAllDTO();
    List<Product> findAllByNameContaining(Long idCustomer,String name);
    List<DTOProduct> findDTOAllByNameContaining(Long idCustomer, String name);
    List<Product> findAllByCategoryId(Long idCustomer, long id);
    List<DTOProduct> findDTOAllByCategoryId(Long idCustomer, long id);
    List<Product> findProductByPriceBetween (Long idCustomer, Double priceMin, Double priceMax);
    List<DTOProduct> findDTOProductByPriceBetween (Long idCustomer, Double priceMin, Double priceMax);
    List<Product> findProductByCustomerId(Long id);
    List<DTOProduct> findAllProductByCustomerId(Long id);
    List<Product> findAllProduct(Long idCustomer);
    List<DTOProduct> findAllProductNotCustomerId(Long idCustomer);
    List<Product> findAllProductByCategoryIdAndBrandId(Long idCustomer, Long idCategory, Long idBrand);
    List<DTOProduct> findAllDTOProductByCategoryIdAndBrandId(Long idCustomer, Long idCategory, Long idBrand);
    List<Product> findAllProductByOrderId(Long idOrder);
    List<DTOProduct> findAllDTOProductByOrderId(Long idOrder);
    DTOProductSold findSoldByProductId(Long idProduct);
    List<DTOProductSold> findAllSoldByProductId();
    List<Long> findProductIdSoldForCustomer(Long idCustomer);



}
