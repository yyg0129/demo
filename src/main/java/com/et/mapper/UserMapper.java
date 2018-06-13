package com.et.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 用xml文件写sql语句
 * 
 * @author 杨勇国
 *
 * @version 1.0
 */
@Mapper
public interface UserMapper {

	public List<Map> selectAllUser();
}
