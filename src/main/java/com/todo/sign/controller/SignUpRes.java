package com.todo.sign.controller;

import lombok.Data;

@Data
public class SignUpRes {
	private String username;
	private String token;
	
	protected SignUpRes() {
		
	}
	
	public SignUpRes(String username, String token) {
		super();
		this.username = username;
		this.token = token;
	}


}
