package com.xsisacademy.pos.xsisacademy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.xsisacademy.pos.xsisacademy.model.Products;

public interface ProductRepository extends JpaRepository<Products, Long> {
	// get Products using its class in Model
	//@Query(value="select p from Products p where p.isActive = ?1 and p.productInitial like '%?2' order by p.productName ") // 1 and 2 is from the order of the parameter
	//List<Products> findByisActive(Boolean isActive, String productInitial);
	
	@Query(value="select p from Products p where p.isActive = true order by p.id")
	 List<Products> findByisActive(Boolean isActive);
}
