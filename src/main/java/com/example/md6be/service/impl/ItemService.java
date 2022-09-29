package com.example.md6be.service.impl;

import com.example.md6be.model.Item;
import com.example.md6be.repository.ItemRepository;
import com.example.md6be.service.IItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ItemService implements IItemService {

    @Autowired
    private ItemRepository itemRepository;


    @Override
    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    @Override
    public Optional<Item> findById(Long id) {
        return itemRepository.findById(id);
    }

    @Override
    public Item save(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public void delete(Long id) {
        itemRepository.deleteById(id);
    }

    @Override
    public List<Item> findItemByCustomerId(Long id) {
        return itemRepository.findItemByCustomerId(id);
    }



    // Phần xử lý giỏ hàng


    // Trả sản phẩm ở trong giỏ hàng
    private Item getItemById(Long id, Long idCustomer){
        List<Item> items = findItemByCustomerId(idCustomer);
        for (Item item : items){
            if (item.getProduct().getId().equals(id)){
                return item;
            }
        }
        return null;
    }


    // Thêm vào giỏ hàng
    public void addItem(Item item, Long idCustomer){
        if (getItemById(item.getProduct().getId(), idCustomer) != null){
            // Tìm ra sản phẩm đã có trong giỏ hàng
            Item itemNew = getItemById(item.getProduct().getId(),idCustomer);
            // Tăng số lượng sản phẩm lên mà k thêm sản phẩm cũ vào
            assert itemNew != null;
            itemNew.setQuantity(itemNew.getQuantity() + item.getQuantity());
        }else {
            save(item);
        }
    }
    // Xóa sản phẩm trong giỏ hàng
    public void removeItem(Long id, Long idCustomer){
        if (getItemById(id,idCustomer) != null){
            delete(id);
        }
    }

}
