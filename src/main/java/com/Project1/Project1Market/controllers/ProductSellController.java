package com.Project1.Project1Market.controllers;


import com.Project1.Project1Market.models.SellProduct;
import com.Project1.Project1Market.services.ProductSellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ProductSellController {
    @Autowired
    private ProductSellService productSellService;
    
    @GetMapping("/createSell")
    public String createSell(Model model) {

        SellProduct sellProduct = new SellProduct();
        model.addAttribute("sellProduct", sellProduct);
        
        return "createSell";
    }
    
     @PostMapping("/addPS")
    public String saveProductSell(@RequestParam("image") MultipartFile file,
    		@RequestParam("pname") String name,
    		@RequestParam("price") int price,
    		@RequestParam("desc") String desc,
                @RequestParam("id_user") long id,
                @RequestParam("phone_user") String phone)
    {
    	productSellService.saveProductToDB(file, name, desc, price, id, phone);
    	return "redirect:/profile";
    }
    
    @PostMapping("/updatePS")
    public String updateProduct(
                @RequestParam("image") MultipartFile file,
    		@RequestParam("item_Name") String name,
    		@RequestParam("item_Price") int price,
    		@RequestParam("item_Desc") String desc,
                @RequestParam("id_user") long id,
                @RequestParam("id_Sell") long id_sell,
                @RequestParam("phone_user") String phone)
    {
    	productSellService.updateProductToDB(file, name, desc, price, id, id_sell, phone);
    	return "redirect:/profile";
    }
    
    @GetMapping("/sellProduct/{id_Sell}/edit")
    public String edit(@PathVariable(value = "id_Sell") long id, Model model) {
      SellProduct sellProduct = productSellService.getById(id);

      model.addAttribute("sellProduct", sellProduct);
      return "edit_sell";
    }

    @PostMapping("/sellProduct/{id_Sell}/delete")
    public String delete(@PathVariable(value = "id_Sell") long id, Model model) {
      productSellService.delete(id);
      return "redirect:/profile";
    }
}