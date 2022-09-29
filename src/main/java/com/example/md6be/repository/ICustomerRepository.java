package com.example.md6be.repository;

import com.example.md6be.model.Customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ICustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByName(String name);

    Optional<Customer> findByEmailAddressAndPassword(String email, String password);

    Optional<Customer> findByEmailAddress(String email);

    @Query(value = "select max(id) from customer", nativeQuery = true)
    Long findNewCustomerId();

}
