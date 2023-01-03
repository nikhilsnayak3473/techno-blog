package com.nikhilsnayak3473.technoblog.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nikhilsnayak3473.technoblog.dto.CategoryDto;
import com.nikhilsnayak3473.technoblog.service.CategoryService;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {
	private CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		super();
		this.categoryService = categoryService;
	}

	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<CategoryDto> addCategory(@RequestBody CategoryDto categoryDto) {
		CategoryDto savedCategory = categoryService.addCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(savedCategory, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<CategoryDto>> getCategories() {
		List<CategoryDto> allCategories = categoryService.getAllCategories();
		return ResponseEntity.ok(allCategories);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable("id") Long id) {
		CategoryDto category = categoryService.getCategory(id);
		return ResponseEntity.ok(category);
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<CategoryDto> updateCategory(@PathVariable("id") Long id,
			@RequestBody CategoryDto categoryDto) {
		CategoryDto category = categoryService.updateCategory(id, categoryDto);

		return ResponseEntity.ok(category);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> deleteCategory(@PathVariable("id") Long id) {
		categoryService.deleteCategory(id);
		return ResponseEntity.ok("Category Deleted Successfully");
	}

}
