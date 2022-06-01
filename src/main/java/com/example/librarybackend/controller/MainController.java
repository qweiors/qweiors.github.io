package com.example.librarybackend.controller;

import com.example.librarybackend.service.UserServe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class MainController {
  private UserServe userServe = new UserServe();

  @GetMapping("/main")
  public String mainView(Model model) {
    return "main";
  }

  @GetMapping("/toMain")
  public String mainView(@ModelAttribute("id") String id, Model model) {
    model.addAttribute("user", userServe.findById(id));
    return "main";
  }
}
