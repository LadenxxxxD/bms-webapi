package com.springboot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.dao.ReturnBookDao;
import com.springboot.service.ReturnBookService;

@Service
public class ReturnBookServiceImpl implements ReturnBookService {

	@Autowired
	private ReturnBookDao rbDao;

	@Override
	public boolean returnBookCheck(String userId, String bookId) {
		if (rbDao.ReturnBook(userId, bookId)) {
			return true;
		} else {
			return false;
		}
	}

}
