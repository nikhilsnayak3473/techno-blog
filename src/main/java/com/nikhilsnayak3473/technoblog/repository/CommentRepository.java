package com.nikhilsnayak3473.technoblog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nikhilsnayak3473.technoblog.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
	List<Comment> findByPostId(long postId);
}
