package com.Project1.Project1Market.controllers;


import com.Project1.Project1Market.interfaces.InventoryBuyInterface;
import com.Project1.Project1Market.interfaces.InventorySellInterface;
import com.Project1.Project1Market.models.InventoryBuy;
import com.Project1.Project1Market.models.InventorySell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class MainController {
    
    @Autowired
    private InventorySellInterface inventorySellInterface;
    
    @Autowired
    private InventoryBuyInterface inventoryBuyInterface;
    
    @GetMapping("/home")
    public String index(Model model) {
        model.addAttribute("list", inventorySellInterface.getAll());
        model.addAttribute("list", inventoryBuyInterface.getAll());
        
        return "index";
    }
    
    @GetMapping("/profile")
    public String profile() {

        return "profile";
    }

    @GetMapping("/home/profile/create-sell")
    public String create_sell(Model model) {

        InventorySell inventorySell = new InventorySell();
        model.addAttribute("inventorySell", inventorySell);

        return "create_sell";
    }
    
    @GetMapping("/home/profile/create-buy")
    public String create_buy(Model model) {

        InventoryBuy inventoryBuy = new InventoryBuy();
        model.addAttribute("inventoryBuy", inventoryBuy);

        return "create_buy";
    }

    @GetMapping("/profile/{id}/edit-sell")
    public String edit_sell(@PathVariable(value = "id_Sell") long id, Model model) {
         InventorySell inventorySell = inventorySellInterface.getById(id);

        model.addAttribute("inventorySell",inventorySell);
        return "edit_sell";
    }

    @PostMapping("/profile/{id}/delete-sell")
    public String deleteSell(@PathVariable(value = "id_Sell") long id, long user_id){
        inventorySellInterface.delete(id, user_id);
        return "redirect:/";
    }
    
    @PostMapping("/profile/{id}/delete-buy")
    public String deleteBuy(@PathVariable(value = "id_Buy") long id, long user_id) {
        inventoryBuyInterface.delete(id, user_id);
        return "redirect:/";
    }
    
    
}