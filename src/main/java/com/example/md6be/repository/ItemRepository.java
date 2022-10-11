package com.example.md6be.repository;

import com.example.md6be.model.Item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query(value = "select * from item where cart_id = (select cart.customer_id from cart where customer_id = ?1)", nativeQuery = true)
    List<Item> findItemByCustomerId(@Param("id") Long id);

    // Tìm ra List ID của cửa hàng có tồn tại trong List Item trong giỏ hàng của người đang đăng nhập
    @Query(value = "select customer_id from product where id in (select product_id from item where cart_id = ?1) group by customer_id", nativeQuery = true)
    List<Long> findListIdShop(Long idShop);

    // Tìm ra List Item của người đang đăng nhập được chia ra dựa vào Id của Cửa hàng
    @Query(value = "select * from item where product_id in (select id from product where customer_id = ?1) and cart_id = ?2", nativeQuery = true)
    List<Item> findAllItemByIdShop(Long idShop, Long idCustomer);

    //Tim List Item theo ID của người đang đăng nhập
    @Query(value = "select * from item where cart_id = ?1", nativeQuery = true)
    List<Item> findAllItemByCartId(Long idCustomer);

    // Tìm ra Id của cửa hàng mà Item có tồn tại trong đó
    @Query(value = "select customer_id from product where id = ?1", nativeQuery = true)
    Long findShopIdByIdProduct(Long idProduct);


}
