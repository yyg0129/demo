package com.et.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;

import com.et.model.User;

/**
 * 使用注解写sql语句
 * 
 * @author 杨勇国
 *
 * @version 1.0
 */
@Mapper
public interface UserNoteMapper {

	/**
	 * 提供动态sql的内部类
	 * 
	 * @author 杨勇国
	 *
	 * @version 1.0
	 */
	static class SqlProvider {
		public String getSelectAllUserSql(Map map) {
			Map m = (Map) map.get("user");
			Object userName = m.get("userName");
			Integer startIndex = (Integer) map.get("startIndex");
			Integer limit = (Integer) map.get("limit");
			SQL sql = new SQL();
			sql = sql.SELECT("*").FROM("user_2");
			if (userName != null && !"".equals(userName)) {
				sql.WHERE("u_name like '%" + userName + "%'");
			}
			String sqlStr = sql.toString() + " limit " + startIndex + "," + limit;
			System.out.println(sqlStr);
			return sqlStr;
		}

		public String getSelectAllUserCount(Map map) {
			Map map2 = (Map) map.get("user");
			Object userName = map2.get("userName");
			SQL sql = new SQL();
			sql = sql.SELECT("count(*)").FROM("user_2");
			if (userName != null && !"".equals(userName)) {
				sql.WHERE("u_name like '%" + userName + "%'");
			}
			System.out.println(sql.toString());
			return sql.toString();
		}

		public String getDeleteUser(Map map) {
			int id = (int) map.get("id");
			SQL sql = new SQL();
			sql = sql.DELETE_FROM("user_2").WHERE(" id =" + id);
			return sql.toString();
		}

		public String getAddUser(Map map) {
			User user = (User) map.get("user");
			SQL sql = new SQL();
			sql = sql.INSERT_INTO("user_2");
			sql.VALUES("u_name", "'" + user.getName() + "'");
			sql.VALUES("psw", "'" + user.getPassword() + "'");
			System.out.println(sql.toString());
			return sql.toString();
		}

	}

	// @Select("select * from user_2 limit ${startIndex},${limit}")
	@SelectProvider(type = SqlProvider.class, method = "getSelectAllUserSql")
	public List<Map> selectAllUser(@Param("startIndex") Integer startIndex, @Param("limit") Integer limit,
			@Param("user") Map map);

	// @Select("select count(*) from user_2")
	@SelectProvider(type = SqlProvider.class, method = "getSelectAllUserCount")
	public int selectAllUserCount(@Param("user") Map map);

	/**
	 * 删除用户方法
	 * 
	 * @param id
	 */
	@SelectProvider(type = SqlProvider.class, method = "getDeleteUser")
	public void deleteUser(@Param("id") Integer id);

	/**
	 * 添加用户方法
	 * 
	 * @param user
	 */
	@SelectProvider(type = SqlProvider.class, method = "getAddUser")
	// @Insert("insert into user_2 values(#{name},#{password})")
	public void addUser(@Param("user") User user);

	@Select("select * from user_2 where id=${id}")
	public Map detailUser(@Param("id") int id);
}
