package com.springboot.controller;

import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.controller.BookController;
import com.springboot.entity.Book;
import com.springboot.entity.BookRankList;
import com.springboot.entity.Rental;
import com.springboot.entity.UploadedFile;
import com.springboot.entity.UserRankList;
import com.springboot.service.BookService;
import com.springboot.service.RankListService;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;

public class BookControllerTest {

	@Tested
	public BookController bookController;

	@Injectable
	BookService bookService;

	@Injectable
	RankListService ranklistService;
	
	@Injectable
	HttpServletRequest request1;

	@Injectable
	HttpServletRequest request2;

	@Injectable
	MultipartFile file;

	@Injectable
	List<UserRankList> userRankList = new ArrayList<UserRankList>();
	@Injectable
	List<BookRankList> bookRankList = new ArrayList<BookRankList>();

	@Test
	public void testQueryBooksForAll() {
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

		List<Book> booklist = new ArrayList<Book>();
		booklist.add(book1);
		booklist.add(book2);

		new Expectations() {
			{
				Book book = new Book();
				bookService.queryBooks("", "", "", "");
				result = booklist;
			}
		};
		// replay
		List<Book> books = bookController.queryBooks("", "", "", "");
		assertEquals(2, books.size());
		assertEquals(booklist.get(0).getBookId(), books.get(0).getBookId());
	}

	@Test
	public void testLentForFalse() throws ParseException {
		Rental rental = new Rental();
		rental.setBookId("");
		rental.setUserId("");
		String date = "2020-06-24 07:18:42";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date dt = sdf.parse(date);
		Timestamp timestamp = new Timestamp(dt.getTime());
		rental.setRentalDatetime(timestamp);
		new Expectations() {
			{
				bookController.lent(rental);
				result = false;
			}
		};
		// replay
		boolean result = bookController.lent(rental);
		assertEquals(false, result);
	}

	@Test
	public void testLentForTrue() throws ParseException {
		Rental rental = new Rental();
		rental.setBookId("bookid");
		rental.setUserId("userid");
		String date = "2020-06-24 07:18:42";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date dt = sdf.parse(date);
		Timestamp timestamp = new Timestamp(dt.getTime());
		rental.setRentalDatetime(timestamp);
		new Expectations() {
			{
				bookController.lent(rental);
				result = false;
			}
		};
		new Expectations() {
			{
				bookService.updateBookCount(rental);
				result = true;
			}
		};
		// replay
		boolean result = bookController.lent(rental);
		assertEquals(true, result);
	}

	@Test
	public void testUpdateBookForFalse() {
		Book book = new Book();
		book.setBookId("");
		new Expectations() {
			{
				bookService.updateBook(book);
				result = false;
			}
		};
		// replay
		boolean result = bookController.updateBook(book);
		assertEquals(false, result);
	}
	
	@Test
	public void testUpdateBookForTrue() {
		Book book = new Book();
		book.setBookId("bookId");
		new Expectations() {
			{
				bookService.updateBook(book);
				result = true;
			}
		};
		// replay
		boolean result = bookController.updateBook(book);
		assertEquals(true, result);
	}
	
	@Test
	public void testDeleteBookForFalse() {
		new Expectations() {
			{
				bookService.deleteBook("");
				result = false;
			}
		};
		// replay
		boolean result = bookController.deleteBook("");
		assertEquals(false, result);
	}
	
	@Test
	public void testDeleteBookForTrue() {
		new Expectations() {
			{
				bookService.deleteBook("bookid");
				result = true;
			}
		};
		// replay
		boolean result = bookController.deleteBook("bookid");
		assertEquals(true, result);
	}
	
	@Test
	public void testUploadBookImgForFileNull() {
		UploadedFile uploadedFile = new UploadedFile();
		uploadedFile.setName("");
		uploadedFile.setStatus("failed");
		uploadedFile.setThumbUrl("");
		uploadedFile.setUrl("");
		new Expectations() {
			{
				file = null;
				result = uploadedFile;
			}
		};
		UploadedFile result = bookController.uploadBookImg(file);
		assertEquals("failed", result.getStatus());
	}
	
	@Test
	public void testUploadBookImgForFileNotNull() {
		UploadedFile uploadedFile = new UploadedFile();
		uploadedFile.setName("name");
		uploadedFile.setStatus("success");
		uploadedFile.setThumbUrl("thumburl");
		uploadedFile.setUrl("url");
		new Expectations() {
			{
				bookService.uploadBookImg(file);
				result = uploadedFile;
			}
		};
		UploadedFile result = bookController.uploadBookImg(file);
		assertEquals("success", result.getStatus());
	}
	
	@Test
	public void testUploadBookInfoForFalse() {
		Book book = new Book();
		new Expectations() {
			{
				bookService.addBook(book);
				result = false;
			}
		};
		// replay
		boolean result = bookController.uploadBookInfo(book);
		assertEquals(false, result);
	}
	
	@Test
	public void testUploadBookInfoForTrue() {
		Book book = new Book();
		book.setBookId("a134");
		book.setAuthorName("a");
		book.setBookDescription("qwer");
		book.setBookImg("img");
		book.setQuantity(20);
		book.setEducationName("eduname");
		book.setBookName("qqqq");
		new Expectations() {
			{
				bookService.addBook(book);
				result = true;
			}
		};
		// replay
		boolean result = bookController.uploadBookInfo(book);
		assertEquals(true, result);
	}
	
	@Test
	public void testQueryUserRL() {
		UserRankList userRankList1 = new UserRankList();
		userRankList1.setCountLentNum(20);
		userRankList1.setUserId("123456");
		userRankList1.setUserName("asd");
		
		UserRankList userRankList2 = new UserRankList();
		userRankList2.setCountLentNum(30);
		userRankList2.setUserId("1234567");
		userRankList2.setUserName("asdqw");
		
		List<UserRankList> userList = new ArrayList<UserRankList>();
		userList.add(userRankList1);
		userList.add(userRankList2);
		new Expectations() {
			{
				ranklistService.getUserRankList();
				result = userList;
			}
		};
		// replay
		List<UserRankList> userRankList = bookController.queryUserRL(request1);
		assertEquals(2, userRankList.size());
		assertEquals(userRankList.get(0).getUserId(), userList.get(0).getUserId());
	}

	@Test
	public void testQueryBookRL() {
		BookRankList bookRankList1 = new BookRankList();
		bookRankList1.setAuthorName("asd");
		bookRankList1.setBookName("qwe");
		bookRankList1.setCountLentNum(20);
		
		BookRankList bookRankList2 = new BookRankList();
		bookRankList1.setAuthorName("ghj");
		bookRankList1.setBookName("zxc");
		bookRankList1.setCountLentNum(30);
		
		List<BookRankList> bookRankList = new ArrayList<BookRankList>();
		bookRankList.add(bookRankList1);
		bookRankList.add(bookRankList2);
		
		new Expectations() {
			{
				ranklistService.getBookRankList();
				result = bookRankList;
			}
		};
		// replay
		List<BookRankList> bookList = bookController.queryBookRL(request2);
		assertEquals(2, bookList.size());
		assertEquals(bookRankList.get(0).getCountLentNum(), bookList.get(0).getCountLentNum());
	}

}
