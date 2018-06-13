package com.et.contorller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.et.model.ResponseEnt;
import com.et.model.User;
import com.et.service.UserMybatisService;

@RestController
public class UserMybatisNoteController {

	@Autowired
	UserMybatisService userMybatisService;

	/**
	 * 查询方法
	 * 
	 * @param page
	 * @param limit
	 * @param userName
	 * @return
	 */
	@GetMapping("/NoteQueryUser")
	public ResponseEnt selectAllUser(Integer page, Integer limit, String userName) {
		Map map = new HashMap<>();
		map.put("userName", userName);

		List<Map> list = userMybatisService.selectAllUser(page, limit, map);
		ResponseEnt re = new ResponseEnt();
		re.setData(list);
		re.setCount(userMybatisService.selectAllUserCount(map));
		return re;
	}

	/**
	 * 删除方法
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/user/deleteUser")
	public String deleteUser(Integer id) {
		try {
			userMybatisService.deleteUser(id);
			return "1";
		} catch (Exception e) {
			// TODO: handle exception
			return "0";
		}

	}

	@GetMapping("/user/addUser")
	public String addUser(@Valid User user) {

		try {
			userMybatisService.addUser(user);
			return "1";
		} catch (Exception e) {
			// TODO: handle exception
			return "0";
		}

	}

	@GetMapping("/user/detailUser")
	public Map detailUser(int id) {
		return userMybatisService.detailUser(id);
	}
}
