package com.Project1.Project1Market.controllers;


import com.Project1.Project1Market.models.BuyProduct;
import com.Project1.Project1Market.models.SellProduct;
import com.Project1.Project1Market.models.SuggestModel;
import com.Project1.Project1Market.services.FeedbackService;
import com.Project1.Project1Market.services.ProductBuyService;
import com.Project1.Project1Market.services.ProductSellService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
    
    @Autowired
    private FeedbackService feedbackService;
    
    @Autowired
    private ProductBuyService productBuyService;
    
    @Autowired
    private ProductSellService productSellService;
    
    @GetMapping("/")
    public String index(Model model) {
        List<BuyProduct> buyProduct = productBuyService.getAll();
                model.addAttribute("buyProduct", buyProduct);
        List<SellProduct> sellProduct = productSellService.getAll();
		model.addAttribute("sellProduct", sellProduct);
        return "index";
    }
    
    @GetMapping("/profile")
    public String viewProfile(Model model, HttpServletRequest request){
        
        HttpSession session = request.getSession(true);

        long user_id = (long) session.getAttribute("id");

        List<SellProduct> sellProduct = productSellService.findByUserid(user_id);
		model.addAttribute("sellProduct", sellProduct);
        List<BuyProduct> buyProduct = productBuyService.findByUserid(user_id);
                model.addAttribute("buyProduct", buyProduct);
                
        return "profile";
    }
    
    @GetMapping("/feedback")
    public String getFeedback(Model model){
        
        SuggestModel suggestModel = new SuggestModel();
        model.addAttribute("suggestModel", suggestModel);
        
        return "feedback";
    }
    
    @PostMapping("/feedback/store")
    public String postFeedback(@RequestParam("id_user") long id,
            @RequestParam("email") String email,
            @RequestParam("suggest") String suggest)
    {
        feedbackService.postFeedbackToDB(id, email, suggest);
        return "redirect:/";
    }
    /*
    @Autowired
    private InventorySellInterface inventorySellInterface;
    
    @Autowired
    private InventoryBuyInterface inventoryBuyInterface;
    
    @GetMapping("/home")
    public String index(Model model) {
        model.addAttribute("list", inventorySellInterface.getAll());
        model.addAttribute("list", inventoryBuyInterface.getAll());
        
    }
    */
    
    /*
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
    */
}