package com.springboot.service.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.springboot.dao.AnotationUserDao;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;

public class UserServiceImplTest {
	@Tested
	UserServiceImpl userService;

	@Injectable
	AnotationUserDao user;

	
	@Test
	public void testGetUserId01() {
		
		
		// 执行
		int result = userService.getUserId("user1");
		// 断言
		assertEquals(01, result);
	}
	@Test
	public void testGetUserId02() {
		new Expectations() {
			{
				userService.getUserId("user2");
				result = true;
			}
			

		};
		// 执行
		int result = userService.getUserId("user2");
		// 断言
		assertEquals(02, result);
	}

}
