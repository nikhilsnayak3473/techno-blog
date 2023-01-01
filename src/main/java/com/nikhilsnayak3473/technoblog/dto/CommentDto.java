package com.nikhilsnayak3473.technoblog.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CommentDto {
	
	private long id;
	
	@NotEmpty(message = "Name should not be null or empty")
	private String name;

	@NotEmpty(message = "Email should not be null or empty")
	@Email
	private String email;

	@NotEmpty
	@Size(min = 10, message = "Comment body must be minimum 10 characters")
	private String body;
}
