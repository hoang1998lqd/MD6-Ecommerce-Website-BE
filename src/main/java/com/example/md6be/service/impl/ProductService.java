package com.example.md6be.service.impl;

import com.example.md6be.model.DTO.DTOProduct;
import com.example.md6be.model.DTO.DTOProductSold;
import com.example.md6be.model.Product;
import com.example.md6be.repository.ProductRepository;
import com.example.md6be.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class ProductService implements IProductService {
    @Autowired
    private ProductRepository productRepository;

   @Autowired
    private DTOProductService dtoProductService;


    @Override
    public List<Product> findAll() {
        return productRepository.findAllProduct();
    }


    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void delete(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
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
    public List<Product> findAllByNameContaining(Long idCustomer,String name) {
        return productRepository.findAllByNameContaining(idCustomer,"%"+name+"%");
    }

    @Override
    public List<DTOProduct> findDTOAllByNameContaining(Long idCustomer,String name) {
        return dtoProductService.searchNameDtoProducts(idCustomer,name);
    }

    @Override
    public List<Product> findAllByCategoryId(Long idCustomer, long id) {
        return productRepository.findAllByCategoryId(idCustomer, id);
    }

    public List<DTOProduct> findDTOAllByCategoryId(Long idCustomer, long id) {
        return dtoProductService.searchDtoProductsByCategory(idCustomer, id);
    }

    @Override
    public List<Product> findProductByPriceBetween(Long idCustomer,Double priceMin, Double priceMax) {
        return productRepository.findProductByPriceBetween(idCustomer,priceMin, priceMax);
    }

    @Override
    public List<DTOProduct> findDTOProductByPriceBetween(Long idCustomer, Double priceMin, Double priceMax) {
        return dtoProductService.searchDtoProductsByPrice(idCustomer, priceMin, priceMax);
    }

    public List<Product> findProductByCustomerId(Long id) {
        return productRepository.findProductByCustomerId(id);
    }

    @Override
    public List<DTOProduct> findAllProductByCustomerId(Long id) {
        List<DTOProduct> dtoProducts = dtoProductService.findAllProductByCustomerId(id);
        dtoProducts.sort(new Comparator<DTOProduct>() {
            @Override
            public int compare(DTOProduct dtoProduct, DTOProduct t1) {
                return (int) (t1.getProduct().getId() - dtoProduct.getProduct().getId());
            }
        });
        return dtoProducts ;
    }

    @Override
    public List<Product> findAllProduct(Long idCustomer) {
        return productRepository.findAllProduct(idCustomer);
    }

    @Override
    public List<DTOProduct> findAllProductNotCustomerId(Long idCustomer) {
        List<DTOProduct>dtoProducts = dtoProductService.findAllProduct(idCustomer);
        dtoProducts.sort(new Comparator<DTOProduct>() {
            @Override
            public int compare(DTOProduct dtoProduct, DTOProduct t1) {
                return (int) (t1.getProduct().getId() - dtoProduct.getProduct().getId());
            }
        });
        return dtoProducts;
    }

    @Override
    public List<Product> findAllProductByCategoryIdAndBrandId(Long idCustomer, Long idCategory, Long idBrand) {
        return productRepository.findAllProductByCategoryIdAndBrandId(idCustomer,idCategory,idBrand);
    }

    @Override
    public List<DTOProduct> findAllDTOProductByCategoryIdAndBrandId(Long idCustomer, Long idCategory, Long idBrand) {
        return dtoProductService.findAllDTOProductByCategoryIdAndBrandId(idCustomer,idCategory,idBrand);
    }

    @Override
    public List<Product> findAllProductByOrderId(Long idOrder) {
        return productRepository.findAllProductByOrderId(idOrder);
    }

    @Override
    public List<DTOProduct> findAllDTOProductByOrderId(Long idOrder) {
        return dtoProductService.findAllDTOProductByOrderId(idOrder);
    }

    @Override
    public DTOProductSold findSoldByProductId(Long idProduct) {
        return new DTOProductSold(idProduct,productRepository.findSoldByProductId(idProduct));
    }

    @Override
    public List<DTOProductSold> findAllSoldByProductId() {
        List<DTOProductSold> dtoProductSoldList = new ArrayList<>();
        List<Long> listProductId = productRepository.findProductIdInOrderDetail();
        for (Long aLong : listProductId) {
            DTOProductSold dtoProductSold = findSoldByProductId(aLong);
            dtoProductSoldList.add(dtoProductSold);
        }
        return dtoProductSoldList;
    }

    @Override
    public List<Long> findProductIdSoldForCustomer(Long idCustomer) {
        return productRepository.findProductIdSoldForCustomer(idCustomer);
    }

}
