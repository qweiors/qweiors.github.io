package com.example.librarybackend.controller;

import com.example.librarybackend.service.UserServe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class RegisterController {
  private UserServe userServe = new UserServe();

  @GetMapping("toRegister")
  public String toRegister(Model model) {return "register";}

  @GetMapping("/register")
  public String registerView(Model model) {
    return "register";
  }

  @PostMapping("/register")
  public RedirectView register(@ModelAttribute("name") String name,
                               RedirectAttributes redirectAttributes) {
    RedirectView redirectView = new RedirectView("register", true);
    if(name.equals(""))
    {
      redirectAttributes.addFlashAttribute("id", "name is empty");
      return redirectView;
    }
    String newId = userServe.register(name, "000000");
    redirectAttributes.addFlashAttribute("id", newId);
    return redirectView;
  }
}
