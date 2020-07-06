package com.springboot.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.springboot.entity.BookRankList;
import com.springboot.entity.UserRankList;



@Mapper
public interface RankListDao {
	List<UserRankList> getUserRankList();
	List<BookRankList> getBookRankList();
}
