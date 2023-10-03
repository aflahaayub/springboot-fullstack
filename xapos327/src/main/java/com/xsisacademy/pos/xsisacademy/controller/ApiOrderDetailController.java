package com.xsisacademy.pos.xsisacademy.controller;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xsisacademy.pos.xsisacademy.model.OrderDetails;
import com.xsisacademy.pos.xsisacademy.repository.OrderDetailRepository;

@RestController
@RequestMapping("/api/transaction")
public class ApiOrderDetailController {
	@Autowired
	private OrderDetailRepository orderDetailRepo;
	
	
	@PostMapping("/orderdetail/add")
	public ResponseEntity<OrderDetails> addOrderDetails(@RequestBody OrderDetails orderDetail){
		orderDetail.setActive(true);
		orderDetail.setCreateBy("admin1");
		orderDetail.setCreateDate(LocalDateTime.now());
		
		OrderDetails datas = this.orderDetailRepo.save(orderDetail);
		
		if(datas.equals(orderDetail)) {
			return new ResponseEntity<>(datas, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/orderDetailByHeaderId/{headerId}")
	public ResponseEntity<List<OrderDetails>> getHeaderIdForOrderDetail(@PathVariable("headerId") Long id){
		try {
			List<OrderDetails> datas = this.orderDetailRepo.findOrderByHeaderId(id);
			return new ResponseEntity<>(datas, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/orderdetail/getAllData")
	public ResponseEntity<List<OrderDetails>> getAllData(){
		try {
			List<OrderDetails> datas = this.orderDetailRepo.findAllOrdered();
			return new ResponseEntity<>(datas, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
}
