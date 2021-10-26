/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Project1.Project1Market.controllers;


import com.Project1.Project1Market.interfaces.UserInterface;
import com.Project1.Project1Market.models.User;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author USER
 */
@Controller
public class RegisterController {

    @Autowired
    private UserInterface userInterface;

    @GetMapping("/register")
    public String index(Model model) {

        User user = new User();
        model.addAttribute("user", user);

        return "register";
    }

//    @PostMapping("/register")
//    public String store(@ModelAttribute("user") User user, 
//            HttpServletRequest request, RedirectAttributes ra) throws Exception {
//        try{
//            userInterface.register(user);
//        }
//        catch (Exception e){
//            return "redirect:/register";
//        }
//        return "redirect:/login";
//    }
    
    @PostMapping("/register")
    public String store(@ModelAttribute("user") User user, 
            HttpServletRequest request, RedirectAttributes ra) throws Exception {
        if (user.getName().equals("")) {
            ra.addFlashAttribute("danger", "Name cannot be null!");
            return "redirect:/register";
        }
        
        if (user.getPassword().equals("")) {
            ra.addFlashAttribute("danger", "Password cannot be null!");
            return "redirect:/register";
        }
        
        if (user.getAddress().equals("")) {
            ra.addFlashAttribute("danger", "Address cannot be null!");
            return "redirect:/register";
        }
        
        if (user.getCity().equals("")) {
            ra.addFlashAttribute("danger", "City cannot be null!");
            return "redirect:/register";
        }
        
        if (user.getEmail().equals("")) {
            ra.addFlashAttribute("danger", "Email cannot be null!");
            return "redirect:/register";
        }
        
        if (user.getPhone().equals("")) {
            ra.addFlashAttribute("danger", "Phone cannot be null!");
            return "redirect:/register";
        }
        
        userInterface.register(user);
        return "redirect:/login";
    }

}
