package com.example.md6be.service;

import com.example.md6be.model.Customer;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
;

import java.util.List;
import java.util.Optional;

public interface ICustomerService extends IGeneralService<Customer>, UserDetailsService {
    UserDetails loadUserByUsername(String name) throws UsernameNotFoundException;

    Optional<Customer> findByEmailAddressAndPassword(String email, String password);

    Optional<Customer> findByName (String name);
    Optional<Customer> findCustomerById (Long idCustomer);

    Optional<Customer> findByEmailAddress (String email);
    Long findNewCustomerId();
    List <Integer> findRoleByCustomerId(Long id);
    List<Customer> findCustomerHaveShop();

    Optional<Customer> findById(Long id);
    List<Customer> findAllCustomerByStatus();

}
