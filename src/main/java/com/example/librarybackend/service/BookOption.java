package com.example.librarybackend.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;

public class BookOption 
{
		private static Connection con = null;
		
		//select by publication
		List<Book> selectByPublication(String publicationDate)
		{
			List<Book> books = new ArrayList<Book>();
			con = DBConnect.conn();
			Statement stmt;
			try 
			{
				stmt = (Statement) con.createStatement();
				String sql = "select distinct name, price, publication_date, author_name, subject_name, ISBN, location from Book";
				ResultSet r = stmt.executeQuery(sql);
				while(r.next())
				{
					if(r.getString("publication_date").equals(publicationDate))
					{
						Book book = new Book();
						book.setISBN(r.getString("ISBN"));
						book.setName(r.getString("name"));
						book.setPrice(r.getFloat("price"));
						book.setPublication(r.getString("publication_date"));
						book.setAuthorName(r.getString("author_name"));
						book.setSubjectName(r.getString("subject_name"));
						book.setLocation(r.getString("location"));
						books.add(book);
					}
				}
				con.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			return books;
		}
		
		//select by Subject 	book.subjectName == subject
		List<Book> selectBySubject(String subject)
		{
			List<Book> books = new ArrayList<Book>();
			con = DBConnect.conn();
			Statement stmt;
			try 
			{
				stmt = (Statement) con.createStatement();
				String sql = "select distinct name, price, publication_date, author_name, subject_name, ISBN, location from Book";
				ResultSet r = stmt.executeQuery(sql);
				while(r.next())
				{
					if(r.getString("subject_name").equals(subject))
					{
						Book book = new Book();
						book.setISBN(r.getString("ISBN"));
						book.setName(r.getString("name"));
						book.setPrice(r.getFloat("price"));
						book.setPublication(r.getString("publication_date"));
						book.setAuthorName(r.getString("author_name"));
						book.setSubjectName(r.getString("subject_name"));
						book.setLocation(r.getString("location"));
						books.add(book);
					}
				}
				con.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			return books;
		}
		
		//select by Author		book.authorName == author
		List<Book> selectByAuthor(String author)
		{
			List<Book> books = new ArrayList<Book>();
			con = DBConnect.conn();
			Statement stmt;
			try 
			{
				stmt = (Statement) con.createStatement();
				String sql = "select distinct name, price, publication_date, author_name, subject_name, ISBN, location from Book";
				ResultSet r = stmt.executeQuery(sql);
				while(r.next())
				{
					if(r.getString("author_name").equals(author))
					{
						Book book = new Book();
						book.setISBN(r.getString("ISBN"));
						book.setName(r.getString("name"));
						book.setPrice(r.getFloat("price"));
						book.setPublication(r.getString("publication_date"));
						book.setAuthorName(r.getString("author_name"));
						book.setSubjectName(r.getString("subject_name"));
						book.setLocation(r.getString("location"));
						books.add(book);
					}
				}
				con.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			return books;
		}
		
		//select by Title		book.name == title
		List<Book> selectByTitle(String title)
		{
			List<Book> books = new ArrayList<Book>();
			con = DBConnect.conn();
			Statement stmt;
			try 
			{
				stmt = (Statement) con.createStatement();
				String sql = "select distinct name, price, publication_date, author_name, subject_name, ISBN, location from Book";
				ResultSet r = stmt.executeQuery(sql);
				while(r.next())
				{
					if(r.getString("name").equals(title))
					{
						Book book = new Book();
						book.setISBN(r.getString("ISBN"));
						book.setName(r.getString("name"));
						book.setPrice(r.getFloat("price"));
						book.setPublication(r.getString("publication_date"));
						book.setAuthorName(r.getString("author_name"));
						book.setSubjectName(r.getString("subject_name"));
						book.setLocation(r.getString("location"));
						books.add(book);
					}
				}
				con.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			return books;
		}
		
		//add book to sql
		//if success return true
		Boolean add(Book book)
		{
			con = DBConnect.conn();
			boolean judge = false;
			try 
			{
				java.sql.PreparedStatement ps = null;
				String sql_insert = "INSERT INTO Book(id, name, price, publication_date, author_name, subject_name, borrowed, ISBN, location) VALUES(?,?,?,?,?,?,?,?,?)";
				ps = con.prepareStatement(sql_insert);
				ps.setString(1, book.getId());
				ps.setString(2,  book.getName());
				ps.setFloat(3, book.getPrice());
				ps.setString(4, book.getPublication());
				ps.setString(5, book.getAuthorName());
				ps.setString(6, book.getSubjectName());
				ps.setString(7, String.valueOf(book.getBorrowed()));
				ps.setString(8, book.getISBN());
				ps.setString(9, book.getLocation());
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
		
		public Book selectById(String id)
		{
			con = DBConnect.conn();
			Statement stmt;
			Book book = null;
			try 
			{
				stmt = (Statement) con.createStatement();
				String sql = "select * from Book";
				ResultSet r = stmt.executeQuery(sql);
				while(r.next())
				{
					if(r.getString("id").equals(id))
					{
						book = new Book();
						book.setId(r.getString("id"));
						book.setName(r.getString("name"));
						book.setPrice(r.getFloat("price"));
						book.setPublication(r.getString("publication_date"));
						book.setAuthorName(r.getString("author_name"));
						book.setSubjectName(r.getString("subject_name"));
						book.setBorrowed(r.getString("borrowed").charAt(0));
						book.setISBN(r.getString("ISBN"));
						book.setLocation(r.getString("location"));
					}
				}
				con.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			return book;
		}

		public List<Book> selectByISBN(String ISBN) {
			List<Book> books = new ArrayList<Book>();
			con = DBConnect.conn();
			Statement stmt;
			try {
				stmt = (Statement) con.createStatement();
				String sql = "select * from Book";
				ResultSet r = stmt.executeQuery(sql);
				while (r.next()) {
					if (r.getString("ISBN").equals(ISBN)) {
						Book book = new Book();
						book.setISBN(r.getString("ISBN"));
						book.setId(r.getString("id"));
						book.setName(r.getString("name"));
						book.setPrice(r.getFloat("price"));
						book.setPublication(r.getString("publication_date"));
						book.setAuthorName(r.getString("author_name"));
						book.setSubjectName(r.getString("subject_name"));
						book.setBorrowed(r.getString("borrowed").charAt(0));
						book.setLocation(r.getString("location"));
						books.add(book);
					}
				}
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return books;
		}

	public List<Book> selectBorrowed()
	{
		List<Book> books = new ArrayList<Book>();
		con = DBConnect.conn();
		Statement stmt;
		try {
			stmt = (Statement) con.createStatement();
			String sql = "select * from Book";
			ResultSet r = stmt.executeQuery(sql);
			while (r.next()) {
				if (r.getString("borrowed").charAt(0) == 49) {
					Book book = new Book();
					book.setISBN(r.getString("ISBN"));
					book.setId(r.getString("id"));
					book.setName(r.getString("name"));
					book.setPrice(r.getFloat("price"));
					book.setPublication(r.getString("publication_date"));
					book.setAuthorName(r.getString("author_name"));
					book.setSubjectName(r.getString("subject_name"));
					book.setBorrowed(r.getString("borrowed").charAt(0));
					book.setLocation(r.getString("location"));
					books.add(book);
				}
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return books;
	}

		//select all books
		public List<Book> selectAll()
		{
			List<Book> books = new ArrayList<Book>();
			con = DBConnect.conn();
			Statement stmt;
			try 
			{
				stmt = (Statement) con.createStatement();
				String sql = "select distinct name, price, publication_date, author_name, subject_name, ISBN, location from Book";
				ResultSet r = stmt.executeQuery(sql);
				while(r.next())
				{
					Book book = new Book();
					book.setISBN(r.getString("ISBN"));
					book.setName(r.getString("name"));
					book.setPrice(r.getFloat("price"));
					book.setPublication(r.getString("publication_date"));
					book.setAuthorName(r.getString("author_name"));
					book.setSubjectName(r.getString("subject_name"));
					book.setLocation(r.getString("location"));
					books.add(book);
				}
				con.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			return books;
		}

	public List<Book> selectAllNodistinct()
	{
		List<Book> books = new ArrayList<>();
		con = DBConnect.conn();
		Statement stmt;
		try
		{
			stmt = (Statement) con.createStatement();
			String sql = "select * from Book";
			ResultSet r = stmt.executeQuery(sql);
			while(r.next())
			{
				Book book = new Book();
				book.setISBN(r.getString("ISBN"));
				book.setName(r.getString("name"));
				book.setPrice(r.getFloat("price"));
				book.setPublication(r.getString("publication_date"));
				book.setAuthorName(r.getString("author_name"));
				book.setSubjectName(r.getString("subject_name"));
				book.setLocation(r.getString("location"));
				books.add(book);
			}
			con.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return books;
	}


	//remove from Book where book.id == id
		Boolean removeById(String id)
		{
			System.out.println(id);
			con = DBConnect.conn();
			boolean judge = false;
			try 
			{
				java.sql.PreparedStatement ps = null;
				String sql_insert = "DELETE FROM Book where id = ?";
				ps = con.prepareStatement(sql_insert);
				ps.setString(1, id);
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

		public Boolean remove(String ISBN)
		{
			con = DBConnect.conn();
			boolean judge = false;
			try
			{
				java.sql.PreparedStatement ps = null;
				String sql_insert = "DELETE FROM Book where ISBN = ?";
				ps = con.prepareStatement(sql_insert);
				ps.setString(1, ISBN);
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
		
		//update book where book.id == id
		//if success return true
		Boolean update(String ISBN, Book book)
		{
			con = DBConnect.conn();
			boolean judge = false;
			try 
			{
				java.sql.PreparedStatement ps = null;
				String sql_update = "UPDATE Book SET name = ?, price = ?, publication_date = ?, author_name = ?, subject_name = ?, borrowed = ?, location = ? WHERE ISBN = ?";
				ps = con.prepareStatement(sql_update);
				ps.setString(1, book.getName());
				ps.setFloat(2,  book.getPrice());
				ps.setString(3, book.getPublication());
				ps.setString(4, book.getAuthorName());
				ps.setString(5, book.getSubjectName());
				ps.setString(6, String.valueOf(book.getBorrowed()));
				ps.setString(7, book.getLocation());
				ps.setString(8, ISBN);
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

		public Boolean updateById(String id, Book book)
		{
			con = DBConnect.conn();
			boolean judge = false;
			try
			{
				java.sql.PreparedStatement ps = null;
				String sql_update = "UPDATE Book SET name = ?, price = ?, publication_date = ?, author_name = ?, subject_name = ?, borrowed = ?, location = ? WHERE id = ?";
				ps = con.prepareStatement(sql_update);
				ps.setString(1, book.getName());
				ps.setFloat(2,  book.getPrice());
				ps.setString(3, book.getPublication());
				ps.setString(4, book.getAuthorName());
				ps.setString(5, book.getSubjectName());
				ps.setString(6, String.valueOf(book.getBorrowed()));
				ps.setString(7, book.getLocation());
				ps.setString(8, id);
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
}
