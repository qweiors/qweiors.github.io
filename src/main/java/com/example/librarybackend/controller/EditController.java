package com.example.librarybackend.controller;

import com.example.librarybackend.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class EditController {
  AdminServe adminServe = new AdminServe();
  UserServe userServe = new UserServe();

  @GetMapping("editBookRequest")
  public String editBook(@ModelAttribute("ISBN") String ISBN, @ModelAttribute("userId") String userId,
                         @ModelAttribute("name") String bookName, @ModelAttribute("publication") String publication,
                         @ModelAttribute("authorName") String authorName,
                         @ModelAttribute("subjectName") String subjectName, @ModelAttribute("location") String location, Model model) {
    Book book = new Book(
        "", bookName, 0, publication, authorName, subjectName, '0', ISBN, location
    );
    boolean result = adminServe.editBook(ISBN, book);
    if (result) {
      model.addAttribute("user", userServe.findById(userId));
      return "search";
    } else {
      model.addAttribute("book", book);
      model.addAttribute("user", userServe.findById(userId));
      model.addAttribute("errmsg", "修改失败");
      return "edit";
    }
  }

  @GetMapping("toAddRequest")
  String addRequestView(@ModelAttribute("id") String userId, Model model) {
    model.addAttribute("user", userServe.findById(userId));
    return "add";
  }

  @GetMapping("addBookRequest")
  public String addBookRequest(@ModelAttribute("num") int num, @ModelAttribute("userId") String userId, @ModelAttribute("ISBN") String ISBN,
                               @ModelAttribute("name") String bookName, @ModelAttribute("publication") String publication,
                               @ModelAttribute("authorName") String authorName, @ModelAttribute("location") String location,
                               @ModelAttribute("subjectName") String subjectName, Model model) {
    Book book = new Book("0", bookName, 0, publication, authorName, subjectName, '0', ISBN, location);
    List<String> idList = adminServe.addBook(book, num);
    model.addAttribute("user", userServe.findById(userId));
    if (idList.size() > 0) {
      model.addAttribute("idList", idList);
      return "addList";
    } else {
      model.addAttribute("errmsg", "添加失败");
      return "add";
    }
  }

  @GetMapping("removeOneBookRequest")
  public String removeOneView(@ModelAttribute("id") String bookId, @ModelAttribute("userId") String userId, Model model)
  {
    adminServe.removeBookById(bookId);
    User user = userServe.findById(userId);
    model.addAttribute("user", user);
    return "search";
  }
}
