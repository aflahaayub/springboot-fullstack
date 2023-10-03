package com.xsisacademy.pos.xsisacademy.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table
@Entity(name="order_details")
public class OrderDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="headerId")
	private Long headerId;
	
	@Column(name="productId")
	private Long productId;
	
	@Column(name="quantity")
	private Double quantity;
	
	@Column(name="price")
	private Double price;
	
	@Column(name="active")
	private Boolean active;
	
	@Column(name="createBy")
	private String createBy;
	
	@Column(name="createDate")
	private LocalDateTime createDate;
	
	@Column(name="modifyBy")
	private String modifyBy;
	
	@Column(name="modifyDate")
	private LocalDateTime modifyDate;
	
	@ManyToOne
	@JoinColumn(name="productId", insertable=false, updatable=false)
	public Products product;
	
	@ManyToOne
	@JoinColumn(name="headerId", insertable=false, updatable=false)
	private OrderHeader orderHeader;

	public OrderHeader getOrderHeader() {
		return orderHeader;
	}

	public void setOrderHeader(OrderHeader orderHeader) {
		this.orderHeader = orderHeader;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getHeaderId() {
		return headerId;
	}

	public void setHeaderId(Long headerId) {
		this.headerId = headerId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getCreateDate() {
		DateTimeFormatter formatted = DateTimeFormatter.ofPattern("EE, dd-MM-yyyy HH:mm:ss", Locale.ENGLISH);
		String formatDate = createDate.format(formatted);
		return formatDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public String getModifyBy() {
		return modifyBy;
	}

	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}

	public String getModifyDate() {
		DateTimeFormatter formatted = DateTimeFormatter.ofPattern("EE, dd-MM-yyyy HH:mm:ss", Locale.ENGLISH);
		String formatDate = createDate.format(formatted);
		return formatDate;
	}

	public void setModifyDate(LocalDateTime modifyDate) {
		this.modifyDate = modifyDate;
	}
	
	
}
