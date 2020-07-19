package com.springboot.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.entity.User;
import com.springboot.service.UserService;
import com.springboot.util.HttpRequestUtil;

@CrossOrigin(origins = { "http://localhost:4200", "null" })
@RestController
public class RegisterController {

	@Autowired
	private UserService userService;

	@PostMapping(value = "/register")
	@ResponseBody
	public boolean register(HttpServletRequest request) {

		if (request == null) {
			return false;
		}
		HttpRequestUtil tool = new HttpRequestUtil();
		Map<String, String> params = new HashMap<>();
		params = tool.commonHttpRequestParamConvert(request);
		System.out.println(params);

		String username = params.get("user_name");
		User user = new User();
		user.setUsername(username);

		String password = params.get("password");
		user.setPassword(password);
		String email = params.get("email");
		user.setEmail(email);
		String birthday = params.get("birthday");
		LocalDate birthData = LocalDate.parse("2019-12-05", DateTimeFormatter.ofPattern("yyyy-MM-dd"));

		user.setBirthday(birthData);
		String sex = params.get("sex");
		user.setSex(sex);
		String interest = params.get("interest");
		user.setInterest(interest);
		String grade = params.get("grade");
		user.setGrade(grade);
		String comment = params.get("comment");
		user.setComment(comment);
		//// ������
		String authority = params.get("authority");
		;
		user.setAuthority(authority);
		String description = params.get("description");
		user.setDescription(description);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date date = new java.util.Date();
		String nowTime = sdf.format(date);
		// user.setRegisterTime(nowTime);
		userService.addUser(user);
		// ResponseResult result = new
		// ResponseResult(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getMsg(),user);
		// return user;
		return true;
	}

	@PostMapping(value = "/checkUserThere")
	@ResponseBody
	public boolean checkuserThere(HttpServletRequest request) {
		HttpRequestUtil tool = new HttpRequestUtil();
		Map<String, String> params = new HashMap<>();
		params = tool.commonHttpRequestParamConvert(request);
		System.out.println(params);
		String userName = params.get("userName");
		// System.out.println(userName);
		boolean result = userService.checkUserThere(userName);
		if (result) {
			return result;
		}
		return false;
	}

}
