package com.springboot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.dao.ReturnBookDao;
import com.springboot.entity.BookLog;
import com.springboot.service.ReturnBookService;

@Service
public class ReturnBookServiceImpl implements ReturnBookService {

	@Autowired
	private ReturnBookDao rbDao;

	@Override
	public boolean returnBookCheck(String userId, String bookId) {
		List<BookLog> bookLogs=rbDao.getReturnBook(userId, bookId);
		
		for(int i=0;i<bookLogs.size();i++) {
			
			if(bookLogs.get(i).getReturnDatetime()==null) {
				if (rbDao.ReturnBook(userId, bookId)!=0) {
					return true;
				} else {
					return false;
				}
			}
		}
		return false;
	}

}
