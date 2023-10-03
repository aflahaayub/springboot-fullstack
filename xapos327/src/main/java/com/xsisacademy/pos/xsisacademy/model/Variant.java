package com.xsisacademy.pos.xsisacademy.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "variant")
public class Variant {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public Long id;
	
	@Column(name="category_id")
	public Long categoryId;
	
	@Column(name="variant_initial")
	public String variantInitial;
	
	@Column(name="variant_name")
	public String variantName;
	
	@Column(name="create_by")
	public String createBy;
	
	@Column(name="create_date")
	public Date createDate;
	
	@Column(name= "is_active")
	public Boolean isActive;
	
	@Column(name= "modify_by")
	public String modifyBy;
	
	@Column(name= "modify_date")
	public Date modifyDate;
	
	@ManyToOne
	@JoinColumn(name="category_id", insertable = false, updatable = false)
	public Category category;

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getVariantInitial() {
		return variantInitial;
	}

	public void setVariantInitial(String variantInitial) {
		this.variantInitial = variantInitial;
	}

	public String getVariantName() {
		return variantName;
	}

	public void setVariantName(String variantName) {
		this.variantName = variantName;
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


	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
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

	
	

}
