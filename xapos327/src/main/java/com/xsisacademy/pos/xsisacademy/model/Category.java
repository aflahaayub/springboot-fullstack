package com.xsisacademy.pos.xsisacademy.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "category")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name= "id")
	public Long id;
	
	@Column(name= "category_initial")
	public String categoryInitial;
	
	@Column(name= "category_name")
	public String categoryName;
	
	@Column(name= "create_by")
	public String createBy;
	
	@Column(name= "create_date")
	public Date createDate;
	
	@Column(name= "is_active")
	public Boolean isActive;
	
	@Column(name= "modify_by")
	public String modifyBy;
	
	@Column(name= "modify_date")
	public Date modifyDate;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategoryInitial() {
		return categoryInitial;
	}

	public void setCategoryInitial(String categoryInitial) {
		this.categoryInitial = categoryInitial;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
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
