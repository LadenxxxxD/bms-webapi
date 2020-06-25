package com.springboot.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.springboot.entity.User;

@Mapper
public interface MyBatisXMLUserDao {

	User findById(@Param("userid") String userid);

}