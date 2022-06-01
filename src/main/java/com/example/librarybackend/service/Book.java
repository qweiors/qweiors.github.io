package com.example.librarybackend.service;

public class Book {

	private String id;
	private String name;
	private float price;
	private char borrowed;
	private String publication;
	private String authorName;
	private String subjectName;
	private String ISBN;
	private String location;

	public void setISBN(String ISBN) {
		this.ISBN = ISBN;
	}

	public Book(String id, String name, float price, String publication, String authorName, String subjectName, char borrowed, String ISBN, String location) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.borrowed = borrowed;
		this.publication = publication;
		this.authorName = authorName;
		this.subjectName = subjectName;
		this.ISBN = ISBN;
		this.location = location;
	}

	public Book() {

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

	public void setLocation(String location) {
		this.location = location;
	}

	public String getISBN() {
		return ISBN;
	}

	public String getLocation() {
		return location;
	}

	@Override
	public String toString() {
		return "Book{" +
				"id='" + id + '\'' +
				", name='" + name + '\'' +
				", price=" + price +
				", borrowed=" + borrowed +
				", publication='" + publication + '\'' +
				", authorName='" + authorName + '\'' +
				", subjectName='" + subjectName + '\'' +
				", ISBN='" + ISBN + '\'' +
				", location='" + location + '\'' +
				'}';
	}
}

