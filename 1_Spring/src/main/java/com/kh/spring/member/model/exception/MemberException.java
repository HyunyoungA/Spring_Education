package com.kh.spring.member.model.exception;

public class MemberException extends RuntimeException {//RuntimeException :예외처리안해줘도됨
	public MemberException() {}
	public MemberException(String msg) {
		super(msg);
	}
}
