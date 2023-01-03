package com.nikhilsnayak3473.technoblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nikhilsnayak3473.technoblog.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
