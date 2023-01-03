package com.nikhilsnayak3473.technoblog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.nikhilsnayak3473.technoblog.dto.PostDto;
import com.nikhilsnayak3473.technoblog.dto.PostResponse;
import com.nikhilsnayak3473.technoblog.entity.Category;
import com.nikhilsnayak3473.technoblog.entity.Post;
import com.nikhilsnayak3473.technoblog.exception.ResourceNotFoundException;
import com.nikhilsnayak3473.technoblog.repository.CategoryRepository;
import com.nikhilsnayak3473.technoblog.repository.PostRepository;
import com.nikhilsnayak3473.technoblog.service.PostService;

@Service
public class PostServiceImpl implements PostService {

	private PostRepository postRepository;

	private CategoryRepository categoryRepository;

	private ModelMapper mapper;

	public PostServiceImpl(PostRepository postRepository, CategoryRepository categoryRepository, ModelMapper mapper) {
		this.postRepository = postRepository;
		this.categoryRepository = categoryRepository;
		this.mapper = mapper;
	}

	@Override
	public PostDto createPost(PostDto postDto) {

		Post post = mapToEntity(postDto);
		Category category = categoryRepository.findById(postDto.getCategoryId())
				.orElseThrow(() -> new ResourceNotFoundException("Category", "id", postDto.getCategoryId()));
		post.setCategory(category);
		Post newPost = postRepository.save(post);

		PostDto postResponse = mapToDTO(newPost);
		return postResponse;
	}

	@Override
	public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {

		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();

		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

		Page<Post> posts = postRepository.findAll(pageable);

		List<Post> listOfPosts = posts.getContent();

		List<PostDto> content = listOfPosts.stream().map(post -> mapToDTO(post)).collect(Collectors.toList());

		PostResponse postResponse = new PostResponse();
		postResponse.setContent(content);
		postResponse.setPageNo(posts.getNumber());
		postResponse.setPageSize(posts.getSize());
		postResponse.setTotalElements(posts.getTotalElements());
		postResponse.setTotalPages(posts.getTotalPages());
		postResponse.setLast(posts.isLast());

		return postResponse;
	}

	@Override
	public PostDto getPostById(long id) {
		Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
		return mapToDTO(post);
	}

	@Override
	public PostDto updatePost(PostDto postDto, long id) {
		Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
		Category category = categoryRepository.findById(postDto.getCategoryId())
				.orElseThrow(() -> new ResourceNotFoundException("Category", "id", postDto.getCategoryId()));
		post.setTitle(postDto.getTitle());
		post.setDescription(postDto.getDescription());
		post.setContent(postDto.getContent());
		post.setCategory(category);

		Post updatedPost = postRepository.save(post);
		return mapToDTO(updatedPost);
	}

	@Override
	public void deletePostById(long id) {
		Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
		postRepository.delete(post);
	}

	private PostDto mapToDTO(Post post) {
		PostDto postDto = mapper.map(post, PostDto.class);
		return postDto;
	}

	private Post mapToEntity(PostDto postDto) {
		Post post = mapper.map(postDto, Post.class);
		return post;
	}

	@Override
	public List<PostDto> getPostsByCategory(Long categoryId) {
		categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));

		List<Post> posts = postRepository.findByCategoryId(categoryId);
		return posts.stream().map(post -> mapToDTO(post)).collect(Collectors.toList());
	}
}
