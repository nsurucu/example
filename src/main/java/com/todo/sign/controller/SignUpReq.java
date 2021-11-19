package com.todo.sign.controller;

import lombok.Data;

@Data
public class SignUpReq {

    private String username;
    

    private String password;
    
    protected SignUpReq() {
    	
    }

	public SignUpReq(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}


    
}
