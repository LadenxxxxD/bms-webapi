package com.springboot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.dao.LogDao;
import com.springboot.entity.BookLog;
import com.springboot.entity.LoginLog;
import com.springboot.service.LogService;

@Service
public class LogServiceImpl implements LogService{

	@Autowired
	private LogDao lDao;
	@Override
	public List<BookLog> getBookLog() {
		// TODO Auto-generated method stub
		return lDao.getBookLog();
	}

	@Override
	public List<LoginLog> getLoginLog() {
		// TODO Auto-generated method stub
		return lDao.getLoginLog();
	}

}
