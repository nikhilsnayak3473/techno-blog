package com.nikhilsnayak3473.technoblog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.nikhilsnayak3473.technoblog.dto.CategoryDto;
import com.nikhilsnayak3473.technoblog.entity.Category;
import com.nikhilsnayak3473.technoblog.exception.ResourceNotFoundException;
import com.nikhilsnayak3473.technoblog.repository.CategoryRepository;
import com.nikhilsnayak3473.technoblog.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	private CategoryRepository categoryRepository;
	private ModelMapper modelMapper;

	public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
		super();
		this.categoryRepository = categoryRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public CategoryDto addCategory(CategoryDto categoryDto) {
		Category category = modelMapper.map(categoryDto, Category.class);
		Category savedCategory = categoryRepository.save(category);
		return modelMapper.map(savedCategory, CategoryDto.class);
	}

	@Override
	public CategoryDto getCategory(Long id) {
		Category category = categoryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
		return modelMapper.map(category, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategories() {
		List<Category> categories = categoryRepository.findAll();

		return categories.stream().map(category -> modelMapper.map(category, CategoryDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public CategoryDto updateCategory(Long id, CategoryDto categoryDto) {
		Category category = categoryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
		category.setName(categoryDto.getName());
		category.setDescription(categoryDto.getDescription());
		Category updatedCategory = categoryRepository.save(category);
		return modelMapper.map(updatedCategory, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Long id) {
		Category category = categoryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
		categoryRepository.delete(category);
	}

}
