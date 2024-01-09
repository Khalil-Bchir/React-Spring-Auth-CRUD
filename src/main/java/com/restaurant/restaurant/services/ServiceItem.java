package com.restaurant.restaurant.services;

import com.restaurant.restaurant.dao.ItemRepository;
import com.restaurant.restaurant.entities.Items;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ServiceItem implements IServiceItem {

    private ItemRepository itemRepository;

    @Override
    public void saveItem(Items item )   {
        itemRepository.save(item);

    }

    @Override
    public List<Items> getAllItems() {
        return itemRepository.findAll();
    }

    @Override
    public Items getItem(Long id) {
        return itemRepository.findById(id).orElse(null);
    }

    @Override
    public void editItem(Long id, Items editedItem) {
        Optional<Items> existingItemOptional = itemRepository.findById(id);
        if (existingItemOptional.isPresent()) {
            Items existingItem = existingItemOptional.get();
            existingItem.setName(editedItem.getName());
            existingItem.setPrice(editedItem.getPrice());
            existingItem.setDescription(editedItem.getDescription());
            existingItem.setMenu(editedItem.getMenu());

            itemRepository.save(existingItem);
        }
    }

    @Override
    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }

    @Override
    public Page<Items> getItemByMc(String mc, Pageable pageable) {
        return itemRepository.findByNameContains(mc, pageable);
    }

    @Override
    public List<Items> getItemByCat(Long idCat) {
        return itemRepository.getItemByCat(idCat);
    }

}
