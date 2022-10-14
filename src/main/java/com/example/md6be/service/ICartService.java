package com.example.md6be.service;

import com.example.md6be.model.Cart;


import java.util.List;

public interface ICartService extends IGeneralService<Cart>{
    public List<Cart> findAllByCustomerId(Long id);

    void deleteAllByAccountDetail_Id(Long id);
}
