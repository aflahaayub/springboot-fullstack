package com.xsisacademy.pos.xsisacademy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.xsisacademy.pos.xsisacademy.model.OrderDetails;

public interface OrderDetailRepository extends JpaRepository<OrderDetails, Long> {
	List<OrderDetails> findOrderByHeaderId(Long id);
	
	@Query(value="select * from order_details order by header_id asc", nativeQuery=true)
	List<OrderDetails> findAllOrdered();
}
