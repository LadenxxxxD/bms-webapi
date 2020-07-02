package com.springboot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.dao.AnotationUserDao;
import com.springboot.dao.ReturnBookDao;
import com.springboot.entity.User;
import com.springboot.service.ReturnBookService;

@Service
public class ReturnBookServiceImpl implements ReturnBookService{
	
	@Autowired
	private ReturnBookDao rbDao;
	@Autowired
	private AnotationUserDao userDao;
	
	@Override
	
	public boolean returnBookCheck(String userId, String bookId) {
		// TODO Auto-generated method stub
		User user = userDao.findById(userId);
		if (user!=null) {
			int rl=rbDao.ReturnBook(userId, bookId);
			System.out.println(rl);
			if(rl!=0) {
			   return true;
		    }else {
			   return false;
		    }
		}else {
			return false;
		}
		
		
		
	}

	

}
