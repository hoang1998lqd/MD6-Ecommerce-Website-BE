package com.example.md6be.model.DTO;

import com.example.md6be.model.Product;


import java.util.Comparator;
import java.util.List;

public class DTOProduct implements Comparator<DTOProduct> {
    private Product product;
    private List<String> imageURLS;

    public DTOProduct() {
    }

    public DTOProduct(Product product, List<String> imageURLS) {
        this.product = product;
        this.imageURLS = imageURLS;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<String> getImageURLS() {
        return imageURLS;
    }

    public void setImageURLS(List<String> imageURLS) {
        this.imageURLS = imageURLS;
    }


    @Override
    public int compare(DTOProduct dtoProduct, DTOProduct t1) {
        return (int) (t1.getProduct().getId() - dtoProduct.getProduct().getId());
    }
}
