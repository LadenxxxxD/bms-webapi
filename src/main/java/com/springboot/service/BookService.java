package com.springboot.service;

import java.util.List;

import com.springboot.entity.Book;
import com.springboot.entity.Rental;
import com.springboot.entity.UploadedFile;

import org.springframework.web.multipart.MultipartFile;

public interface BookService {
	public List<Book> queryBooks(String bookId, String authorName, String bookName, String educationName);

	public boolean updateBookCount(Rental rental);

	public boolean addBook(Book book);

	public boolean updateBook(Book book);

	public boolean deleteBook(String bookId);

	public UploadedFile uploadBookImg(MultipartFile bookImg);
}
