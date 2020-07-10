package com.springboot.entity;

import java.sql.Timestamp;

public class Rental {
	private String userId;
	private String bookId;
	private Timestamp rentalDatetime;

	public void setRentalDatetime(Timestamp rentalDatetime) {
		this.rentalDatetime = rentalDatetime;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public Timestamp getRentalDatetime() {
		return rentalDatetime;
	}
}
