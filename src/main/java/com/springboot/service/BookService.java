package com.springboot.service;

import java.util.List;

import com.springboot.entity.Book;
import com.springboot.entity.Rental;

public interface BookService {
	public List<Book> getBooksAll ();
	public List<Book> queryBookByUser(String bookId, String authorName,String bookName,String educationName);
	public List<Book> queryBookByAuthorName(String authorName);
	public List<Book> queryBookByBookName(String bookName);
	public List<Book> queryBookByEducationName(String educationName);
	public boolean updateBookCount(Rental rental);
	public boolean addBook(Book book);
}
