package com.springboot.entity;

public class UserRankList {
	String userId;
	String userName;
	int countLentNum;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getCountLentNum() {
		return countLentNum;
	}
	public void setCountLentNum(int countLentNum) {
		this.countLentNum = countLentNum;
	}

}
