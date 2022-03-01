package com.greedy.waterfall.common.exception.member;

public class LoginFailedException extends Exception {

	public LoginFailedException() {}
	
	public LoginFailedException(String msg) {
		super(msg);
	}
}
