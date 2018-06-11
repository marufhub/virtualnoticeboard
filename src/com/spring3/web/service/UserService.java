package com.spring3.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring3.web.dao.User;
import com.spring3.web.dao.UserDao;

@Service("userService")
public class UserService {
	
	private UserDao userDao;
	
	
	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	

	public void create(User user) {
		userDao.createUser(user);
		
		
	}



	public boolean exists(String username) {
		
		return userDao.exists(username);
	}



	
	public List<User> getAllUsers() {
		return userDao.getAllUsers();
		
	}

}
