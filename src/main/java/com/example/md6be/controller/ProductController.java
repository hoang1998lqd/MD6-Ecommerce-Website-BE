package com.example.md6be.controller;

import com.example.md6be.model.DTO.DTOProduct;
import com.example.md6be.model.DTO.DTOProductSold;
import com.example.md6be.model.ImageURL;
import com.example.md6be.model.Product;
import com.example.md6be.service.IProductService;
import com.example.md6be.service.ImageURLGet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/api/products")
public class ProductController {
    @Autowired
    private IProductService iProduct;

    @Autowired
    private ImageURLGet imageURLGet;

    @GetMapping
    private ResponseEntity<List<DTOProduct>> findAll() {
        return new ResponseEntity<>(iProduct.getAllDTO(), HttpStatus.OK);
    }

    @GetMapping("/new-product")
    private ResponseEntity<?> findIdNewProduct() {
        return new ResponseEntity<>(iProduct.findIdNewProduct(), HttpStatus.OK);
    }

    @GetMapping("/image")
    private ResponseEntity<?> findAllImage() {
        return new ResponseEntity<>(imageURLGet.findAll(), HttpStatus.OK);
    }

    @GetMapping("/image-product/{id}")
    private ResponseEntity<?> findImageURLByProductId(@PathVariable Long id) {
        return new ResponseEntity<>(imageURLGet.findByProduct(id), HttpStatus.OK);
    }


    @PostMapping("/imageURL")
    private ResponseEntity<ImageURL> saveImage(@RequestBody ImageURL imageURL) {
        return new ResponseEntity<>(imageURLGet.save(imageURL), HttpStatus.CREATED);
    }

    @PostMapping
    private ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return new ResponseEntity<>(iProduct.save(product), HttpStatus.CREATED);
    }


    @DeleteMapping("/{id}")
    private ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        iProduct.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Update IMG cho Product
    @PutMapping("/update-img")
    private ResponseEntity<ImageURL> updateImg(@RequestBody ImageURL imageURL) {
        Optional<ImageURL> optionalImageURL = imageURLGet.findById(imageURL.getId());
        if (optionalImageURL.isPresent()) {
            return new ResponseEntity<>(imageURLGet.save(imageURL), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/product-update/{id}")
    private ResponseEntity<?> findIdByProductId(@PathVariable Long id) {
        return new ResponseEntity<>(imageURLGet.findIdByProductId(id), HttpStatus.OK);
    }

    @PutMapping
    private ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        Optional<Product> optionalProduct = iProduct.findById(product.getId());
        if (optionalProduct.isPresent()) {
            return new ResponseEntity<>(iProduct.save(product), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    private ResponseEntity<Product> getProduct(@PathVariable Long id) {
        Optional<Product> product = iProduct.findById(id);
        if (product.isPresent()) {
            return new ResponseEntity<>(product.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/find-name-products/{name}&{idCustomer}")
    private ResponseEntity<List<DTOProduct>> findAllByNameContaining(@PathVariable("name") String name, @PathVariable Long idCustomer) {
        return new ResponseEntity<>(iProduct.findDTOAllByNameContaining(idCustomer, name), HttpStatus.OK);
    }

    @GetMapping("/find-by-id/{id}&{idCustomer}")
    private ResponseEntity<List<DTOProduct>> findProductByCategoryId(@PathVariable("id") long id,
                                                                     @PathVariable Long idCustomer) {
        return new ResponseEntity<>(iProduct.findDTOAllByCategoryId(idCustomer, id), HttpStatus.OK);
    }

    @GetMapping("/find-by-price/{priceMin}&{priceMax}&{idCustomer}")
    private ResponseEntity<List<DTOProduct>> findProductByPrice(@PathVariable("priceMin") Optional<Double> priceMin,
                                                                @PathVariable("priceMax") Optional<Double> priceMax,
                                                                @PathVariable("idCustomer") Long idCustomer) {
        Double min = priceMin.orElse(Double.MIN_VALUE);
        Double max = priceMax.orElse(Double.MAX_VALUE);
        List<DTOProduct> betweenPrice = iProduct.findDTOProductByPriceBetween(idCustomer, min, max);
        return new ResponseEntity<>(betweenPrice, HttpStatus.OK);
    }

    @GetMapping("/customer/{id}")
    private ResponseEntity<List<DTOProduct>> getProductByCustomerId(@PathVariable Long id) {
        return new ResponseEntity<>(iProduct.findAllProductByCustomerId(id), HttpStatus.OK);
    }

    @GetMapping("/not-customer/{idCustomer}")
    private ResponseEntity<List<DTOProduct>> findAllProductNotByCustomerId(@PathVariable Long idCustomer) {
        return new ResponseEntity<>(iProduct.findAllProductNotCustomerId(idCustomer), HttpStatus.OK);
    }

    //Tìm kiếm sản phẩm theo idCategory và idBrand
    @GetMapping("/brand/{idCustomer}&{idCategory}&{idBrand}")
    private ResponseEntity<List<DTOProduct>> findAllProductByCategoryIdAndBrandId(@PathVariable Long idCustomer,
                                                                                  @PathVariable Long idCategory,
                                                                                  @PathVariable Long idBrand) {
        return new ResponseEntity<>(iProduct.findAllDTOProductByCategoryIdAndBrandId(idCustomer,idCategory,idBrand), HttpStatus.OK);
    }

    // Tìm kiếm sản phẩm theo ID của đơn hàng
    @GetMapping("/orders/{idOrder}")
    private ResponseEntity<List<DTOProduct>>findAllDTOProductByOrderId(@PathVariable Long idOrder){
        return new ResponseEntity<>(iProduct.findAllDTOProductByOrderId(idOrder),HttpStatus.OK);
    }

    @GetMapping("/detail-product/{idCustomer}&{idProduct}")
    private ResponseEntity<?> detailProduct(@PathVariable("idCustomer") Long idCustomer, @PathVariable("idProduct") Long id){
        List<DTOProduct> dtoProductList = iProduct.findAllProductNotCustomerId(idCustomer);
        DTOProduct product;
        for (DTOProduct dtoProduct: dtoProductList){
            if (dtoProduct.getProduct().getId() == id){
                product = dtoProduct;
                return new ResponseEntity<>(product, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Tìm kiếm số lượng đã bán ra của cửa hàng
    @GetMapping("/product-sold")
    private ResponseEntity<List<DTOProductSold>>findAllSoldByProductId(){
        return new ResponseEntity<>(iProduct.findAllSoldByProductId(),HttpStatus.OK);
    }
    @GetMapping("/product-sold-customer/{idCustomer}")
    private ResponseEntity<List<Long>>findProductIdSoldForCustomer(@PathVariable Long idCustomer){
        return new ResponseEntity<>(iProduct.findProductIdSoldForCustomer(idCustomer),HttpStatus.OK);
    }
}
