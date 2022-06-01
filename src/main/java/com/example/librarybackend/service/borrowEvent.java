package com.example.librarybackend.service;

public class borrowEvent {
    private String userId;
    private String bookId;
    private int year;
    private int month;
    private int day;
    private int borrowDay;

    public borrowEvent(String userId, String bookId, int year, int month, int day, int borrowDay) {
        this.userId = userId;
        this.bookId = bookId;
        this.year = year;
        this.month = month;
        this.day = day;
        this.borrowDay = borrowDay;
    }

    public borrowEvent() {

    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getBorrowDay() {
        return borrowDay;
    }

    public void setBorrowDay(int borrowDay) {
        this.borrowDay = borrowDay;
    }

    @Override
    public String toString() {
        return "borrowEvent{" +
                "userId='" + userId + '\'' +
                ", bookId='" + bookId + '\'' +
                ", year=" + year +
                ", month=" + month +
                ", day=" + day +
                ", borrowDay=" + borrowDay +
                '}';
    }
}
