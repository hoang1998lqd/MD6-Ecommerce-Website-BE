package com.example.md6be.service.impl;

import com.example.md6be.model.DTO.DTOProduct;
import com.example.md6be.model.ImageURL;
import com.example.md6be.model.Product;
import com.example.md6be.service.IProductService;
import com.example.md6be.service.ImageURLGet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
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

    private List<ImageURL> getImageURLS() {
        return imageURLGet.findAll();
    }

    private List<Product> getAllProduct() {
        return iProductService.findAll();
    }
    private List<Product> getAllProductByCustomerId(Long id){
        return iProductService.findProductByCustomerId(id);
    }
    private List<Product> findAllProductNotByCustomerId(Long id){
        return iProductService.findAllProduct(id);
    }
    private List<Product> findAllProductByCategoryIdAndBrandId(Long idCustomer, Long idCategory, Long idBrand){
        return iProductService.findAllProductByCategoryIdAndBrandId(idCustomer,idCategory,idBrand);
    }

    private List<Product> searchNameProduct(Long idCustomer, String name) {
        return iProductService.findAllByNameContaining(idCustomer, name);
    }

    private List<Product> searchProductByCategory(Long idCustomer, long id) {
        return iProductService.findAllByCategoryId(idCustomer, id);
    }

    private List<Product> searchProductByPrice(Long idCustomer, Double priceMin, Double priceMax) {
        return iProductService.findProductByPriceBetween(idCustomer,priceMin, priceMax);
    }

    // Tìm kiếm sản phẩm theo ID của đơn hàng
    private List<Product> findAllProductByOrderId(Long idOrder){
        return iProductService.findAllProductByOrderId(idOrder);
    }

    //Tất cả Product
    public List<DTOProduct> createDtoProducts() {
        List<DTOProduct> dtoProducts = new ArrayList<>();
        ArrayList<Product> products = (ArrayList<Product>) getAllProduct();
        return getImageURLByCustomerId(dtoProducts, products);
    }

    //Product theo người dùng

    public List<DTOProduct> findAllProductByCustomerId(Long id){
        List<DTOProduct> dtoProducts = new ArrayList<>();
        ArrayList<Product> products = (ArrayList<Product>) getAllProductByCustomerId(id);
        return getImageURLByCustomerId(dtoProducts, products);
    }

    // Tìm tất cả sản phẩm cho người bán hàng
    public List<DTOProduct> findAllProduct(Long idCustomer){
        List<DTOProduct> dtoProducts = new ArrayList<>();
        ArrayList<Product> products = (ArrayList<Product>) findAllProductNotByCustomerId(idCustomer);
        return getImageURLByCustomerId(dtoProducts, products);
    }

    private List<DTOProduct> getImageURLByCustomerId(List<DTOProduct> dtoProducts, ArrayList<Product> products) {
        return getImg(dtoProducts, products);
    }


    private List<DTOProduct> getImg(List<DTOProduct> dtoProducts, ArrayList<Product> products) {
        ArrayList<ImageURL> imageURLS = (ArrayList<ImageURL>) getImageURLS();
        for (Product product : products) {
            DTOProduct dtoProduct = null;
            ArrayList<String> imageURLSProduct = new ArrayList<>();
            for (ImageURL imageURL : imageURLS) {
                if (Objects.equals(imageURL.getProduct().getId(), product.getId())) {
                    imageURLSProduct.add(imageURL.getName());
                }
                dtoProduct = new DTOProduct(product, imageURLSProduct);
            }
            dtoProducts.add(dtoProduct);
        }
        return dtoProducts;
    }

    public List<DTOProduct> searchNameDtoProducts(Long idCustomer, String name) {
        List<DTOProduct> dtoProducts = new ArrayList<>();
        ArrayList<Product> products = (ArrayList<Product>) searchNameProduct(idCustomer, name);
        return getImg(dtoProducts, products);
    }

    public List<DTOProduct>findAllDTOProductByCategoryIdAndBrandId(Long idCustomer, Long idCategory, Long idBrand){
        List<DTOProduct> dtoProducts = new ArrayList<>();
        ArrayList<Product> products = (ArrayList<Product>) findAllProductByCategoryIdAndBrandId(idCustomer,idCategory,idBrand) ;
        return getImg(dtoProducts, products);
    }

    public List<DTOProduct> searchDtoProductsByCategory(Long idCustomer, long id) {
        List<DTOProduct> dtoProducts = new ArrayList<>();
        ArrayList<Product> products = (ArrayList<Product>) searchProductByCategory(idCustomer, id);
        return getImg(dtoProducts, products);
    }

    public List<DTOProduct> searchDtoProductsByPrice(Long idCustomer, Double priceMin, Double priceMax) {
        List<DTOProduct> dtoProducts = new ArrayList<>();
        ArrayList<Product> products = (ArrayList<Product>) searchProductByPrice(idCustomer, priceMin, priceMax);
        return getImg(dtoProducts, products);
    }

    //Tìm kiếm DTOProduct theo ID của đơn hàng
    public List<DTOProduct>findAllDTOProductByOrderId(Long idOrder){
        List<DTOProduct> dtoProducts = new ArrayList<>();
        ArrayList<Product> products = (ArrayList<Product>)findAllProductByOrderId(idOrder);
        return getImg(dtoProducts,products);
    }
}
