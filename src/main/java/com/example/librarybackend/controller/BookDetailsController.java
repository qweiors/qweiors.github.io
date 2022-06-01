package com.example.librarybackend.controller;

import com.example.librarybackend.service.AdminServe;
import com.example.librarybackend.service.Book;
import com.example.librarybackend.service.BookOption;
import com.example.librarybackend.service.UserServe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class BookDetailsController {

    UserServe userServe = new UserServe();

    @GetMapping("toDetailsRequest")
    public String toDetailsView(@ModelAttribute("ISBN") String ISBN, @ModelAttribute("userId") String id,
                                Model model)
    {
        model.addAttribute("user", userServe.findById(id));
        model.addAttribute("books", (new BookOption()).selectByISBN(ISBN));
        return "bookDetails";
    }

    @GetMapping("toEditOneRequest")
    public String toEditOneView(@ModelAttribute("id") String bookId, @ModelAttribute("userId") String userId, Model model)
    {
        model.addAttribute("user", userServe.findById(userId));
        Book book = userServe.findBookById(bookId);
        model.addAttribute("book", userServe.findBookById(bookId));
        //model.addAttribute("errmsg", "");
        return "editOne";
    }
}
