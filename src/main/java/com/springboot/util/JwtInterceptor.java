package com.springboot.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import io.jsonwebtoken.Claims;

/**
 * ======================== token验证拦截器 Created with IntelliJ IDEA. User：pyy
 * Date：2019/7/18 9:46 Version: v1.0 ========================
 */
@CrossOrigin(origins = { "http://localhost:4200", "null" })
public class JwtInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 获取请求头信息authorization信息
		String a = request.getHeader("a");
//		System.out.println("aaaaaaaaaaaaaaaaaa:" + a);
		Claims claims = JWTUtil.parseToken(a, "MDk4ZjZiY2Q0NjIxZDM3M2NhZGU0ZTgzMjYyN2I0ZjY=");
		if (claims == null) {
			// 若判断是322则跳转至登录页面
			response.setStatus(322);
			return false;
		}
		return true;
	}

}
