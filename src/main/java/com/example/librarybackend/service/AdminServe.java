package com.example.librarybackend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AdminServe {
    public Boolean addBook(Book book){
        BookOption bo = new BookOption();
        return bo.add(book);
    }

    private String getRandomString(int length)
    {
        String str = "0123456789";
        Random random = new Random();
        StringBuffer myString = new StringBuffer();
        for(int i = 0; i < length; ++i)
        {
            int num = random.nextInt(10);
            myString.append(str.charAt(num));
        }
        return myString.toString();
    }

    public List<String> addBook(Book book, int num)
    {
        List<String> list = new ArrayList<>();
        BookOption bo = new BookOption();
        for(int i = 0; i < num; ++i)
        {
            String id = getRandomString(12);
            do {
                id = getRandomString(12);
                book.setId(id);
            }while(!bo.add(book));
            list.add(id);
        }
        return list;
    }

    public Boolean removeBook(String ISBN){
        BookOption bo = new BookOption();
        return bo.remove(ISBN);
    }

    public Boolean removeBookById(String id)
    {
        BookOption bo = new BookOption();
        return bo.removeById(id);
    }

    public List<User> findAllUser()
    {
        UserOption uo = new UserOption();
        return uo.selectAllUser();
    }

    public Boolean editBook(String ISBN,Book book){
        BookOption bo = new BookOption();
        return bo.update(ISBN,book);
    }
}
