package com.example.librarybackend.controller;

import com.example.librarybackend.service.UserServe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class changePasswordController {

    UserServe userServe = new UserServe();

    @GetMapping("toChangePassword")
    public String toChangePassword(@ModelAttribute("id") String userId, Model model)
    {
        model.addAttribute("user", userServe.findById(userId));
        return "changePassword";
    }

    @PostMapping("changePasswordRequest")
    public String changePasswordRequest(@ModelAttribute("userId") String userId,
                                        @ModelAttribute("oldPassword") String oldPassword,
                                        @ModelAttribute("newPassword") String newPassword, Model model)
    {
        String errmsg = userServe.changePassword(userId, oldPassword, newPassword);
        model.addAttribute("user", userServe.findById(userId));
        if(errmsg.equals("")) return "information";
        model.addAttribute("errmsg", errmsg);
        return "changePassword";
    }
}
