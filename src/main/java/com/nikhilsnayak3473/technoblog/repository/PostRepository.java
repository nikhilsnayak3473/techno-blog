package com.nikhilsnayak3473.technoblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nikhilsnayak3473.technoblog.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}
