package com.todo.sign.controller;

import lombok.Data;

@Data
public class SignInRes {
	private String username;
	private String token;
	
	protected SignInRes() {
		
	}
	
	public SignInRes(String username, String token) {
		super();
		this.username = username;
		this.token = token;
	}


	
}
