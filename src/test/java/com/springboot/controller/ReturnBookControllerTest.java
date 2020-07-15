package com.springboot.controller;

import static org.junit.Assert.*;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;

import com.springboot.entity.ReturnBook;
import com.springboot.service.ReturnBookService;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;

public class ReturnBookControllerTest {

	@Tested
	ReturnBookController returnBookController;

	@Injectable
	ReturnBookService rbservice;

	@Injectable
	ReturnBook returnBook;
	
	@Test
	public void testReturnbook01() {
		// 执行
		boolean result = returnBookController.returnbook(returnBook);
		// 断言
		assertEquals(false, result);
	}
	@Test
	public void testReturnbook02() {
		
		new Expectations() {
			{
				rbservice.returnBookCheck(returnBook.getUserId(), returnBook.getBookId());
				result = true;
			}
			
		};

		// 执行
		boolean result = returnBookController.returnbook(returnBook);
		// 断言
		assertEquals(true, result);
	}

}
