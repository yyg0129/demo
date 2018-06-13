package com.et.service;

import com.et.model.User;

public interface UserService {

	Iterable<User> queryUserAll();

	void addUser(User user);

	void update(String name, String password);
}
