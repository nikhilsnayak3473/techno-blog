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

	public BlogAPIException(String message, HttpStatus status, String message1) {
		super(message);
		this.status = status;
		this.message = message1;
	}

	public HttpStatus getStatus() {
		return status;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
