package com.nikhilsnayak3473.technoblog.service;

import com.nikhilsnayak3473.technoblog.dto.PostDto;
import com.nikhilsnayak3473.technoblog.dto.PostResponse;

public interface PostService {
	PostDto createPost(PostDto postDto);

	PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

	PostDto getPostById(long id);

	PostDto updatePost(PostDto postDto, long id);

	void deletePostById(long id);
}
