package com.springboot.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.springboot.entity.Book;
import com.springboot.entity.Rental;
import com.springboot.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import net.sf.json.JSONObject;

@CrossOrigin(origins = { "http://localhost:4200", "null" })
@RequestMapping("/api/books")
@RestController
public class QueryController {

	@Autowired
	private BookService bookService;

	// 根据条件查询
	@GetMapping("/query")
	@ResponseBody
	public List<Book> queryBooks(@RequestParam(value = "bookId", required = false, defaultValue = "") String bookId,
			@RequestParam(value = "authorName", required = false, defaultValue = "") String authorName,
			@RequestParam(value = "bookName", required = false, defaultValue = "") String bookName,
			@RequestParam(value = "educationName", required = false, defaultValue = "") String educationName) {

		return bookService.queryBooks(bookId, authorName, bookName, educationName);
	}

	// 点击借阅更新书的数目
	@PostMapping(value = "/lent")
	@ResponseBody
	public boolean lent(@RequestBody String lent) {
		JSONObject obj = JSONObject.fromObject(lent);
		Timestamp time = new Timestamp(System.currentTimeMillis()); // 获取系统当前时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String timeStr = df.format(time);
		time = Timestamp.valueOf(timeStr);
		Rental rental = new Rental();
		rental.setUserId(obj.get("userId").toString());
		rental.setBookId(obj.get("bookId").toString());
		rental.setRentalDatetime(time);
		boolean stat = bookService.updateBookCount(rental);
		return stat;
	}

	// 编辑图书
	@PutMapping(value = "/updateBook")
	@ResponseBody
	public boolean updateBook(@RequestBody Book book) {
		// System.out.println(book);
		// System.out.println(book.getBookId());
		// System.out.println(book.getBookName());
		// System.out.println(book.getAuthorName());
		// System.out.println(book.getEducationName());
		// System.out.println(book.getQuantity());
		return bookService.updateBook(book);
	}

	// 删除图书
	@DeleteMapping(value = "/delete/{bookId}")
	@ResponseBody
	public boolean deleteBook(@PathVariable("bookId") String bookId) {
		return bookService.deleteBook(bookId);
	}

	// TODO 新增图书
	@PostMapping(value = "/addBook")
	@ResponseBody
	public boolean uploadBookInfo(@RequestBody Book book) {
		// System.out.println(book);
		// System.out.println(book.getBookId());
		// System.out.println(book.getBookName());
		// System.out.println(book.getAuthorName());
		// System.out.println(book.getEducationName());
		// System.out.println(book.getQuantity());
		return bookService.addBook(book);
	}

}
