package com.springboot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.dao.AnotationUserDao;
import com.springboot.entity.User;
import com.springboot.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private AnotationUserDao userDao;

	@Override
	public boolean checkPassword(String id, String password) {
		
		System.out.println("impl id:"+id);
		User user = userDao.findById(id);
		if (user != null && password.equals(user.getPassword())) {
			System.out.println(user.getAuthority());
			System.out.println(user.getComment());
			System.out.println(user.getDescription());
			System.out.println(user.getEmail());
			System.out.println(user.getGrade());
			System.out.println(user.getInterest());
			System.out.println(user.getPassword());
			System.out.println(user.getSex());
			System.out.println(user.getUserid());
			System.out.println(user.getUsername());
			System.out.println(user.getBirthday());
			System.out.println(user.getClass());
			return true;
		}
		return false;
	}

	@Override
	public User getUser(String userId) {
		User user = userDao.findById(userId);
		return user;
	}	

}
