package com.xsisacademy.pos.xsisacademy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.xsisacademy.pos.xsisacademy.model.OrderHeader;

public interface OrderHeaderRepository extends JpaRepository<OrderHeader, Long> {
//	@Query(value="select o from OrderHeader o") //java class
//	public Long findByMaxId();
	
	@Query(value="select max(id) from orderheaders", nativeQuery= true)
	public Long findByMaxId();
}
