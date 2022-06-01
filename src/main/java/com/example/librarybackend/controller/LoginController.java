package com.example.librarybackend.controller;

import com.example.librarybackend.service.User;
import com.example.librarybackend.service.UserServe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class LoginController {
  private UserServe userServe = new UserServe();

  @GetMapping("/login")
  public String loginView(Model model) {
    return "login";
  }

  @PostMapping("/login")
  public RedirectView login(@ModelAttribute("id") String id,
                            @ModelAttribute("password") String password,
                            RedirectAttributes redirectAttributes) {
    RedirectView redirectView;
    User user = userServe.login(id, password);
    System.out.println(user);
    if (user != null) {
      redirectView = new RedirectView("toMain?id="+user.getId(), true);
    } else {
      redirectView = new RedirectView("login", true);
      redirectAttributes.addFlashAttribute("errmsg", "账号或密码错误");
    }
    return redirectView;
  }
}
