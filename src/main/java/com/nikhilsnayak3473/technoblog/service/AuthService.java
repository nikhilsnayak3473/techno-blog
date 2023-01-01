package com.nikhilsnayak3473.technoblog.service;

import com.nikhilsnayak3473.technoblog.dto.LoginDto;
import com.nikhilsnayak3473.technoblog.dto.RegisterDto;

public interface AuthService {
	
	String login(LoginDto loginDto);
	
	String register(RegisterDto registerDto);

}
