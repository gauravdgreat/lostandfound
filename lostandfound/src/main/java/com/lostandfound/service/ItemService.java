package com.lostandfound.service;


import com.lostandfound.model.Item;
import com.lostandfound.repository.ItemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;
    // Method to find all lost items
    public List<Item> findAllLostItems() {
        return itemRepository.findAllByImageUrlNotNull(); // Adjust this based on your repository method
    }

    // Method to find all found items
    public List<Item> findAllFoundItems() {
        return itemRepository.findAllByImageUrlNull(); // Adjust this based on your repository method
    }
    public List<Item> searchItems(String query) {
        return itemRepository.findByNameContainingIgnoreCase(query);
    }


    public List<Item> getItemsByType(String type) {
        return itemRepository.findByNameContainingIgnoreCase(type);
    }

    public void saveItem(Item item) throws IOException {
        if (item.getImageUrl() != null) {
            // Implement image saving logic here (refer to ItemController)
        }
        itemRepository.save(item);
    }
}
