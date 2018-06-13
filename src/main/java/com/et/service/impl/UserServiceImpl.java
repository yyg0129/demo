package com.et.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.et.dao.UserDao;
import com.et.model.User;
import com.et.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userdao;

	@Override
	public Iterable<User> queryUserAll() {
		// TODO Auto-generated method stub
		Iterable<User> userList = userdao.findAll();
		return userList;
	}

	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		userdao.save(user);
	}

	@Override
	public void update(String name, String password) {
		// TODO Auto-generated method stub

	}

}
