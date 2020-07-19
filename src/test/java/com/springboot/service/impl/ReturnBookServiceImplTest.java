package com.springboot.service.impl;

import static org.junit.Assert.*;

import org.junit.Test;

import com.springboot.controller.ReturnBookController;
import com.springboot.dao.ReturnBookDao;
import com.springboot.entity.ReturnBook;
import com.springboot.service.ReturnBookService;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;

public class ReturnBookServiceImplTest {

	@Tested
	ReturnBookServiceImpl returnBookServiceImpl;

	@Injectable
	ReturnBookDao rbDao;

	
	@Test
	public void testReturnBookCheck01() {
		// 执行
		boolean result = returnBookServiceImpl.returnBookCheck("userId","bookId");
		// 断言
		assertEquals(false, result);
	}
	@Test
	public void testReturnBookCheck02() {
		new Expectations() {
			{
				rbDao.ReturnBook("userId","bookId");
				result = true;
			}
			

		};
		// 执行
		boolean result = returnBookServiceImpl.returnBookCheck("userId","bookId");
		// 断言
		assertEquals(true, result);
	}

}
