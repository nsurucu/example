package com.todo.sign.controller;

import lombok.Data;

@Data
public class SignInReq {
    private String username;
    private String password;
    public SignInReq(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}


    
}
