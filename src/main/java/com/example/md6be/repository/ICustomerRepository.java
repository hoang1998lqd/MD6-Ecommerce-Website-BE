package com.example.md6be.repository;

import com.example.md6be.model.Customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ICustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByName(String name);

    Optional<Customer> findByEmailAddressAndPassword(String email, String password);

    Optional<Customer> findByEmailAddress(String email);

    @Query(value = "select max(id) from customer", nativeQuery = true)
    Long findNewCustomerId();

    @Query(value = "select role_id from customer_role where customer_id = ?1",
            nativeQuery = true)
    List<Integer> findRoleByCustomerId(Long idCustomer);

    // Tìm kiếm những người dùng có quyền là bán hàng
    @Query(value = "select * from customer where id in (select id from customer left join customer_role on customer.id = customer_id where role_id = 2)",
            nativeQuery = true)
    List<Customer> findCustomerHaveShop();


   Optional<Customer> findById(Long id);

   // Tìm kiếm tất cả Người dùng có status = 1;
    @Query(value = "select * from customer where not status = 0;",
            nativeQuery = true)
    List<Customer> findAllCustomerByStatus();

}
