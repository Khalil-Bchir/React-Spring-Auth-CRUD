package com.restaurant.restaurant.services;

import com.restaurant.restaurant.entities.Menu;
import java.util.List;

public interface IServiceMenu {
    public void saveMenu(Menu menu);
    public List<Menu> getAllMenu();
    public Menu getMenu(Long id);
    public void deleteMenu( Long id);
    public void editMenu(Long id, Menu editedMenu);
}

