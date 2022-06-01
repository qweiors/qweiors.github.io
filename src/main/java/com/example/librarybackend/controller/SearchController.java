package com.example.librarybackend.controller;

import com.example.librarybackend.service.AdminServe;
import com.example.librarybackend.service.*;
import com.example.librarybackend.service.BookOption;
import com.example.librarybackend.service.UserServe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SearchController {
  private UserServe userServe = new UserServe();
  private AdminServe adminServe = new AdminServe();

  @GetMapping("search")
  String searchView(Model model) {
    return "search";
  }

  @GetMapping("toSearchRequest")
  String toSearchView(@ModelAttribute("id") String id, Model model) {
    model.addAttribute("user", userServe.findById(id));
    return "search";
  }

  @GetMapping("searchByPublicationRequest")
  String searchByPublicationRequest(@ModelAttribute("publication") String publication,
                                    @ModelAttribute("id") String id, Model model) {
    model.addAttribute("publication", publication);
    model.addAttribute("user", userServe.findById(id));
    model.addAttribute("books", userServe.findBypublication(publication));
    return "search";
  }

  @GetMapping("searchBySubjectRequest")
  String searchBySubjectRequest(@ModelAttribute("subjectName") String subjectName,
                                @ModelAttribute("id") String id, Model model) {
    model.addAttribute("subjectName", subjectName);
    model.addAttribute("user", userServe.findById(id));
    model.addAttribute("books", userServe.findBySubject(subjectName));
    return "search";
  }

  @GetMapping("searchByAuthorRequest")
  String searchByAuthorRequest(@ModelAttribute("authorName") String authorName,
                               @ModelAttribute("id") String id, Model model) {
    model.addAttribute("authorName", authorName);
    model.addAttribute("user", userServe.findById(id));
    model.addAttribute("books", userServe.findByAuthor(authorName));
    return "search";
  }

  @GetMapping("searchByTitleRequest")
  String searchByTitleRequest(@ModelAttribute("name") String name,
                              @ModelAttribute("id") String id, Model model) {
    model.addAttribute("name", name);
    model.addAttribute("user", userServe.findById(id));
    model.addAttribute("books", userServe.findByTitle(name));
    return "search";
  }

  @GetMapping("searchAllRequest")
  String searchAllRequest(@ModelAttribute("id") String id, Model model) {
    model.addAttribute("user", userServe.findById(id));
    model.addAttribute("books", userServe.findAll());
    return "search";
  }

  @GetMapping("toEditRequest")
  String toEditRequest(@ModelAttribute("ISBN") String ISBN,
                       @ModelAttribute("userId") String userId,
                       Model model) {
    model.addAttribute("book", (new BookOption()).selectByISBN(ISBN).get(0));
    model.addAttribute("user", userServe.findById(userId));
    return "edit";
  }

  @GetMapping("removeBookRequest")
  String removeBookRequest(@ModelAttribute("ISBN") String ISBN,
                           @ModelAttribute("userId") String userId,
                           Model model) {
    adminServe.removeBook(ISBN);
    model.addAttribute("user", userServe.findById(userId));
    return "search";
  }
}
