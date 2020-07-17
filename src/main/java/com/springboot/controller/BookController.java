package com.springboot.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.springboot.entity.Book;
import com.springboot.entity.BookRankList;
import com.springboot.entity.Rental;
import com.springboot.entity.UploadedFile;
import com.springboot.entity.UserRankList;
import com.springboot.service.BookService;
import com.springboot.service.RankListService;

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
import org.springframework.web.multipart.MultipartFile;

import net.sf.json.JSONObject;

@CrossOrigin(origins = { "http://localhost:4200", "null" })
@RequestMapping("/api/books")
@RestController
public class BookController {

	@Autowired
	private BookService bookService;
	@Autowired
	private RankListService ranklistService;

	private List<UserRankList> userRankList = new ArrayList<UserRankList>();
	private List<BookRankList> bookRankList = new ArrayList<BookRankList>();

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
	public boolean lent(@RequestBody Rental rental) {
		System.out.println(rental.getUserId());
		System.out.println(rental.getBookId());
		Timestamp time = new Timestamp(System.currentTimeMillis()); // 获取系统当前时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String timeStr = df.format(time);
		time = Timestamp.valueOf(timeStr);
		rental.setRentalDatetime(time);
		System.out.println(rental.getRentalDatetime());
		boolean stat = bookService.updateBookCount(rental);
		return stat;
	}

	// 编辑图书
	@PutMapping(value = "/updateBook")
	@ResponseBody
	public boolean updateBook(@RequestBody Book book) {
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
		return bookService.addBook(book);
	}

	// TODO 上传图片
	// 前端需要的Json样例：
	// {
	// "name": "xxx.png",
	// "status": "done",
	// "url":"https://zos.alipayobjects.com/rmsportal/jkjgkEfvpUPVyRjUImniVslZfWPnJuuZ.png",
	// "thumbUrl":"https://zos.alipayobjects.com/rmsportal/jkjgkEfvpUPVyRjUImniVslZfWPnJuuZ.png"
	// }
	@PostMapping(value = "/uploadBookImg")
	@ResponseBody
	public UploadedFile uploadBookImg(@RequestParam("file") MultipartFile file) {

		if (file == null) {
			System.out.println("没有读取到文件");
			return new UploadedFile("", "failed", "");
		} else {
			return this.bookService.uploadBookImg(file);
		}
	}

	// 查询当月读者排行榜
	@PostMapping(value = "/queryRankingList/queryUserRL")
	@ResponseBody
	public List<UserRankList> queryUserRL(HttpServletRequest request) {
		userRankList = ranklistService.getUserRankList();
		return userRankList;
	}

	// 查询当月热门图书
	@PostMapping(value = "/queryRankingList/queryBookRL")
	@ResponseBody
	public List<BookRankList> queryBookRL(HttpServletRequest request) {
		bookRankList = ranklistService.getBookRankList();
		return bookRankList;
	}

}
