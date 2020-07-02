package com.springboot.service.impl;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.dao.AnotationUserDao;
import com.springboot.dao.MyBatisXMLUserDao;
import com.springboot.entity.User;
import com.springboot.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private AnotationUserDao userDao;
	@Autowired
	MyBatisXMLUserDao userMbDao;

	User user = new User();

	@Override
	public boolean checkPassword(String id, String password) {

		user = userDao.findById(id);
		if (user != null && password.equals(user.getPassword())) {
			return true;
		}
		return false;
	}

	@Override
	public User getUser(String userId) {
		user = userDao.findById(userId);
		return user;
	}

	@Override
	public boolean addUser(User user) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date date = new java.util.Date();
		String nowTime = sdf.format(date);
		boolean result = userDao.addUser(user.getUserid(), user.getUsername(), user.getPassword(),
				user.getDescription(), user.getAuthority(), user.getEmail(), user.getBirthday(), user.getSex(),
				user.getGrade(), user.getInterest(), user.getComment());
		if (result) {
			System.out.println("add User sucess------------------------------------------------------------------");
			return true;
		} else {
			System.out.println("add User fail------------------------------------------------------------------");
		}
		return false;
	}

}
