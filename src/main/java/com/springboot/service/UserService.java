package com.springboot.service;

import com.springboot.entity.User;

public interface UserService {
	public boolean checkPassword(String name, String password);

	public User getUser(String userId);

	public boolean addUser(User user);
	// public List<User> getAll();
}
