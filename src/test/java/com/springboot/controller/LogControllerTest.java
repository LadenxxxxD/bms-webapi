package com.springboot.controller;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.springboot.entity.BookLog;
import com.springboot.entity.LoginLog;
import com.springboot.entity.ReturnBook;
import com.springboot.service.LogService;
import com.springboot.service.ReturnBookService;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
import mockit.Verifications;

public class LogControllerTest {

	@Tested
	LogController logController;

	@Injectable
	LogService logService;

	@Test
	public void testGetBookLog01() {
		// 执行
		List<BookLog> result = logController.getBookLog(null);
		// 断言
		assertEquals(0, result.size());
	}
	
	@Test
	public void testGetBookLog02() {
		new Expectations() {
			{
				BookLog bookLog1=new BookLog();
				BookLog bookLog2=new BookLog();
				
				List<BookLog> bookLogs=new ArrayList<BookLog>();
				bookLogs.add(bookLog1);
				bookLogs.add(bookLog2);
				
				logService.getBookLog();
				result = bookLogs;
			}
		};
		// 执行
		List<BookLog> result = logController.getBookLog(null);
		// 断言
		assertEquals(2, result.size());
		
	}

	@Test
	public void testGetLoginLog01() {
		// 执行
		List<LoginLog> result = logController.getLoginLog(null);
		// 断言
		assertEquals(0, result.size());
	}
	@Test
	public void testGetLoginLog02() {
		
		new Expectations() {
			{
				LoginLog loginLog1=new LoginLog();
				LoginLog loginLog2=new LoginLog();
				
				List<LoginLog> loginLogs=new ArrayList<LoginLog>();
				loginLogs.add(loginLog1);
				loginLogs.add(loginLog2);
				
				logService.getLoginLog();
				result = loginLogs;
			}
		};
		
		// 执行
		List<LoginLog> result = logController.getLoginLog(null);
		// 断言
		assertEquals(2, result.size());
	}

}
