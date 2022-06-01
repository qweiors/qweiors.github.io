package com.example.librarybackend.controller;

import com.example.librarybackend.service.UserServe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class CheckUserController {
    UserServe userServe = new UserServe();
    @GetMapping("toCheckUser")
    public String toCheckUser(@ModelAttribute("id") String userId, Model model){
        model.addAttribute("user", userServe.findById(userId));
        return "checkUser";
    }
}
