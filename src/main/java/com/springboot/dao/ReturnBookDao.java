package com.springboot.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.springboot.entity.BookLog;
@Mapper
public interface ReturnBookDao {
	int ReturnBook(@Param("userId") String userId, @Param("bookId") String bookId);
	List<BookLog> getReturnBook(@Param("userId") String userId, @Param("bookId") String bookId);
}
