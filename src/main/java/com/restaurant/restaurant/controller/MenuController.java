package com.restaurant.restaurant.controller;

import com.restaurant.restaurant.entities.Menu;
import com.restaurant.restaurant.services.ServiceMenu;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/api/menu")
public class MenuController {
    ServiceMenu serviceMenu;

    @GetMapping("/createMenu")
    public String showMenuForm(Model m) {
        m.addAttribute("menu", new Menu());
        return "createMenu";
    }

    @PostMapping("/createMenu")
    public String saveMenu(@Valid Menu menu, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "createMenu";
        }
        serviceMenu.saveMenu(menu);
        return "redirect:/api/menu/getMenu";
    }

    @GetMapping("/getMenu")
    public String getAllMenu(Model m) {
            List<Menu> menu = serviceMenu.getAllMenu();
            m.addAttribute("menu", menu);
            return "menu";
    }

    @GetMapping("/getMenu/{id}")
    public String getMenu(@PathVariable("id") Long id, Model model){
        Menu menu =  serviceMenu.getMenu(id);
        model.addAttribute("menu", menu);
        return "checkMenu";
    }

    @GetMapping("/deleteMenu/{id}")
    public String deleteMenu(@PathVariable ("id") Long idMenu){
        serviceMenu.deleteMenu(idMenu);
        return "redirect:/api/menu/getMenu";
    }

    @GetMapping("/editMenu/{id}")
    public String editMenuForm(@PathVariable("id") Long id, Model model){
        Menu menu = serviceMenu.getMenu(id);
        model.addAttribute("menu", menu);
        return "editMenu";
    }

    @PostMapping("/editMenu/{id}")
    public String editMenu(@PathVariable("id") Long id, @ModelAttribute Menu editedMenu){
        serviceMenu.editMenu(id, editedMenu);;
        return "redirect:/api/menu/getMenu";
    }
}
