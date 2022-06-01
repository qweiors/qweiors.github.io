package com.example.librarybackend.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;

public class BorrowOption
{
    private static Connection con = null;
    Boolean insertIntoBorrow(borrowEvent event)
    {
        con = DBConnect.conn();
        boolean judge = false;
        try
        {
            java.sql.PreparedStatement ps = null;
            String sql_insert ="INSERT INTO borrow(user_id, book_id, year, month, day, borrow_day) VALUES(?,?,?,?,?,?)";
            ps = con.prepareStatement(sql_insert);
            ps.setString(1, event.getUserId());
            ps.setString(2,  event.getBookId());
            ps.setInt(3, event.getYear());
            ps.setInt(4, event.getMonth());
            ps.setInt(5, event.getDay());
            ps.setInt(6, event.getBorrowDay());
            int n = ps.executeUpdate();	//used to check success or not
            con.close();
            //check whether update success or not
            if(n > 0)
            {
                judge = true;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return judge;
    }
    Boolean removeBorrow(String bookId)
    {
        con = DBConnect.conn();
        boolean judge = false;
        try
        {
            java.sql.PreparedStatement ps = null;
            String sql_delete = "DELETE FROM borrow where book_id = ?";
            ps = con.prepareStatement(sql_delete);
            ps.setString(1, bookId);
            int n = ps.executeUpdate();	//used to check success or not
            con.close();
            if(n > 0)
            {
                judge = true;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return judge;
    }
    Boolean updateBorrow(String bookId, int borrowDay)
    {
        con = DBConnect.conn();
        boolean judge = false;
        try
        {
            java.sql.PreparedStatement ps = null;
            String sql_update = "UPDATE borrow SET borrow_day = ? WHERE book_id = ?";
            ps = con.prepareStatement(sql_update);
            ps.setInt(1, borrowDay);
            ps.setString(2,  bookId);
            int n = ps.executeUpdate();	//used to check success or not
            con.close();
            //check whether update success or not
            if(n > 0)
            {
                judge = true;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return judge;
    }
    List<borrowEvent> selectByUserId(String userId)
    {
        List<borrowEvent> events = new ArrayList<borrowEvent>();
        con = DBConnect.conn();
        Statement stmt;
        try
        {
            stmt = (Statement) con.createStatement();
            String sql = "select * from borrow";
            ResultSet r = stmt.executeQuery(sql);
            while(r.next())
            {
                if(r.getString("user_id").equals(userId))
                {
                    borrowEvent event = new borrowEvent();
                    event.setUserId(r.getString("user_id"));
                    event.setBookId(r.getString("book_id"));
                    event.setYear(r.getInt("year"));
                    event.setMonth(r.getInt("month"));
                    event.setDay(r.getInt("day"));
                    event.setBorrowDay(r.getInt("borrow_day"));
                    events.add(event);
                }
            }
            con.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return events;
    }
    public borrowEvent selectByBookId(String bookId)
    {
        con = DBConnect.conn();
        Statement stmt;
        borrowEvent event = null;
        try
        {
            stmt = (Statement) con.createStatement();
            String sql = "select * from borrow";
            ResultSet r = stmt.executeQuery(sql);
            while(r.next())
            {
                if(r.getString("book_id").equals(bookId))
                {
                    event = new borrowEvent();
                    event.setUserId(r.getString("user_id"));
                    event.setBookId(r.getString("book_id"));
                    event.setYear(r.getInt("year"));
                    event.setMonth(r.getInt("month"));
                    event.setDay(r.getInt("day"));
                    event.setBorrowDay(r.getInt("borrow_day"));
                }
            }
            con.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return event;
    }


}
