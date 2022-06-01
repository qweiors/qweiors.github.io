package com.example.librarybackend.controller;

import com.example.librarybackend.service.BookOption;
import com.example.librarybackend.service.*;
import com.example.librarybackend.service.ReserveService;

import com.example.librarybackend.service.UserServe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;
import java.util.List;

@Controller
public class borrowController {

    BorrowService bs = new BorrowService();
    ReserveService rs = new ReserveService();

    UserServe userServe = new UserServe();

    public class bookShow
    {
        private String id;
        private String name;
        private float price;
        private char borrowed;
        private String publication;
        private String authorName;
        private String subjectName;
        private String ISBN;
        private String location;
        private int num;

        public bookShow(Book book)
        {
            this.id = book.getId();
            this.name = book.getName();
            this.price = book.getPrice();
            this.borrowed = book.getBorrowed();
            this.publication = book.getPublication();
            this.authorName = book.getAuthorName();
            this.subjectName = book.getSubjectName();
            this.ISBN = book.getISBN();
            this.location = book.getLocation();
            this.num = (new BorrowService()).getNotBorrowNum(this.ISBN);
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public float getPrice() {
            return price;
        }

        public void setPrice(float price) {
            this.price = price;
        }

        public char getBorrowed() {
            return borrowed;
        }

        public void setBorrowed(char borrowed) {
            this.borrowed = borrowed;
        }

        public String getPublication() {
            return publication;
        }

        public void setPublication(String publication) {
            this.publication = publication;
        }

        public String getAuthorName() {
            return authorName;
        }

        public void setAuthorName(String authorName) {
            this.authorName = authorName;
        }

        public String getSubjectName() {
            return subjectName;
        }

        public void setSubjectName(String subjectName) {
            this.subjectName = subjectName;
        }

        public String getISBN() {
            return ISBN;
        }

        public void setISBN(String ISBN) {
            this.ISBN = ISBN;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public List<bookShow> getList(List<Book> books)
        {
            List<bookShow> bookShows = new ArrayList<>();
            for(Book book : books)
            {
                bookShows.add((new bookShow(book)));
            }
            return bookShows;
        }
    }

    @GetMapping("toBorrowRequest")
    public String toBorrowRequest(@ModelAttribute("id") String userId, Model model)
    {
        model.addAttribute("user", (new UserServe()).findById(userId));
        return "borrow";
    }

    @GetMapping("borrowBookRequest")
    public String borrowBookRequest(@ModelAttribute("ISBN") String ISBN, @ModelAttribute("userId") String userId,
                                    Model model)
    {
        String bookId = bs.borrowBookByISBN(ISBN, userId);
        model.addAttribute("user", (new UserServe()).findById(userId));
        if(!bookId.equals("")) model.addAttribute("errmsg", "borrow success, the book("+bookId+") in "+ (new UserServe()).findBookById(bookId).getLocation());
        else model.addAttribute("errmsg", "borrow error");
        return "borrow";
    }

    @GetMapping("borrowReserveBookRequest")
    public String borrowReserveBookRequest(@ModelAttribute("bookId") String bookId, @ModelAttribute("userId") String userId, Model model)
    {
        Boolean res = bs.borrowBook(userId, bookId);
        model.addAttribute("user", (new UserServe()).findById(userId));
        if(res == false) model.addAttribute("errmsg", "borrow failed");
        else
        {
            rs.deleteReserve(bookId);
            model.addAttribute("errmsg", "borrow success, the book("+bookId+") in "+ (new UserServe()).findBookById(bookId).getLocation());
        }
        return "toHistory?id="+userId;
    }

    @GetMapping("reserveBookRequest")
    public String reserveBookRequest(@ModelAttribute("ISBN") String ISBN, @ModelAttribute("userId") String userId,
                                    Model model)
    {
        Boolean res = rs.reserveByISBN(ISBN, userId);
        model.addAttribute("user", (new UserServe()).findById(userId));
        if(res) model.addAttribute("errmsg", "reserve success!");
        else model.addAttribute("errmsg", "reserve error");
        return "borrow";
    }

    @GetMapping("BsearchByPublicationRequest")
    String searchByPublicationRequest(@ModelAttribute("publication") String publication,
                                      @ModelAttribute("id") String id, Model model) {
        model.addAttribute("publication", publication);
        model.addAttribute("user", userServe.findById(id));
        model.addAttribute("books", (new bookShow(new Book())).getList(userServe.findBypublication(publication)));
        return "borrow";
    }

    @GetMapping("BsearchBySubjectRequest")
    String searchBySubjectRequest(@ModelAttribute("subjectName") String subjectName,
                                  @ModelAttribute("id") String id, Model model) {
        model.addAttribute("subjectName", subjectName);
        model.addAttribute("user", userServe.findById(id));
        model.addAttribute("books", (new bookShow(new Book())).getList(userServe.findBySubject(subjectName)));
        return "borrow";
    }

    @GetMapping("BsearchByAuthorRequest")
    String searchByAuthorRequest(@ModelAttribute("authorName") String authorName,
                                 @ModelAttribute("id") String id, Model model) {
        model.addAttribute("authorName", authorName);
        model.addAttribute("user", userServe.findById(id));
        model.addAttribute("books", (new bookShow(new Book())).getList(userServe.findByAuthor(authorName)));
        return "borrow";
    }

    @GetMapping("BsearchByTitleRequest")
    String searchByTitleRequest(@ModelAttribute("name") String name,
                                @ModelAttribute("id") String id, Model model) {
        model.addAttribute("authorName", name);
        model.addAttribute("user", userServe.findById(id));
        model.addAttribute("books", (new bookShow(new Book())).getList(userServe.findByTitle(name)));
        return "borrow";
    }

    @GetMapping("BsearchAllRequest")
    String searchAllRequest(@ModelAttribute("id") String id, Model model) {
        model.addAttribute("user", userServe.findById(id));
        model.addAttribute("books", (new bookShow(new Book())).getList(userServe.findAll()));
        return "borrow";
    }

    @GetMapping("toReturnRequest")
    public String toReturnRequest(@ModelAttribute("id") String userId, Model model)
    {
        model.addAttribute("user", (new UserServe()).findById(userId));
        return "return";
    }

    @GetMapping("returnBookByIdRequest")
    public String returnBookByIdRequest(@ModelAttribute("bookId") String bookId, @ModelAttribute("userId") String userId,
                                        @ModelAttribute("memberId") String memberId, Model model)
    {
        Boolean res = (new BorrowService()).returnBook(memberId, bookId);
        model.addAttribute("user", (new UserServe()).findById(userId));
        model.addAttribute("memberId", memberId);
        model.addAttribute("bookId", bookId);
        if(!res) model.addAttribute("errmsg", "return error");
        else model.addAttribute("errmsg", "return success");
        return "return";
    }
}
