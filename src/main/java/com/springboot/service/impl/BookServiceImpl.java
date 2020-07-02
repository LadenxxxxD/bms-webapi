package com.springboot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.dao.BookDao;
import com.springboot.entity.Book;
import com.springboot.entity.Rental;
import com.springboot.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	BookDao bookDao;

	@Override
	public List<Book> queryBooks(String bookId, String authorName, String bookName, String educationName) {
		// TODO Auto-generated method stub
		List<Book> books = bookDao.findBooksByUser(bookId, authorName, bookName, educationName);
		return books;
	}

	@Override
	public List<Book> queryBookByUser(String bookId, String authorName, String bookName, String educationName) {
		List<Book> books = bookDao.findBooksByUser(bookId, authorName, bookName, educationName);
		return books;
	}

	@Override
	public boolean updateBookCount(Rental rental) {
		int bookCount = bookDao.findQuantityByBookId(rental.getBookId());
		if (bookCount > 0) {// 剩余书的数目>0
			int updateNumber = bookDao.updateBookQuantity(rental.getBookId());
			if (updateNumber != 0) {// 减一成功
				int insertNumber = bookDao.insertRental(rental);
				if (insertNumber != 0) {// 插入数据库借阅时间成功
					return true;
				} else {// 插入数据库借阅时间失败
					return false;
				}
			} else {// 减一失败
				return false;
			}
		} else {// 剩余书的数目<0
			return false;
		}
	}

	@Override
	public boolean addBook(Book book) {
		try {
			int count = bookDao.addBook(book);
			if (count != 0) {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;

	}

}
