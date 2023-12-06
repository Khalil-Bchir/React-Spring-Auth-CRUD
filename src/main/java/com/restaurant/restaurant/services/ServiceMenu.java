package com.restaurant.restaurant.services;

import com.restaurant.restaurant.dao.MenuRepository;
import com.restaurant.restaurant.entities.Menu;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ServiceMenu implements IServiceMenu {

    private MenuRepository menuRepository;

    @Override
    public void saveMenu(Menu menu){
        menuRepository.save(menu);
    }

    @Override
    public List<Menu> getAllMenu(){
        return menuRepository.findAll();
    }

    @Override
    public  Menu getMenu(Long id){
        Optional<Menu> optionalMenu = menuRepository.findById(id);
        return optionalMenu.orElse(null);
    }

    @Override
    public void deleteMenu(Long id){
        menuRepository.deleteById(id);
    }

    @Override
    public void editMenu(Long id, Menu editedMenu){
        Optional<Menu> existingMenuOptional = menuRepository.findById(id);
        if (existingMenuOptional.isPresent()){
            Menu existingMenu = existingMenuOptional.get();
            existingMenu.setName(editedMenu.getName());
            menuRepository.save(existingMenu);
        }
    }
}
