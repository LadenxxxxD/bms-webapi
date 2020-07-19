package com.springboot.controller;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;

import com.springboot.entity.User;
import com.springboot.service.UserService;
import com.springboot.util.HttpRequestUtil;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;

public class RegisterControllerTest {

	@Tested
	RegisterController registerController;

	@Injectable
	UserService uservice;

	@Injectable
	HttpServletRequest request;
	
	@Injectable
	HttpRequestUtil tool;
	
	@Injectable
	Map<String, String> params = new HashMap<>();
	
	// yzm :-ea
	// -javaagent:C:/Users/Administrator/.m2/repository/org/jmockit/jmockit/1.49/jmockit-1.49.jar

	@Test
	public void testRegisterFalse() {
		// 执行
		request = null;
		boolean result = registerController.register(request);
//		System.out.println(result);
		// 断言
		assertEquals(false, result);
	}

	@Test
	public void testRegisterTrue() {

		User user = new User();
		tool = new HttpRequestUtil();
		params = tool.commonHttpRequestParamConvert(request);
		user.setUsername("username");
		user.setPassword("password");
		user.setEmail("1.@qq.com");
		LocalDate birthData = LocalDate.parse("2019-12-05", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		user.setBirthday(birthData);
		user.setSex("man");
		user.setInterest("");
		user.setGrade("17级/其他/");

		user.setAuthority("admin");
		user.setDescription("");
//		System.out.println(user);
//		System.out.println(params);
		
		new Expectations() {
			{
				uservice.addUser(user);
				result = true;
			}

		};

		// 执行
		boolean result = registerController.register(request);
//		System.out.println(result);
		// 断言
		assertEquals(true,result);

	}

	@Test
	public void testcheckuserThereFalse() {
		// 执行
		request = null;
		boolean result = registerController.checkuserThere(request);
		// 断言
		assertEquals(false, result);
	}
	
	@Test
	public void testcheckuserThereTrue() {
		
		new Expectations() {
			{
				uservice.checkUserThere("user12222");
				result = true;
			}
		};
		
		// 执行
		request = null;
		boolean result = registerController.checkuserThere(request);
		System.out.println(result);
		// 断言
		assertEquals(false, result);
	}

}
