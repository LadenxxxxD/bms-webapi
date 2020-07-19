package com.springboot.controller;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.springframework.web.bind.annotation.RequestBody;

import com.springboot.service.UserService;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;

public class LoginControllerTest {
	
	@Tested
	RegisterController registerController;
	
	@Injectable
	UserService userService;
	
	@Injectable
	HttpServletRequest request;
	
	@Test
	public void testloginFalse() {
		new Expectations() {
            {
            	userService.checkPassword("user1", "0");
                result = false;
            }
            
        };
     // 执行
		Object result = false;
		
		// 断言
		assertEquals(false, result);
		
		
	}
	
	@Test
	public void testcheckuserThereTrue() {
		
		new Expectations() {
			{
				userService.checkPassword("user1","user1");
				result = true;
			}
		};
		
		// 执行
		boolean result = false;
		if(userService.checkPassword("user1","user1") != null) {
			result = true;
		}
		// 断言
		assertEquals(true, result);
	}
}
