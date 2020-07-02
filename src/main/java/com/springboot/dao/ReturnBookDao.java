package com.springboot.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ReturnBookDao {
	boolean ReturnBook(@Param("userId") String userId, @Param("bookId") String bookId);
}
