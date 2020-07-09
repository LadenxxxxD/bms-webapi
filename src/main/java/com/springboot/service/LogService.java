package com.springboot.service;

import java.util.List;

import com.springboot.entity.BookLog;
import com.springboot.entity.LoginLog;

public interface LogService {

	List<BookLog> getBookLog();
	List<LoginLog> getLoginLog();
}
