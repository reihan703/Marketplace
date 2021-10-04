/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Project1.Project1Market.controllers;


import com.Project1.Project1Market.interfaces.UserInterface;
import com.Project1.Project1Market.models.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
public class LoginController {

    @Autowired
    private UserInterface userInterface;

    @GetMapping("/login")
    public String index(Model model) {

        User user = new User();
        model.addAttribute("user", user);

        return "login";
    }

    @PostMapping("/login")
    public String store(@ModelAttribute("user") User user,
            HttpServletRequest request, RedirectAttributes ra) throws Exception {
        HttpSession session = request.getSession(true);

        User obj = userInterface.auth(user.getEmail(), user.getPassword());

        if (obj == null) {
            ra.addFlashAttribute("error", "Invalid username or password!");
            return "redirect:/login";
        }

        session.setAttribute("id", obj.getId());
        session.setAttribute("phone", obj.getPhone());
        session.setAttribute("city", obj.getCity());
        session.setAttribute("address", obj.getAddress());
        session.setAttribute("email", obj.getEmail());
        session.setAttribute("name", obj.getName());
        session.setAttribute("password", obj.getPassword());
        session.setAttribute("loggedIn", true);

        return "redirect:/";
    }

}
