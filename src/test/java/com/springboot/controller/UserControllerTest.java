package com.springboot.controller;

import org.junit.Test;
  
import com.springboot.service.UserService;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;

public class UserControllerTest {

	@Tested
	LoginController userController;

	@Injectable
	private UserService anotationUserService;

	@Test
	public void testHello() {
		//准备
		new Expectations() {
            {
                // mock公用方法
            	anotationUserService.checkPassword("001", "001");
                result = false;
            }
        };
        //执行
       // userController.login();
        //断言
		//Assert.assertEquals("Hello World !!!!false", result);
	}
}
