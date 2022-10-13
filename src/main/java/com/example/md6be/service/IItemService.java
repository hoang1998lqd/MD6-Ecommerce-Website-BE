package com.example.md6be.service;

import com.example.md6be.model.DTO.DTOItem;
import com.example.md6be.model.Item;


import java.util.List;

public interface IItemService extends IGeneralService<Item>{
    List<Item> findItemByCustomerId(Long id);

    void deleteAll(List<Item> items);
    List<Long> findListIdShop( Long idShop);
    List<Item> findAllItemByIdShop( Long idShop, Long idCustomer);
    public List<DTOItem>findAllDTOItem(Long idCustomer);
}
