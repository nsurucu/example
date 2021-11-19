package com.todo.sign.service;

import com.todo.sign.controller.SignInReq;
import com.todo.sign.controller.SignInRes;
import com.todo.sign.controller.SignUpReq;
import com.todo.sign.controller.SignUpRes;
import com.todo.sign.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.todo.sign.security.JwtTokenGenerator;
import com.todo.sign.entity.User;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
    PasswordEncoder passwordEncoder;
    
	@Autowired
    JwtTokenGenerator jwtTokenGenerator;
	
	public SignUpRes signup(SignUpReq signUpReq) {
		try {
			String username = signUpReq.getUsername();
	        String password = signUpReq.getPassword();
	        
	        User user =  userRepository.findByUsername(username);

	        User _user = new User(username, passwordEncoder.encode(password));
	        _user = userRepository.save(_user);
	        
	        String token = jwtTokenGenerator.createToken(_user.getUsername(), _user.getRoleAsList());
	        
			return new SignUpRes(username, token);
		} catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password");
        }
	}
	
	public SignInRes signin(SignInReq userSigninRequest) {
		try {
			String username = userSigninRequest.getUsername();
	        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, userSigninRequest.getPassword()));
	        String token = jwtTokenGenerator.createToken(username, this.userRepository.findByUsername(username).getRoleAsList());
	        
			return new SignInRes(username, token);
		} catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password");
        }
	}
}
