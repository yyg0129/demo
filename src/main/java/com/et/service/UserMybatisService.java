package com.et.service;

import java.util.List;
import java.util.Map;

import com.et.model.User;

public interface UserMybatisService {

	/**
	 * 查询方法
	 * 
	 * @return
	 */
	List<Map> selectAllUser(Integer page, Integer limit, Map map);

	/**
	 * 查询总记录数方法
	 * 
	 * @return
	 */
	int selectAllUserCount(Map map);

	/**
	 * 删除用户方法
	 * 
	 * @param id
	 */
	void deleteUser(Integer id);

	/**
	 * 添加用户方法
	 * 
	 * @param user
	 */
	void addUser(User user);

	Map detailUser(int id);
}
