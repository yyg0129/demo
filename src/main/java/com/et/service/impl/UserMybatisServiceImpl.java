package com.et.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.et.mapper.UserNoteMapper;
import com.et.model.User;
import com.et.service.UserMybatisService;

@Service
public class UserMybatisServiceImpl implements UserMybatisService {

	@Autowired
	UserNoteMapper userNoteMapper;

	@Autowired
	StringRedisTemplate template;

	/**
	 * 查询方法
	 */
	@Override
	public List<Map> selectAllUser(Integer page, Integer limit, Map map) {
		Object userName = map.get("userName");
		if (userName == null) {
			userName = "";
			map.put("userName", userName);
		}

		int startIndex = (page - 1) * limit;
		// TODO Auto-generated method stub
		return userNoteMapper.selectAllUser(startIndex, limit, map);
	}

	/**
	 * 查询总记录数方法
	 */
	@Override
	public int selectAllUserCount(Map map) {
		Object userName = map.get("userName");
		if (userName == null) {
			userName = "";
			map.put("userName", userName);
		}
		// TODO Auto-generated method stub
		return userNoteMapper.selectAllUserCount(map);
	}

	@Override
	public void deleteUser(Integer id) {
		// TODO Auto-generated method stub
		userNoteMapper.deleteUser(id);
	}

	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		userNoteMapper.addUser(user);
	}

	@Override
	public Map detailUser(int id) {
		// TODO Auto-generated method stub
		// 判断redis是否存在该id对应的用户数据
		String userId = "user_2:" + id;
		Set<String> key = template.keys(userId);
		// 缓存存在的数据
		if (key.size() > 0) {
			Map map = template.boundHashOps(userId).entries();
			return map;
			// 不存在Redis的数据
		} else {
			// mysql查询数据
			Map map = userNoteMapper.detailUser(id);
			Object tempId = map.get("id");
			map.put("id", tempId + "");
			// 把查询出来的数据放到Redis
			template.boundHashOps(userId).putAll(map);
			return map;
		}
	}

}
