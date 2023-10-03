package com.xsisacademy.pos.xsisacademy.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.xsisacademy.pos.xsisacademy.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	// Long berasal dari data type id di model database
	
//	make our own function to get filtered data from apicategoryControlle
	// from native database
//	@Query(value = "SELECT * FROM CATEGORY WHERE is_active = true ORDER BY category_name", nativeQuery=true)
//	List<Category> findByCategories();
	
	@Query(value="SELECT c FROM Category c WHERE c.isActive = true") // from java class model Category
	// @Query(value="SELECT c FROM Category c ")
	List<Category> findByCategories();
	
	//PAGING
	@Query(value="SELECT * FROM Category WHERE lower(category_name) LIKE lower(concat('%', ?1, '%')) and is_active = ?2 ORDER BY category_name asc ", nativeQuery=true)
	Page<Category> findByIsActive(String keyword, Boolean isActive, Pageable page);
	
	@Query(value="SELECT * FROM Category WHERE lower(category_name) LIKE lower(concat('%', ?1, '%')) and is_active = ?2 ORDER BY category_name desc ", nativeQuery=true)
	Page<Category> findByIsActiveDesc(String keyword, Boolean isActive, Pageable page);
	
	@Query(value="SELECT * FROM category WHERE is_active = true AND category_name = ?1 LIMIT 1", nativeQuery=true)
	Category findByIdName(String categoryName);
	
	@Query(value="SELECT * FROM category WHERE is_active = true AND category_name = ?1 AND id != ?2", nativeQuery=true)
	Category findByIdNameForEdit(String categoryName, Long id);
	
}
