package com.springboot.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.entity.BookLog;
import com.springboot.entity.LoginLog;
import com.springboot.service.LogService;

@CrossOrigin(origins = { "http://localhost:4200", "null" })
@RestController
public class LogController {

	@Autowired
	private LogService logService;

	@PostMapping("/getBookLog")
	@ResponseBody
	public List<BookLog> getBookLog(HttpServletRequest request) {

		List<BookLog> result = this.logService.getBookLog();

		return result;
	}

	@PostMapping("/getLoginLog")
	@ResponseBody
	public List<LoginLog> getLoginLog(HttpServletRequest request) {

		List<LoginLog> result = this.logService.getLoginLog();

		return result;
	}
}
