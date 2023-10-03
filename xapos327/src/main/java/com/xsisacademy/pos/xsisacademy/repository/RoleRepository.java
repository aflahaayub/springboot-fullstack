package com.xsisacademy.pos.xsisacademy.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.xsisacademy.pos.xsisacademy.model.Category;
import com.xsisacademy.pos.xsisacademy.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	@Query(value="select r from Role r Where isDelete = false")
	List<Role> findByNotDelete();
	
	@Query(value ="select r from Role r where r.id = ?1")
	Optional<Role> findIdAndNotDelete(Long id);
	
	//PAGING
	@Query(value="SELECT * FROM Role WHERE lower(rolename) LIKE lower(concat('%', ?1, '%')) and isdelete = ?2 ORDER BY rolename asc ", nativeQuery=true)
	Page<Role> findByKeywordNotDelete(String keyword, Boolean isDelete, Pageable page);
		
	@Query(value="SELECT * FROM Category WHERE lower(rolename) LIKE lower(concat('%', ?1, '%')) and isdelete = ?2 ORDER BY rolename desc ", nativeQuery=true)
	Page<Role> findByKeywordNotDeleteDesc(String keyword, Boolean isDelete, Pageable page);
}
