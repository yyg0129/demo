package com.et.dao;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.et.model.User;

@Repository
public interface UserDao extends CrudRepository<User, Serializable> {

	/*
	 * @Query("update ") public void updateUser(@Param("id") int id, @Param("name")
	 * String name, @Param("password") String password);
	 */
}
