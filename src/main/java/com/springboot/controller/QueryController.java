package com.springboot.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

	// 用户自定义查询 可查图书id 作者 书名 出版社 查询所有图书
	@PostMapping(value = "/queryUser/queryBookByUser")
	@ResponseBody
	public List<Book> queryBooksByUser(@RequestBody String queryInfo) {
		JSONObject obj = JSONObject.fromObject(queryInfo);
		System.out.println(obj.get("bookId").toString());
		books = bookService.queryBookByUser(obj.get("bookId").toString(), obj.get("authorName").toString(), obj.get("bookName").toString(),
				obj.get("educationName").toString());
		return books;
	}

	// 点击借阅更新书的数目
	@PostMapping(value = "/queryUser/lent")
	@ResponseBody
	public boolean lent(@RequestBody String lent) {
		JSONObject obj = JSONObject.fromObject(lent);
		Timestamp time = new Timestamp(System.currentTimeMillis());// 获取系统当前时间
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

	// 上传图书 json格式的 用的时候记得改下url
	@PostMapping(value = "/queryUser/uploadBookInfo")
	@ResponseBody
	public boolean uploadBookInfo(@RequestBody String bookInfo) {
		JSONObject obj = JSONObject.fromObject(bookInfo);
		Book book = new Book();
		book.setAuthorName(obj.get("authorName").toString());
		book.setBookId(obj.get("bookId").toString());
		book.setBookImg(obj.get("bookImg").toString());
		book.setBookName(obj.get("bookName").toString());
		book.setEducationName(obj.get("educationName").toString());
		book.setQuantity((int) obj.get("quantity"));// 可能有数据类型的问题 你试试
		boolean stat = bookService.addBook(book);
		return stat;
	}

}
