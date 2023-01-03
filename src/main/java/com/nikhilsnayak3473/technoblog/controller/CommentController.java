package com.nikhilsnayak3473.technoblog.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nikhilsnayak3473.technoblog.dto.CommentDto;
import com.nikhilsnayak3473.technoblog.service.CommentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/posts/{postId}")
public class CommentController {

	private CommentService commentService;

	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}

	@PostMapping("/comments")
	public ResponseEntity<CommentDto> createComment(@PathVariable(value = "postId") long postId,
			@Valid @RequestBody CommentDto commentDto) {
		return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);
	}

	@GetMapping("/comments")
	public List<CommentDto> getCommentsByPostId(@PathVariable(value = "postId") Long postId) {
		return commentService.getCommentsByPostId(postId);
	}

	@GetMapping("/comments/{id}")
	public ResponseEntity<CommentDto> getCommentById(@PathVariable(value = "postId") Long postId,
			@PathVariable(value = "id") Long commentId) {
		CommentDto commentDto = commentService.getCommentById(postId, commentId);
		return new ResponseEntity<>(commentDto, HttpStatus.OK);
	}

	@PutMapping("/comments/{id}")
	public ResponseEntity<CommentDto> updateComment(@PathVariable(value = "postId") Long postId,
			@PathVariable(value = "id") Long commentId, @Valid @RequestBody CommentDto commentDto) {
		CommentDto updatedComment = commentService.updateComment(postId, commentId, commentDto);
		return new ResponseEntity<>(updatedComment, HttpStatus.OK);
	}

	@DeleteMapping("/comments/{id}")
	public ResponseEntity<String> deleteComment(@PathVariable(value = "postId") Long postId,
			@PathVariable(value = "id") Long commentId) {
		commentService.deleteComment(postId, commentId);
		return new ResponseEntity<>("Comment deleted successfully", HttpStatus.OK);
	}
}
