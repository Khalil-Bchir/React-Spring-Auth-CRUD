package com.restaurant.restaurant.services;

import com.restaurant.restaurant.entities.Items;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IServiceItem {

    void saveItem(Items item);

    public List<Items> getAllItems();
    public Page<Items> getItemByMc(String mc, Pageable t);
    public List<Items> getItemByCat(Long idCat);
    public void deleteItem(Long id);
    public Items getItem (Long id);
    public void editItem(Long id, Items editedItem);

}
