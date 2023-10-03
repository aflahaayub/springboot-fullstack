package com.xsisacademy.pos.xsisacademy.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="products")
public class Products {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	public Long id;
	
	@Column(name="variant_id")
	public Long variantId;
	
	@ManyToOne
	@JoinColumn(name="variant_id", insertable= false, updatable = false)
	public Variant variant;
	
	@Column(name="product_initial")
	public String productInitial;
	
	@Column(name="product_name")
	public String productName;
	
	@Column(name="description")
	public String desc;
	
	@Column(name="is_active")
	public Boolean isActive;
	
	@Column(name="price")
	public Double price;
	
	@Column(name="stock")
	public Double stock;
	
	@Column(name="create_by")
	public String createBy;
	
	@Column(name="create_date")
	public Date createDate;
	
	@Column(name="modify_by")
	public String modifyBy;
	
	@Column(name="modify_date")
	public Date modifyDate;
	
//	for image
	@Column(name="image_data")
	public String imagePath;

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getVariantId() {
		return variantId;
	}

	public void setVariantId(Long variantId) {
		this.variantId = variantId;
	}

	public Variant getVariant() {
		return variant;
	}

	public void setVariant(Variant variant) {
		this.variant = variant;
	}

	public String getProductInitial() {
		return productInitial;
	}

	public void setProductInitial(String productInitial) {
		this.productInitial = productInitial;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getStock() {
		return stock;
	}

	public void setStock(Double stock) {
		this.stock = stock;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getModifyBy() {
		return modifyBy;
	}

	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	@Override
	public String toString() {
		return "Products [id=" + id + ", variantId=" + variantId + ", variant=" + variant + ", productInitial="
				+ productInitial + ", productName=" + productName + ", desc=" + desc + ", isActive=" + isActive
				+ ", price=" + price + ", stock=" + stock + ", createBy=" + createBy + ", createDate=" + createDate
				+ ", modifyBy=" + modifyBy + ", modifyDate=" + modifyDate + "]";
	}
	
	
	
}
