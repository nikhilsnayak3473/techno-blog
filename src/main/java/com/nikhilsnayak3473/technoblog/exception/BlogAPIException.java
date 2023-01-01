package com.nikhilsnayak3473.technoblog.exception;

import org.springframework.http.HttpStatus;

public class BlogAPIException extends RuntimeException {

	private static final long serialVersionUID = -3109183660190020011L;
	private HttpStatus status;
	private String message;

	public BlogAPIException(HttpStatus status, String message) {
		this.status = status;
		this.message = message;
	}

	public HttpStatus getStatus() {
		return status;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
