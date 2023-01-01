package com.nikhilsnayak3473.technoblog.service;

import java.util.List;

import com.nikhilsnayak3473.technoblog.dto.CommentDto;

public interface CommentService {
	CommentDto createComment(long postId, CommentDto commentDto);

	List<CommentDto> getCommentsByPostId(long postId);

	CommentDto getCommentById(Long postId, Long commentId);

	CommentDto updateComment(Long postId, long commentId, CommentDto commentRequest);

	void deleteComment(Long postId, Long commentId);
}
