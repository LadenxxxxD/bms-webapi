package com.springboot.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Claims;
import com.springboot.util.JWTUtil;

@CrossOrigin(origins = { "http://localhost:4200", "null" })
@RestController
public class CheckToken {
	@PostMapping("/checkToken")
	@ResponseBody
	public boolean checkToken(HttpServletRequest request) {
		String token = request.getParameter("token");
//		System.out.println("checkToken:"+token);
		Claims parseToken = JWTUtil.parseToken(token, "MDk4ZjZiY2Q0NjIxZDM3M2NhZGU0ZTgzMjYyN2I0ZjY=");
//		System.out.println("是否过期claims："+parseToken);
		//token过期
		if(parseToken == null) {
			return false;
		}
		//token未过期
		return true;
	}

}
