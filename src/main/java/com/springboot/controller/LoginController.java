package com.springboot.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.service.UserService;
import com.springboot.util.JWTUtil;

import io.jsonwebtoken.Claims;
import net.sf.json.JSONObject;

@CrossOrigin(origins = { "http://localhost:4200", "null" })
@RestController
public class LoginController {

	@Autowired
	private UserService service;

	@PostMapping(value = "/login", produces = "application/json;charset = UTF-8")
	@ResponseBody
	public String login(@RequestBody String body) {

		JSONObject obj = JSONObject.fromObject(body);
		String userName = obj.get("userName").toString();
		String password = obj.get("password").toString();
		String authority = service.checkPassword(userName, password);
		String token = "";
		if(authority != null) {
			int userId = service.getUserId(userName);
			// authority!=null说明用户名密码正确，可以生成token
			// if(authority != null) {
			// 根据userId和名字生成Token
			System.out.println("userId:" + userId);
			token = JWTUtil.createToken(userId, userName, 3);
			Claims a = JWTUtil.parseToken(token, "MDk4ZjZiY2Q0NjIxZDM3M2NhZGU0ZTgzMjYyN2I0ZjY=");
		}
		return "{\"authority\":\"" + authority + "\",\"token\":\"" + token + "\"}";
	}

	@PostMapping(value = "/test", produces = "application/json;charset = UTF-8")
	@ResponseBody
	public String test(@RequestBody String body, HttpServletRequest req) {
		JSONObject obj = JSONObject.fromObject(body);
		String token = obj.get("token").toString();
		System.out.println("token:" + token);
		Claims claims = JWTUtil.parseToken(token, "MDk4ZjZiY2Q0NjIxZDM3M2NhZGU0ZTgzMjYyN2I0ZjY=");
		return "";
	}
}
