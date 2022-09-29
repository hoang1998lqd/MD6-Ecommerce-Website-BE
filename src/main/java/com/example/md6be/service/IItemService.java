package com.example.md6be.service;

import com.example.md6be.model.Item;


import java.util.List;

public interface IItemService extends IGeneralService<Item>{
    List<Item> findItemByCustomerId(Long id);
}
