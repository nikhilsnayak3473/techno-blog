package com.nikhilsnayak3473.technoblog.service;

import java.util.List;

import com.nikhilsnayak3473.technoblog.dto.PostDto;
import com.nikhilsnayak3473.technoblog.dto.PostResponse;

public interface PostService {
	PostDto createPost(PostDto postDto);

	PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

	PostDto getPostById(long id);

	PostDto updatePost(PostDto postDto, long id);
	
	List<PostDto> getPostsByCategory(Long categoryId);

	void deletePostById(long id);
}
