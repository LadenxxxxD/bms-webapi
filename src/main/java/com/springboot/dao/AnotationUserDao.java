package com.springboot.dao;

import java.time.LocalDate;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.springboot.entity.User;

@Mapper
public interface AnotationUserDao {

	// /**
	// *
	// * @param userid
	// */
	// @Select("SELECT user_id,user_name,password,description FROM t_user where
	// user_id = #{userid}")
	// @Results({
	// @Result(property = "userid", column = "user_id"),
	// @Result(property = "username", column = "user_name"),
	// @Result(property = "password", column = "password"),
	// @Result(property = "description", column = "description"),
	// })
	User findById(@Param("userid") String userid);
	User findByUsername(@Param("userName") String userName);
	boolean registerLogById(@Param("user_id") String userid, @Param("user_name") String username,
			@Param("description") String description);

	boolean addUser(@Param("user_id") int userid, @Param("user_name") String username,
			@Param("password") String password, @Param("description") String description,
			@Param("authority") String authority, @Param("email") String email, @Param("birthday") LocalDate birthday,
			@Param("sex") String sex, @Param("grade") String grade, @Param("interest") String interest,
			@Param("comment") String comment);
	
	boolean registerLogById(@Param("user_id") int userId,
			@Param("register_datetime") String registerDatetime);

	// /**
	// *
	// * @param userid
	// */
	// @Select("SELECT user_id,user_name,password,description FROM t_user")
	// @Results({
	// @Result(property = "userid", column = "user_id"),
	// @Result(property = "username", column = "user_name"),
	// @Result(property = "password", column = "password"),
	// @Result(property = "description", column = "description"),
	// })
	// List<User> findAll();
}