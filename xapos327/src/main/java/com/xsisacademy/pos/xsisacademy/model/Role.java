package com.xsisacademy.pos.xsisacademy.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
//	roleName VARCHAR(80),
//	isDelete BOOLEAN,
//	createdBy BIGINT NOT NULL,
//	createdDate TIMESTAMP NOT NULL,
//	updatedBy BIGINT,
//	updatedDate TIMESTAMP
	@Column(name = "rolename")
	private String roleName;

	@Column(name = "isdelete")
	private Boolean isDelete;

	@Column(name = "createdby")
	private String createdBy;

	@Column(name = "createddate")
	private LocalDateTime createdDate;

	@Column(name = "updatedby")
	private String updatedBy;

	@Column(name = "updateddate")
	private LocalDateTime updatedDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Boolean getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getCreateDate() {
//		DateTimeFormatter formatted = DateTimeFormatter.ofPattern("EE, dd-MM-yyyy HH:mm:ss", Locale.ENGLISH);
//		String formatDate = createdDate.format(formatted);
//		return formatDate;
		
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public LocalDateTime getUpdatedDate() {
//		if (updatedDate != null) {
//			DateTimeFormatter formatted = DateTimeFormatter.ofPattern("EE, dd-MM-yyyy HH:mm:ss", Locale.ENGLISH);
//			String formatDate = updatedDate.format(formatted);
//			return formatDate;
//		}else {
//			return null;
//		}
		
		return updatedDate;
		

	}

	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", roleName=" + roleName + ", isDelete=" + isDelete + ", createdBy=" + createdBy
				+ ", createdDate=" + createdDate + ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate + "]";
	}

}
