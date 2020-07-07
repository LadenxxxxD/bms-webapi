package com.springboot.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.dao.BookDao;
import com.springboot.entity.Book;
import com.springboot.entity.Rental;
import com.springboot.entity.UploadedFile;
import com.springboot.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	BookDao bookDao;

	@Override
	public List<Book> queryBooks(String bookId, String authorName, String bookName, String educationName) {
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
			return bookDao.addBook(book) > 0;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

	@Override
	public boolean updateBook(Book book) {
		try {
			return bookDao.updateBook(book) > 0;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

	@Override
	public boolean deleteBook(String bookId) {
		return this.bookDao.deleteBook(bookId) > 0;
	}

	@Override
	public UploadedFile uploadBookImg(MultipartFile bookImg) {

		String filename = bookImg.getOriginalFilename();// 上传图片的本来名字
		String suffixName = filename.substring(filename.lastIndexOf(".")); // 图片后缀名
		filename = UUID.randomUUID().toString() + suffixName;// 图片重命名 防止重复
		// String filePath = System.getProperty("user.dir") +
		// "/src/main/resources/images/"; // src下的resources文件夹
		String filePath = "D:/bookImages/";

		try {
			String uploadDir = ResourceUtils.getURL("classpath:").getPath() + "images/"; // target下的resources文件夹
			System.out.println(uploadDir);
			File destFile = new File(filePath + filename);
			bookImg.transferTo(destFile);
			System.out.println(destFile.getAbsolutePath());
		} catch (IOException e) {
			System.err.println(e);
		}
		return new UploadedFile(bookImg.getOriginalFilename(), "done",
				"http://localhost:8080/" + bookImg.getOriginalFilename());
	}
}
