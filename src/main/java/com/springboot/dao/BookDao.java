package com.springboot.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.springboot.entity.Book;
import com.springboot.entity.Rental;

@Mapper
public interface BookDao {
	List<Book> findBooksAll();
	List<Book> findBooksByUser(@Param("authorName") String authorName,@Param("bookName") String bookName,@Param("educationName") String educationName);
	List<Book> findBooksByAuthor(@Param("authorName") String authorName);
	List<Book> findBooksByBookName(@Param("bookName") String bookName);
	List<Book> findBooksByEducationName(@Param("educationName") String educationName);
	int updateBookQuantity(@Param("bookId") String bookId);
	int insertRental(@Param("rental") Rental rental);
	int findQuantityByBookId(@Param("bookId") String bookId);
}
