package com.springboot.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.springboot.entity.BookLog;
import com.springboot.entity.LoginLog;

@Mapper
public interface LogDao {

	List<BookLog> getBookLog();
	List<LoginLog> getLoginLog();
}
