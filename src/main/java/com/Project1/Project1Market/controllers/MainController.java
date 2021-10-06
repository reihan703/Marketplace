package com.Project1.Project1Market.controllers;


import com.Project1.Project1Market.models.SellProduct;
import com.Project1.Project1Market.services.ProductSellService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @Autowired
    private ProductSellService productService;
    
    @RequestMapping("/")
    public String index(Model model) {
        List<SellProduct> products = productService.getAllProduct();
		model.addAttribute("products", products);
        return "index";
    }
    
    @GetMapping("/profile")
    public String view(Model model,  HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        
        long user_id = (long) session.getAttribute("id");
        
        List<SellProduct> products = productService.findByUserid(user_id);
		model.addAttribute("products", products);
        return "profile";
    }
    
}