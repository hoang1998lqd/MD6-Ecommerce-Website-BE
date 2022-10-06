package com.example.md6be.service.impl;

import com.example.md6be.model.Customer;

import com.example.md6be.model.CustomerPrinciple;
import com.example.md6be.repository.ICustomerRepository;
import com.example.md6be.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService implements ICustomerService {
    @Autowired
    private ICustomerRepository customerRepository;

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void delete(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Customer> customerOptional = customerRepository.findByEmailAddress(email);
        if (!customerOptional.isPresent()){
            throw new UsernameNotFoundException(email);
        }
        return CustomerPrinciple.build(customerOptional.get());
    }

    @Override
    public Optional<Customer> findByEmailAddressAndPassword(String email, String password) {
        return customerRepository.findByEmailAddressAndPassword(email, password);
    }

    @Override
    public Optional<Customer> findByName(String name) {
        return customerRepository.findByName(name);
    }

    @Override
    public Optional<Customer> findByEmailAddress(String email) {
        return customerRepository.findByEmailAddress(email);
    }

    @Override
    public Long findNewCustomerId() {
        return customerRepository.findNewCustomerId();
    }
}
