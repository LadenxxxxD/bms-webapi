package com.springboot.service.impl;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.javassist.expr.NewArray;
import org.junit.Test;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.dao.BookDao;
import com.springboot.entity.Book;
import com.springboot.entity.Rental;
import com.springboot.entity.UploadedFile;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;

public class BookServiceImplTest {

	@Tested
	BookServiceImpl bookServiceImpl;

	@Injectable
	BookDao bookDao;
	
	@Test
	public void testQueryBooks() {

		Book book1 = new Book();
		book1.setAuthorName("bookname1");
		book1.setBookId("bookid1");
		book1.setBookName("bookname1");
		book1.setEducationName("educationname1");

		Book book2 = new Book();
		book2.setAuthorName("bookname2");
		book2.setBookId("bookid2");
		book2.setBookName("bookname2");
		book2.setEducationName("education2");

		List<Book> books = new ArrayList<Book>();
		books.add(book1);
		books.add(book2);
		
		new Expectations() {
			{
				bookDao.findBooksByUser("", "", "", "");
				result = books;
			}
		};
		List<Book> booksList = bookServiceImpl.queryBooks("", "", "", "");
		assertEquals(2, booksList.size());
		assertEquals(books.get(0).getBookId(), booksList.get(0).getBookId());
	}

	@Test
	public void testUpdateBookCountForTrue() throws ParseException {
		Rental rental = new Rental();
		rental.setBookId("bookid");
		String date = "2020-06-24 07:18:42";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date dt = sdf.parse(date);
		Timestamp timestamp = new Timestamp(dt.getTime());
		rental.setRentalDatetime(timestamp);
		rental.setUserId("userid");
		new Expectations() {
			{
				bookDao.findQuantityByBookId(rental.getBookId());
				result = 1;
			}
			{
				bookDao.updateBookQuantity(rental.getBookId());
				result = 1;
			}
			{
				bookDao.insertRental(rental);
				result = 1;
			}
		};
		boolean flag = bookServiceImpl.updateBookCount(rental);
		assertEquals(true, flag);
	}

	@Test
	public void testUpdateBookCountForFalse01() throws ParseException {
		Rental rental = new Rental();
		rental.setBookId("bookid");
		String date = "2020-06-24 07:18:42";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date dt = sdf.parse(date);
		Timestamp timestamp = new Timestamp(dt.getTime());
		rental.setRentalDatetime(timestamp);
		rental.setUserId("userid");
		new Expectations() {
			{

				bookDao.findQuantityByBookId(rental.getBookId());
				int bookCount = -1;
				result = -1;
			}
		};
		boolean flag = bookServiceImpl.updateBookCount(rental);
		assertEquals(false, flag);
	}

	@Test
	public void testUpdateBookCountForFalse02() throws ParseException {
		Rental rental = new Rental();
		rental.setBookId("bookid");
		String date = "2020-06-24 07:18:42";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date dt = sdf.parse(date);
		Timestamp timestamp = new Timestamp(dt.getTime());
		rental.setRentalDatetime(timestamp);
		rental.setUserId("userid");
		new Expectations() {
			{
				bookDao.findQuantityByBookId(rental.getBookId());
				int bookCount = 1;
				result = 1;
			}
			{
				bookDao.updateBookQuantity(rental.getBookId());
				int updateNumber = 0;
				result = 0;
			}
		};
		boolean flag = bookServiceImpl.updateBookCount(rental);
		assertEquals(false, flag);
	}

	@Test
	public void testUpdateBookCountForFalse03() throws ParseException {
		Rental rental = new Rental();
		rental.setBookId("bookid");
		String date = "2020-06-24 07:18:42";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date dt = sdf.parse(date);
		Timestamp timestamp = new Timestamp(dt.getTime());
		rental.setRentalDatetime(timestamp);
		rental.setUserId("userid");
		new Expectations() {
			{
				bookDao.findQuantityByBookId(rental.getBookId());
				int bookCount = 1;
				result = 1;
			}
			{
				bookDao.updateBookQuantity(rental.getBookId());
				int updateNumber = 1;
				result = 1;
			}
			{
				bookDao.insertRental(rental);
				;
				int insertNumber = 0;
				result = 0;
			}
		};
		boolean flag = bookServiceImpl.updateBookCount(rental);
		assertEquals(false, flag);
	}

	@Test
	public void testAddBookForTrue01() {
		Book book = new Book();
		new Expectations() {
			{
				bookDao.addBook(book);
				result = 1;
			}
		};
		boolean flag = bookServiceImpl.addBook(book);
		assertEquals(true, flag);	
	}
		
	@Test
	public void testAddBookForFalse01() {
		Book book = new Book();
		new Expectations() {
			{
				bookDao.addBook(book);
				result = 0;
			}
		};
		boolean flag = bookServiceImpl.addBook(book);
		assertEquals(false, flag);	
	}
	
	@Test
	public void testAddBookForFalse02() {
		Book book = new Book();
		new Expectations() {
			{
				bookDao.addBook(book);
				result = new RuntimeException();
			}
		};
		boolean flag = bookServiceImpl.addBook(book);
		assertEquals(false, flag);	
	}
	
	@Test
	public void testUpdateBookForTrue() {
		Book book = new Book();
		new Expectations() {
			{
				bookDao.updateBook(book);
				result = 1;
			}
		};
		boolean flag = bookServiceImpl.updateBook(book);
		assertEquals(true, flag);	
	}
	
	@Test
	public void testUpdateBookForFalse01() {
		Book book = new Book();
		new Expectations() {
			{
				bookDao.updateBook(book);
				result = 0;
			}
		};
		boolean flag = bookServiceImpl.updateBook(book);
		assertEquals(false, flag);	
	}
	
	@Test
	public void testUpdateBookForFalse02() {
		Book book = new Book();
		new Expectations() {
			{
				bookDao.updateBook(book);
				result = new RuntimeException();
			}
		};
		boolean flag = bookServiceImpl.updateBook(book);
		assertEquals(false, flag);	
	}
	
	@Test
	public void testDeleteBookForTrue() {
		Book book = new Book();
		new Expectations() {
			{
				bookDao.deleteBook("bookid");
				result = 1;
			}
		};
		boolean flag = bookServiceImpl.deleteBook("bookid");
		assertEquals(true, flag);	
	}
	
	@Test
	public void testDeleteBookForFalse() {
		Book book = new Book();
		new Expectations() {
			{
				bookDao.deleteBook("");
				result = 0;
			}
		};
		boolean flag = bookServiceImpl.deleteBook("");
		assertEquals(false, flag);	
	}
	
	@Test
	public void testUploadBookImg() {
		Book book = new Book();
		new Expectations() {
			{
				bookDao.deleteBook("");
				result = 0;
			}
		};
		boolean flag = bookServiceImpl.deleteBook("");
		assertEquals(false, flag);	
	}
	
}
