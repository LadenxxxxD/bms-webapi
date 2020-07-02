package com.springboot.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.entity.ReturnBook;
import com.springboot.service.ReturnBookService;
import com.springboot.service.UserService;

@CrossOrigin(origins = { "http://localhost:4200", "null" })
@RestController
public class ReturnBookController {
	@Autowired
	private ReturnBookService rbService;

	@PostMapping("/returnbook")
	@ResponseBody
	public boolean login(@RequestBody ReturnBook returnBook,HttpServletRequest request) {

		boolean result = rbService.returnBookCheck(returnBook.getUserId(),returnBook.getBookId());
		if (result) {
			return result;
		}
		return false;
	}
}
