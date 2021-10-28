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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    		@RequestParam("price") String priceProduct,
    		@RequestParam("desc") String desc,
                @RequestParam("id_user") long id, RedirectAttributes ra)
    {
    	int price;
        
        if(priceProduct.equals("")){
            
            ra.addFlashAttribute("danger", "Price cannot be null!");
            return "redirect:/createBuy";
        }
        
        int nameLength = name.length();
        int descLength = desc.length();
        price = Integer.parseInt(priceProduct);
            
        if (name.equals("") || nameLength > 20){
                
                ra.addFlashAttribute("danger", "Name must less than 20 Character and cannot be null!");
                return "redirect:/createBuy";
            
        }
        if (desc.equals("") || descLength > 1000){
                
                ra.addFlashAttribute("danger", "Desc must less than 1000 Character and cannot be null!");
                return "redirect:/createBuy";
        }

        productBuyService.saveProductToDB(file, name, desc, price, id);
        
        return "redirect:/profile";
    }
    
    @PostMapping("/updatePB")
    public String updateProductBuy(@RequestParam("image") MultipartFile file,
    		@RequestParam("item_Name") String name,
    		@RequestParam("item_Price") String priceProduct,
    		@RequestParam("item_Desc") String desc,
                @RequestParam("id_user") long id,
                @RequestParam("id_Buy") long id_buy, RedirectAttributes ra)
    {
        int price;
        
        if(priceProduct.equals("")){
            
            ra.addFlashAttribute("danger", "Price cannot be null!");
            return "redirect:/createBuy";
        }
        
        int nameLength = name.length();
        int descLength = desc.length();
        price = Integer.parseInt(priceProduct);
            
        if (name.equals("") || nameLength > 20){
                
                ra.addFlashAttribute("danger", "Name must less than 20 Character and cannot be null!");
                return "redirect:/createBuy";
            
        }
        if (desc.equals("") || descLength > 1000){
                
                ra.addFlashAttribute("danger", "Desc must less than 1000 Character and cannot be null!");
                return "redirect:/createBuy";
        }

        productBuyService.updateProductToDB(file, name, desc, price, id, id_buy);
        
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
