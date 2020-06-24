package com.springboot.controller;

import java.util.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.javassist.expr.NewArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.entity.Book;
import com.springboot.entity.Rental;
import com.springboot.service.BookService;
import com.springboot.service.UserService;

import net.sf.json.JSONObject;

@CrossOrigin(origins = { "http://localhost:4200", "null" })
@RestController
public class QueryController {
	
	@Autowired
	private BookService bookService;
	@Autowired
	private UserService userService;
	
	private List<Book> books = new ArrayList<Book>();
	//查询所有书籍
	@PostMapping(value = "/queryUser/queryBooks")
	@ResponseBody
	public List<Book> queryBooks(HttpServletRequest request) {
		books =  bookService.getBooksAll();
		return books;
	}
	//用户自定义查询
	@PostMapping(value = "/queryUser/queryBookByUser")
	@ResponseBody
	public List<Book> queryBookByUser(@RequestBody String queryInfo) {
		JSONObject obj = JSONObject.fromObject(queryInfo);
		books =  bookService.queryBookByUser(obj.get("authorName").toString(),obj.get("bookName").toString(),obj.get("educationName").toString());
		return books; 	
	}
	
	@PostMapping(value = "/queryUser/queryBookByAuthor")
	@ResponseBody
	public List<Book> queryBookByAuthorName(@RequestBody String authorName) {
		JSONObject obj = JSONObject.fromObject(authorName);
		books =  bookService.queryBookByAuthorName(obj.get("authorName").toString());
		return books; 	
	}
	
	@PostMapping(value = "/queryUser/queryBookByBookName")
	@ResponseBody
	public List<Book> queryBookByBookName(@RequestBody String bookName) {
		JSONObject obj = JSONObject.fromObject(bookName);
		books =  bookService.queryBookByBookName(obj.get("bookName").toString());
		return books;
	}
	
	@PostMapping(value = "/queryUser/queryBookByEducationName")
	@ResponseBody
	public List<Book> queryBookByEducationName(@RequestBody String educationName) {
		JSONObject obj = JSONObject.fromObject(educationName);
		books =  bookService.queryBookByEducationName(obj.get("educationName").toString());
		return books;
	}
	//点击借阅更新书的数目
	@PostMapping(value = "/queryUser/lent")
	@ResponseBody
	public boolean lent(@RequestBody String lent) {
		JSONObject obj = JSONObject.fromObject(lent);
		Timestamp time= new Timestamp(System.currentTimeMillis());//获取系统当前时间   
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		String timeStr = df.format(time);   
		time = Timestamp.valueOf(timeStr);   
		Rental rental = new Rental();
		rental.setUserId(obj.get("userId").toString());
		rental.setBookId(obj.get("bookId").toString());
		rental.setRentalDatetime(time);
		boolean stat =  bookService.updateBookCount(rental);
		return stat;
	}
}
