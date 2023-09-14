package com.xsisacademy.pos.xsisacademy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xsisacademy.pos.xsisacademy.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	// Long berasal dari data type id di model database
}
