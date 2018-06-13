package com.et.contorller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.et.model.User;
import com.et.service.UserService;

@RestController
public class UserContorller {
	@Autowired
	// UserDao userdao;
	UserService userService;

	@RequestMapping("/query")
	public Iterable<User> queryUserAll() {
		Iterable<User> userList = userService.queryUserAll();
		return userList;
	}

	@RequestMapping("/hello")
	public String query() {
		return "hello";
	}

	@RequestMapping("/add")
	public void addUser(@Valid User user) {
		userService.addUser(user);
	}
}
