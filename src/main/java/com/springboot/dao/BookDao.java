package com.springboot.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.springboot.entity.Book;
import com.springboot.entity.Rental;

@Mapper
public interface BookDao {
	List<Book> findBooksByUser(@Param("bookId") String bookId, @Param("authorName") String authorName,
			@Param("bookName") String bookName, @Param("educationName") String educationName);

	int updateBookQuantity(@Param("bookId") String bookId);

	int insertRental(@Param("rental") Rental rental);

	int findQuantityByBookId(@Param("bookId") String bookId);

	int addBook(@Param("book") Book book);

	int updateBook(@Param("book") Book book);

	int deleteBook(@Param("bookId") String bookId);
}
