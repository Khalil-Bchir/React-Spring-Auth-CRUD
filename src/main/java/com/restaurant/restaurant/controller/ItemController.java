package com.restaurant.restaurant.controller;

import com.restaurant.restaurant.entities.Items;
import com.restaurant.restaurant.services.ServiceItem;
import com.restaurant.restaurant.services.ServiceMenu;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@AllArgsConstructor
@RequestMapping("/api/items")
public class ItemController {

    ServiceItem serviceItem;
    ServiceMenu serviceMenu;

    @GetMapping("/getItem")
    public String getAllItems (Model model, @RequestParam(name = "mc", defaultValue = "") String mc, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "5") int size) {
        Page<Items> l = serviceItem.getItemByMc(mc, PageRequest.of(page, size));
        model.addAttribute("mc", mc);
        model.addAttribute("products", l.getContent());
        model.addAttribute("pages", new int[l.getTotalPages()]);
        model.addAttribute("current page", l.getNumber());

        return "menu";
    }

    @GetMapping("/deleteItem/{id}")
    public String deleteItem(@PathVariable("id") Long idItem){
        serviceItem.deleteItem(idItem);
        return "redirect:/api/menu/getMenu";
    }

    @GetMapping("/createForm")
    public String showCreateForm(Model model) {
        model.addAttribute("item", new Items());
        model.addAttribute("menu", serviceMenu.getAllMenu());
        return "createItemForm";
    }

    // Handle the form submission
    @PostMapping("/createItem")
    public String createItem(@ModelAttribute Items item, BindingResult bindingResult, Model model) throws IOException {
        if (bindingResult.hasErrors()) {
            model.addAttribute("menu", serviceMenu.getAllMenu());
            return "createItemForm";
        }
        serviceItem.saveItem(item);
        return "redirect:/api/menu/getMenu";
    }

    @GetMapping("/getItem/{id}")
    public String getItem(@PathVariable("id") Long idItem, Model model){
        Items items = serviceItem.getItem(idItem);
        model.addAttribute("item", items);
        return "item-details";
    }

    @GetMapping("/updateForm/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model){
        Items item = serviceItem.getItem(id);
        model.addAttribute("item", item);
        model.addAttribute("menu", serviceMenu.getAllMenu());
        return "updateItemForm";
    }

    @PostMapping("/updateItem/{id}")
    public String editItem (@PathVariable("id") Long id, @ModelAttribute Items editedIem){
        serviceItem.editItem(id, editedIem);
        return "redirect:/api/menu/getMenu";
    }
}
