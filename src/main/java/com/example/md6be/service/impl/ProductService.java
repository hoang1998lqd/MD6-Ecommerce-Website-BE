package com.example.md6be.service.impl;

import com.example.md6be.model.DTO.DTOProduct;
import com.example.md6be.model.Product;
import com.example.md6be.repository.ProductRepository;
import com.example.md6be.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private DTOProductService dtoProductService;


    @Override
    public List<Product> findAll() {
        List<Product> products = productRepository.findAll();
        List<Product> productList = new ArrayList<>();
        for (Product product : products){
            if (product.getStatus() != 0 ){
                productList.add(product);
            }
        }
        return productList;
    }


    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product save(Product product) {;
        return productRepository.save(product);
    }

    @Override
    public void delete(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()){
            Product product = productOptional.get();
            product.setStatus(0);
            productRepository.save(product);
        }
    }

    @Override
    public Long findIdNewProduct() {
        return productRepository.findIdNewProduct();
    }

    @Override
    public List<DTOProduct> getAllDTO() {
        return dtoProductService.createDtoProducts();
    }

    @Override
    public List<Product> findAllByNameContaining(String name) {
        return productRepository.findAllByNameContaining(name);
    }

    public List<DTOProduct> findDTOAllByNameContaining(String name) {
        return dtoProductService.searchNameDtoProducts(name);
    }

    @Override
    public List<Product> findAllByCategoryId(long id) {
        return productRepository.findAllByCategoryId(id);
    }

    public List<DTOProduct> findDTOAllByCategoryId(long id) {
        return dtoProductService.searchDtoProductsByCategory(id);
    }

    @Override
    public List<Product> findProductByPriceBetween(Double priceMin, Double priceMax) {
        return productRepository.findProductByPriceBetween(priceMin,priceMax);
    }

    @Override
    public List<DTOProduct> findDTOProductByPriceBetween(Double priceMin, Double priceMax) {
        return dtoProductService.searchDtoProductsByPrice(priceMin,priceMax);
    }


}
