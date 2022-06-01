package com.example.librarybackend.controller;

import com.example.librarybackend.service.UserServe;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class LogoffUserController {
    UserServe userServe = new UserServe();

    @GetMapping("toLogoffUser")
    public String toLogoffUser(@ModelAttribute("id") String userId, Model model)
    {
        model.addAttribute("user", userServe.findById(userId));
        return "logoffUser";
    }

    @GetMapping("logoffUser")
    public String logoffUser(@ModelAttribute("id") String id, @ModelAttribute("userId") String userId, Model model)
    {
        Boolean res = userServe.logoff(userId);
        model.addAttribute("user", userServe.findById(id));
        if(res) model.addAttribute("errmsg", "logoff successfully");
        else model.addAttribute("errmsg", "logoff error");
        return "logoffUser";
    }

}
