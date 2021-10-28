package com.Project1.Project1Market.controllers;


import com.Project1.Project1Market.models.SellProduct;
import com.Project1.Project1Market.models.User;
import com.Project1.Project1Market.services.ProductSellService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    		@RequestParam("price") String priceProduct,
    		@RequestParam("desc") String desc,
                @RequestParam("id_user") long id, RedirectAttributes ra)
    {
        int price;
        
        if(priceProduct.equals("")){
            
            ra.addFlashAttribute("danger", "Price cannot be null!");
            return "redirect:/createSell";
        }
        
        int nameLength = name.length();
        int descLength = desc.length();
        price = Integer.parseInt(priceProduct);
            
        if (name.equals("") || nameLength > 20){
                
                ra.addFlashAttribute("danger", "Name must less than 20 Character and cannot be null!");
                return "redirect:/createSell";
            
        }
        if (desc.equals("") || descLength > 1000){
                
                ra.addFlashAttribute("danger", "Desc must less than 1000 Character and cannot be null!");
                return "redirect:/createSell";
        }

        productSellService.saveProductToDB(file, name, desc, price, id);
        
        return "redirect:/profile";    
    }
    
    @PostMapping("/updatePS")
    public String updateProduct(@RequestParam("image") MultipartFile file,
    		@RequestParam("item_Name") String name,
    		@RequestParam("item_Price") String priceProduct,
    		@RequestParam("item_Desc") String desc,
                @RequestParam("id_user") long id,
                @RequestParam("id_Sell") long id_sell, RedirectAttributes ra)
    {
        int price;
        
        if(priceProduct.equals("")){
            
            ra.addFlashAttribute("danger", "Price cannot be null!");
            return "redirect:/createSell";
        }
        
        int nameLength = name.length();
        int descLength = desc.length();
        price = Integer.parseInt(priceProduct);
            
        if (name.equals("") || nameLength > 20){
                
                ra.addFlashAttribute("danger", "Name must less than 20 Character and cannot be null!");
                return "redirect:/createSell";
            
        }
        if (desc.equals("") || descLength > 1000){
                
                ra.addFlashAttribute("danger", "Desc must less than 1000 Character and cannot be null!");
                return "redirect:/createSell";
        }

        productSellService.updateProductToDB(file, name, desc, price, id, id_sell);
        
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