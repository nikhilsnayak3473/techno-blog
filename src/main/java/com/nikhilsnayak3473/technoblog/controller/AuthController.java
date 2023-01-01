package com.nikhilsnayak3473.technoblog.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nikhilsnayak3473.technoblog.dto.LoginDto;
import com.nikhilsnayak3473.technoblog.dto.RegisterDto;
import com.nikhilsnayak3473.technoblog.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private AuthService authService;

	public AuthController(AuthService authService) {
		super();
		this.authService = authService;
	}

	@PostMapping(value = { "/login", "/signin" })
	public ResponseEntity<String> login(@RequestBody LoginDto loginDto) {
		String response = authService.login(loginDto);
		return ResponseEntity.ok(response);
	}

	@PostMapping(value = { "/register", "/signup" })
	public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
		String response = authService.register(registerDto);
		return new ResponseEntity<String>(response, HttpStatus.CREATED);
	}

}
