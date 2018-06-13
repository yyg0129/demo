package com.et.contorller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.et.mapper.UserMapper;

@RestController
public class UserMybatisController {

	@Autowired
	UserMapper userMapper;

	@GetMapping("/mybatisQueryUser")
	public List<Map> userList() {
		return userMapper.selectAllUser();
	}
}
