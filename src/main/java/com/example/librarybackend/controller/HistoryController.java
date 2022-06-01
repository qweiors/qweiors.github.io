package com.example.librarybackend.controller;

import com.example.librarybackend.service.Book;
import com.example.librarybackend.service.Log;
import com.example.librarybackend.service.LogOption;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.librarybackend.service.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HistoryController {

    public class reserveShow
    {
        private String ISBN;
        private String id;
        private String name;
        private String publicationDate;
        private String authorName;
        private String subjectName;
        private String reserveTime;
        private int reserveHour;

        public reserveShow(reserveEvent event, Book book)
        {
            this.ISBN = book.getISBN();
            this.id = book.getId();
            this.name = book.getName();
            this.publicationDate = book.getPublication();
            this.authorName = book.getAuthorName();
            this.subjectName = book.getSubjectName();
            this.reserveTime = event.getYear() + "/" + event.getMonth() + "/" + event.getDay() + "/" + event.getHour();
            this.reserveHour = event.getReserveHour();
        }

        public String getISBN() {
            return ISBN;
        }

        public void setISBN(String ISBN) {
            this.ISBN = ISBN;
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

        public String getPublicationDate() {
            return publicationDate;
        }

        public void setPublicationDate(String publicationDate) {
            this.publicationDate = publicationDate;
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

        public String getReserveTime() {
            return reserveTime;
        }

        public void setReserveTime(String reserveTime) {
            this.reserveTime = reserveTime;
        }

        public int getReserveHour() {
            return reserveHour;
        }

        public void setReserveHour(int reserveHour) {
            this.reserveHour = reserveHour;
        }
    }

    public class borrowShow
    {
        private String ISBN;
        private String id;
        private String name;
        private String publicationDate;
        private String authorName;
        private String subjectName;
        private String borrowDate;
        private int borrowDay;

        public borrowShow(borrowEvent event, Book book)
        {
            this.ISBN = book.getISBN();
            this.id = book.getId();
            this.name = book.getName();
            this.publicationDate = book.getPublication();
            this.authorName = book.getAuthorName();
            this.subjectName = book.getSubjectName();
            this.borrowDate = event.getYear() + "/" + event.getMonth() + "/" + event.getDay();
            this.borrowDay = event.getBorrowDay();
        }

        public String getISBN() {
            return ISBN;
        }

        public void setISBN(String ISBN) {
            this.ISBN = ISBN;
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

        public String getPublicationDate() {
            return publicationDate;
        }

        public void setPublicationDate(String publicationDate) {
            this.publicationDate = publicationDate;
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

        public String getBorrowDate() {
            return borrowDate;
        }

        public void setBorrowDate(String borrowDate) {
            this.borrowDate = borrowDate;
        }

        public int getBorrowDay() {
            return borrowDay;
        }

        public void setBorrowDay(int borrowDay) {
            this.borrowDay = borrowDay;
        }
    }

    public class logShow
    {
        private String bookId;
        private String name;
        private String borrowDate;
        private String returnDate;

        public logShow(Log log)
        {
            this.bookId = log.getBookId();
            Book book = (new UserServe()).findBookById(log.getBookId());
            if(book == null) this.name = new String("have been delete");
            else this.name = book.getName();
            this.borrowDate = log.getBorrowDate();
            this.returnDate = log.getReturnDate();
        }

        public String getBookId() {
            return bookId;
        }

        public void setBookId(String bookId) {
            this.bookId = bookId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getBorrowDate() {
            return borrowDate;
        }

        public void setBorrowDate(String borrowDate) {
            this.borrowDate = borrowDate;
        }

        public String getReturnDate() {
            return returnDate;
        }

        public void setReturnDate(String returnDate) {
            this.returnDate = returnDate;
        }
    }

    @GetMapping("toHistory")
    public String toHistory(@ModelAttribute("id") String userId, Model model)
    {
        if(userId.length() > 10) userId = (new String(userId.substring(0, userId.length()-4)));
        User user = (new UserServe()).findById(userId);
        if(user == null) return "errorHistory";

        List<Log> logs = (new LogOption()).selectByUserId(userId);
        List<borrowEvent> events = (new BorrowService()).findByUserId(userId);
        List<borrowShow> borrows = new ArrayList<>();
        List<reserveEvent> reserves = (new ReserveService()).findByUserId(userId);
        List<reserveShow> reserveShows = new ArrayList<>();

        for(reserveEvent reserve : reserves)
        {
            reserveShows.add((new reserveShow(reserve, (new UserServe()).findBookById(reserve.getBookId()))));
        }

        for(borrowEvent event : events)
        {
            Book book = (new UserServe()).findBookById(event.getBookId());
            borrows.add((new borrowShow(event, book)));
        }

        List<logShow> logShows = new ArrayList<>();

        for(Log log : logs)
        {
            logShows.add((new logShow(log)));
        }
        model.addAttribute("borrows", borrows);
        model.addAttribute("logs", logShows);
        model.addAttribute("user", (new UserServe()).findById(userId));
        model.addAttribute("reserves", reserveShows);

        return "history";
    }

    @GetMapping("returnRequest")
    public String returnRequest(@ModelAttribute("bookId") String bookId, @ModelAttribute("userId") String userId, Model model)
    {
        (new BorrowService()).returnBook(userId, bookId);
        return "toHistory?id="+userId;
    }

    @GetMapping("renewRequest")
    public String renewRequest(@ModelAttribute("bookId") String bookId, @ModelAttribute("userId") String userId, Model model)
    {
        (new BorrowService()).renewBook(bookId);
        return "toHistory?id="+userId;
    }
}
