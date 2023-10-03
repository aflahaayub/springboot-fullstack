package com.xsisacademy.pos.xsisacademy.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xsisacademy.pos.xsisacademy.model.OrderDetails;
import com.xsisacademy.pos.xsisacademy.model.OrderHeader;
import com.xsisacademy.pos.xsisacademy.model.Products;
import com.xsisacademy.pos.xsisacademy.repository.OrderDetailRepository;
import com.xsisacademy.pos.xsisacademy.repository.OrderHeaderRepository;
import com.xsisacademy.pos.xsisacademy.repository.ProductRepository;

@RestController
@RequestMapping("/api/transaction")
public class ApiOrderHeaderController {
	@Autowired
	private OrderHeaderRepository orderHeaderRepo;
	
	@Autowired
	private OrderDetailRepository orderDetailRepo;
	
	@Autowired
	private ProductRepository productRepo;
	
	///api/transaction/orderheader/create
	@PostMapping("/orderheader/create")
	public ResponseEntity<Object> createReference(){
		OrderHeader orderHeader = new OrderHeader();
		String timeDec = String.valueOf(System.currentTimeMillis());
		
		orderHeader.setReference(timeDec);
		orderHeader.setAmount((double) 0);
		orderHeader.setActive(true);
		orderHeader.setCreateBy("admin1");
		orderHeader.setCreateDate(new Date());
		
		OrderHeader datas = this.orderHeaderRepo.save(orderHeader);	
		
		if(datas.equals(orderHeader)) {
			return new ResponseEntity<>("Create Success", HttpStatus.OK);
		}else {
			return new ResponseEntity<>("Create Failed", HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/maxorderheaderid")
	public ResponseEntity<Long> getMaxOrderHeader(){
		try {
			Long maxId = this.orderHeaderRepo.findByMaxId();
			return new ResponseEntity<Long>(maxId, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<Long>(HttpStatus.NO_CONTENT);
		}
	}
	
	@PutMapping("/checkout/{headerId}/{totalAmount}")
	public ResponseEntity<Object> checkout(@PathVariable("headerId") Long id, @PathVariable("totalAmount") double totalAmount){
			System.out.println(totalAmount);		
			try{
				OrderHeader datas = this.orderHeaderRepo.findById(id).orElse(null);
				datas.setAmount(totalAmount);
				datas.setModifyBy("admin1");
				datas.setModifyDate(new Date());
				
				List<OrderDetails> orderDetail = this.orderDetailRepo.findOrderByHeaderId(id);
				for(OrderDetails item : orderDetail) {
					Products product = item.product;
					if(product.stock - item.getQuantity() <= 0) {
						product.stock = (double) 0;
					}else {
						product.stock = product.stock - item.getQuantity();
					}
					
					this.productRepo.save(product);
				}
				
				this.orderHeaderRepo.save(datas);
				System.out.println(datas);
				return new ResponseEntity<Object>(datas, HttpStatus.OK);
			}catch(Exception e) {
				return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
			}
	}
}
