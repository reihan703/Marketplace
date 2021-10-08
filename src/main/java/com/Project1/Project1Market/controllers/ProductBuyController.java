package com.Project1.Project1Market.controllers;

import com.Project1.Project1Market.models.BuyProduct;
import com.Project1.Project1Market.services.ProductBuyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ProductBuyController {
    
    @Autowired
    private ProductBuyService productBuyService;
    
    @GetMapping("/createBuy")
    public String createBuy(Model model){
        
        BuyProduct buyProduct = new BuyProduct();
        model.addAttribute("buyProduct", buyProduct);
        
        return "createBuy";
    }
    
    @PostMapping("/addPB")
    public String saveProductBuy(@RequestParam("image") MultipartFile file,
    		@RequestParam("pname") String name,
    		@RequestParam("price") int price,
    		@RequestParam("desc") String desc,
                @RequestParam("id_user") long id,
                @RequestParam("phone_user") String phone)
    {
    	productBuyService.saveProductToDB(file, name, desc, price, id, phone);
    	return "redirect:/profile";
    }
    
    @PostMapping("/updatePB")
    public String updateProductBuy(
                @RequestParam("image") MultipartFile file,
    		@RequestParam("item_Name") String name,
    		@RequestParam("item_Price") int price,
    		@RequestParam("item_Desc") String desc,
                @RequestParam("id_user") long id,
                @RequestParam("id_Buy") long id_buy,
                @RequestParam("phone_user") String phone)
    {
        productBuyService.updateProductToDB(file, name, desc, price, id, id_buy, phone);
    	return "redirect:/profile";
    }
    
    //Edit
    @GetMapping("/buyProduct/{id_Buy}/edit")
    public String edit(@PathVariable(value = "id_Buy") long id, Model model) {
      BuyProduct buyProduct = productBuyService.getById(id);

      model.addAttribute("buyProduct", buyProduct);
      return "edit_buy";
    }

    @PostMapping("/buyProduct/{id_Buy}/delete")
    public String delete(@PathVariable(value = "id_Buy") long id, Model model) {
      productBuyService.delete(id);
      return "redirect:/profile";
    }
}
