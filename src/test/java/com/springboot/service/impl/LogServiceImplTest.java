package com.springboot.service.impl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.springboot.dao.LogDao;
import com.springboot.entity.BookLog;
import com.springboot.entity.LoginLog;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;

public class LogServiceImplTest {

	@Tested
	private LogServiceImpl logServiceImpl;
	@Injectable
	private LogDao lDao;
	@Test
	public void testGetBookLog01() {
		// 执行
		List<BookLog> result = logServiceImpl.getBookLog();
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
				
				lDao.getBookLog();
				result = bookLogs;
			}
		};
		// 执行
		List<BookLog> result = logServiceImpl.getBookLog();
		// 断言
		assertEquals(2, result.size());
	}

	@Test
	public void testGetLoginLog01() {
		// 执行
		List<LoginLog> result = logServiceImpl.getLoginLog();
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
				
				lDao.getLoginLog();
				result = loginLogs;
			}
		};
		// 执行
		List<LoginLog> result = logServiceImpl.getLoginLog();
		// 断言
		assertEquals(2, result.size());
	}

}
