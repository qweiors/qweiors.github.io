package com.example.librarybackend.controller;

import com.example.librarybackend.service.AdminServe;
import com.example.librarybackend.service.BorrowService;
import com.example.librarybackend.service.UserServe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class dashboardController {
    private UserServe userServe = new UserServe();
    private AdminServe adminServe = new AdminServe();
    private BorrowService borrowService = new BorrowService();
    @GetMapping("toDashboardRequest")
    public String toDashboardRequest(@ModelAttribute("id") String userId, Model model)
    {
        model.addAttribute("user", userServe.findById(userId));
        model.addAttribute("bookNum", userServe.findAll().size());
        model.addAttribute("memberNum", adminServe.findAllUser().size());
        model.addAttribute("finedNum", borrowService.haveAllFined().size());
        model.addAttribute("bookNodistinctNum", userServe.findAllNodistinct().size());
        model.addAttribute("bookBorrows", userServe.findAllBorrowed().size());

        return "dashBoard";
    }

}
