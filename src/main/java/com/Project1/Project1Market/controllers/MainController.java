package com.Project1.Project1Market.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }
    
    @GetMapping("/profile")
    public String profile() {

        return "profile";
    }
    
}