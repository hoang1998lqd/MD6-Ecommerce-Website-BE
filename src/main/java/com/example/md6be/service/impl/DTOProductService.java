package com.example.md6be.service.impl;

import com.example.md6be.model.DTO.DTOProduct;
import com.example.md6be.model.ImageURL;
import com.example.md6be.model.Product;
import com.example.md6be.service.IProductService;
import com.example.md6be.service.ImageURLGet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Service
public class DTOProductService {
    @Autowired
    private ImageURLGet imageURLGet;
    @Autowired
    private IProductService iProductService;

    private List<ImageURL>getImageURLS (){
        return imageURLGet.findAll();
    }
    private List<Product> getAllProduct(){
        return iProductService.findAll();
    }
    private List<Product> getAllProductByCustomerId(Long id){
        return iProductService.findProductByCustomerId(id);
    }

    public List<DTOProduct> createDtoProducts (){
        List<DTOProduct> dtoProducts = new ArrayList<>();
        ArrayList<Product> products = (ArrayList<Product>) getAllProduct();
        return getImageURLByCustomerId(dtoProducts, products);
    }

    public List<DTOProduct> findAllProductByCustomerId(Long id){
        List<DTOProduct> dtoProducts = new ArrayList<>();
        ArrayList<Product> products = (ArrayList<Product>) getAllProductByCustomerId(id);
        return getImageURLByCustomerId(dtoProducts, products);
    }

    private List<DTOProduct> getImageURLByCustomerId(List<DTOProduct> dtoProducts, ArrayList<Product> products) {
        ArrayList<ImageURL> imageURLS = (ArrayList<ImageURL>) getImageURLS();
        for (Product product : products){
            DTOProduct dtoProduct= null;
            ArrayList<String> imageURLSProduct = new ArrayList<>();
            for (ImageURL imageURL : imageURLS){
                if (Objects.equals(imageURL.getProduct().getId(), product.getId())){
                    imageURLSProduct.add(imageURL.getName());
                }
                dtoProduct = new DTOProduct(product,imageURLSProduct);
            }
            dtoProducts.add(dtoProduct);
        }
        return dtoProducts;
    }

}
