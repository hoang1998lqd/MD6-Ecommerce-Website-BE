package com.example.md6be.service.impl;

import com.example.md6be.model.Cart;
import com.example.md6be.repository.CartRepository;
import com.example.md6be.service.ICartService;
import com.example.md6be.service.IItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService implements ICartService {
    @Autowired
    CartRepository cartRepository;

    @Autowired
    IItemService itemService;

    @Override
    public List<Cart> findAllByCustomerId(Long id) {
        return cartRepository.findCartByCustomerId(id);
    }

    @Override
    public void deleteAllByAccountDetail_Id(Long id) {
        cartRepository.deleteById(id);
    }

    @Override
    public List<Cart> findAll() {
        return cartRepository.findAll();
    }

    @Override
    public Optional<Cart> findById(Long id) {
        return cartRepository.findById(id);
    }

    @Override
    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public void delete(Long id) {
        cartRepository.deleteById(id);
    }

}
