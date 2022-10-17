package com.example.md6be.repository;

import com.example.md6be.model.DTO.DTOProduct;
import com.example.md6be.model.DTO.DTOProductSold;
import com.example.md6be.model.Product;

import org.springframework.context.annotation.aspectj.EnableSpringConfigured;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

import java.util.List;

@Repository
@EnableSpringConfigured
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "select max(id) from product",
            nativeQuery = true)
    Long findIdNewProduct();

    @Query(value = "select * from product where not customer_id = ?1 and status = 1 and name like ?2",
            nativeQuery = true)
    List<Product> findAllByNameContaining(Long idCustomer, String name);

    @Query(value = "select * from product where not customer_id = ?1 and status = 1 and price between ?2 and ?3",
            nativeQuery = true)
    List<Product> findProductByPriceBetween(Long idCustomer, Double priceMin, Double priceMax);

    @Query(value = "select * from product where not customer_id = ?1 and status = 1 and category_id = ?2",
            nativeQuery = true)
    List<Product> findAllByCategoryId(Long idCustomer, long id);

    //Tìm kiếm sản phẩm trong gian hàng của người bán hàng
    @Query(value = "select * from product where customer_id = ?1 and status = 1",
            nativeQuery = true)
    List<Product> findProductByCustomerId(Long id);

    @Query(value = "select * from product where status = 1",
            nativeQuery = true)
    List<Product> findAllProduct();

    //Tìm kiếm sản phẩm mà không phải tồn tại trong cửa hàng của người tạo
    @Query(value = "select * from product where not customer_id = ?1 and status = 1",
            nativeQuery = true)
    List<Product> findAllProduct(Long idCustomer);

    @Query(value = "select * from product where not customer_id = ?1 and category_id = ?2 and brand_id = ?3 and status = 1",
            nativeQuery = true)
    List<Product> findAllProductByCategoryIdAndBrandId(Long idCustomer, Long idCategory, Long idBrand);

    // Tìm kiếm sản phẩm theo ID của đơn đặt hàng
    @Query(value = "select * from product where id in (select product_id from order_detail where orders_id = ?1)",
            nativeQuery = true)
    List<Product> findAllProductByOrderId(Long idOrder);

    // Tìm kiếm số lượng đã được bán ra của từng sản phẩm
    @Query(value = "select count(quantity) as sold from order_detail where product_id = ?1",
            nativeQuery = true)
    Integer findSoldByProductId(Long idProduct);

    @Query(value = "select product_id from order_detail group by product_id",
            nativeQuery = true)
    List<Long> findProductIdInOrderDetail();

    @Query(value = "select product_id from order_detail where orders_id in (select id from orders where customer_id = ?1) group by product_id",
            nativeQuery = true)
    List<Long> findProductIdSoldForCustomer(Long idCustomer);


}
