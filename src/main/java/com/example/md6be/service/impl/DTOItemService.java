package com.example.md6be.service.impl;

import com.example.md6be.model.DTO.DTOItem;
import com.example.md6be.model.Item;
import com.example.md6be.repository.ItemRepository;
import com.example.md6be.service.IItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DTOItemService {

    @Autowired
    private ItemRepository itemRepository;

    private List<Item> findAllItemByCartId( Long idCustomer){
        return itemRepository.findAllItemByCartId(idCustomer);
    }
    private Long findShopIdByIdProduct(Long idProduct){
        return itemRepository.findShopIdByIdProduct(idProduct);
    }

    public List<DTOItem>findAllDTOItem(Long idCustomer){
        List<Item> listItem = findAllItemByCartId(idCustomer);
        List<DTOItem> dtoItemList = new ArrayList<>();
        for (Item item : listItem){
            Long shopId = findShopIdByIdProduct(item.getProduct().getId());
            DTOItem dtoItem = new DTOItem(item, shopId);
            dtoItemList.add(dtoItem);
        }
        return dtoItemList;
    }
}
