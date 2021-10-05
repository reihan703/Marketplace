package com.Project1.Project1Market.controllers;


import com.Project1.Project1Market.services.ProductSellService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.Project1.Project1Market.models.SellProduct;

@Controller
public class ProductController {
    
    @Autowired
    private ProductSellService productService;
    
    
    @GetMapping("/createSell")
    public String create() {

        return "createSell";
    }
    
     @PostMapping("/addP")
    public String saveProduct(@RequestParam("image") MultipartFile file,
    		@RequestParam("pname") String name,
    		@RequestParam("price") int price,
    		@RequestParam("desc") String desc,
                @RequestParam("id_user") int id)
    {
    	productService.saveProductToDB(file, name, desc, price, id);
    	return "redirect:/profile";
    }
}