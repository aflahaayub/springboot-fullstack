package com.xsisacademy.pos.xsisacademy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.xsisacademy.pos.xsisacademy.model.Variant;

public interface VariantRepository extends JpaRepository<Variant, Long> {
	
//	@Query(value="select v from Variant v")
//	List<Variant> findAllDataVariant();
	
	// menggunakan JPArepo
	 List<Variant> findByIsActive(Boolean isActive);
	 
	 @Query(value="select v from Variant v where v.isActive = true and v.categoryId = ?1")
	 List<Variant> findByCategoryId(Long categoryId);
}
