package com.springboot.service;

import java.util.List;
import java.util.Map;

import com.springboot.entity.BookRankList;
import com.springboot.entity.UserRankList;

public interface RankListService {
	public List<UserRankList> getUserRankList ();
	public List<BookRankList> getBookRankList ();
}
