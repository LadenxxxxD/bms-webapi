package com.springboot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.springboot.dao.RankListDao;
import com.springboot.entity.BookRankList;
import com.springboot.entity.UserRankList;
import com.springboot.service.RankListService;

@Service
public class RankListServiceImpl implements RankListService {

	@Autowired
	RankListDao ranklistDao;

	@Override
	public List<UserRankList> getUserRankList() {
		List<UserRankList> userRankList = ranklistDao.getUserRankList();
		return userRankList;
	}

	@Override
	public List<BookRankList> getBookRankList() {
		List<BookRankList> bookRankList = ranklistDao.getBookRankList();
		return bookRankList;
	}

}
