package com.restaurant.restaurant.controller;

import com.restaurant.restaurant.entities.Menu;
import com.restaurant.restaurant.services.ServiceMenu;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.util.List;

@Controller
@AllArgsConstructor
public class MenuController {
    ServiceMenu serviceMenu;

    @GetMapping("/api/createMenu")
    public String showMenuForm(Model m) {
        m.addAttribute("menu", new Menu());
        return "createMenu";
    }

    @PostMapping("/api/createMenu")
    public String saveMenu(@Valid Menu menu, BindingResult bindingResult) throws IOException{
        if (bindingResult.hasErrors()) {
            return "createMenu";
        }
        serviceMenu.saveMenu(menu);
        return "redirect:/api/getMenu";
    }

    @GetMapping("/api/getMenu")
    public String getAllMenu(Model m) {
            List<Menu> menu = serviceMenu.getAllMenu();
            m.addAttribute("menu", menu);
            return "menu";
    }

    @GetMapping("/api/getMenu/{id}")
    public String getMenuById(@PathVariable("id") Long id, Model model){
        Menu menu =  serviceMenu.getMenu(id);
        model.addAttribute("menu", menu);
        return "checkMenu";
    }

    @GetMapping("/api/deleteMenu/{id}")
    public String deleteMenu(@PathVariable ("id") Long idMenu){
        serviceMenu.deleteMenu(idMenu);
        return "redirect:/api/getMenu";
    }

    @GetMapping("/api/editMenu/{id}")
    public String editMenuForm(@PathVariable("id") Long id, Model model){
        Menu menu = serviceMenu.getMenu(id);
        model.addAttribute("menu", menu);
        return "editMenu";
    }

    @PostMapping("/api/editMenu/{id}")
    public String editMenu(@PathVariable("id") Long id, @ModelAttribute Menu editedMenu){
        serviceMenu.editMenu(id, editedMenu);;
        return "redirect:/api/getMenu";
    }
}
