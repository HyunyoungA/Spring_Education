package com.kh.spring.board.model.vo;

import java.sql.Date;

public class Board {
	private String CUST_NO;
	private String CUST_NM;
	
	public Board() {}

	public Board(String CUST_NO, String CUST_NM) {
		super();
		CUST_NO = CUST_NO;
		CUST_NM = CUST_NM;
	}

	public String getCUST_NO() {
		return CUST_NO;
	}

	public void setCUST_NO(String cUST_NO) {
		CUST_NO = cUST_NO;
	}

	public String getCUST_NM() {
		return CUST_NM;
	}

	public void setCUST_NM(String cUST_NM) {
		CUST_NM = cUST_NM;
	}

	@Override
	public String toString() {
		return "Board [CUST_NO=" + CUST_NO + ", CUST_NM=" + CUST_NM + "]";
	}

	
	
}
