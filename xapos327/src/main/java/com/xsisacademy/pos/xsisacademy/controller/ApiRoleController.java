package com.xsisacademy.pos.xsisacademy.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xsisacademy.pos.xsisacademy.model.Role;
import com.xsisacademy.pos.xsisacademy.repository.RoleRepository;

@RestController
@RequestMapping("/api/roles")
public class ApiRoleController {

	@Autowired
	private RoleRepository roleRepo;

	@GetMapping("/allRole")
	public ResponseEntity<List<Role>> getAllRoles() {
		try {
			List<Role> datas = this.roleRepo.findByNotDelete();
			
			return new ResponseEntity<>(datas, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/postRoles")
	public ResponseEntity<Object> createRoles(@RequestBody Role role) {
		
		role.setIsDelete(false);
		role.setCreatedDate(LocalDateTime.now());
		Role datas = this.roleRepo.save(role);
		System.out.println(datas.toString());
		
		if (datas.equals(role)) {
			return new ResponseEntity<>("Post Data Success!", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Post Data Failed!", HttpStatus.NO_CONTENT);
		}

	}
	
	@GetMapping("/getRole/{id}")
	public ResponseEntity<Object> getRole (@PathVariable("id")Long id){
		try {
			Optional<Role> data = this.roleRepo.findIdAndNotDelete(id);
			return new ResponseEntity<>(data, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@PutMapping("/editRole/{id}")
	public ResponseEntity<Role> editRole (@PathVariable("id") Long id, @RequestBody Role role){
		Optional<Role> datas = this.roleRepo.findById(id);
		if(datas.isPresent()) {
			role.setId(id);
			role.setIsDelete(false);
			role.setCreatedDate(datas.get().getCreateDate());
			role.setUpdatedBy(datas.get().getCreatedBy());
			role.setUpdatedDate(LocalDateTime.now());
			this.roleRepo.save(role);
			return new ResponseEntity<>(role, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@PutMapping("/deleteRole/{id}")
	public ResponseEntity<Role> deleteRole(@PathVariable("id") Long id){
		Optional<Role> datas = this.roleRepo.findById(id);
		if(datas.isPresent()) {
			Role role = new Role();
			role.setId(id);
			role.setIsDelete(true);
			role.setCreatedDate(datas.get().getCreateDate());
			role.setCreatedBy(datas.get().getCreatedBy());
			role.setUpdatedBy(datas.get().getCreatedBy());
			role.setUpdatedDate(LocalDateTime.now());
			this.roleRepo.save(role);
			return new ResponseEntity<>(role, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	// Pagination
	@GetMapping("/paging")
	public ResponseEntity<Map<String,Object>> getAllRolePages(@RequestParam(defaultValue = "0") int currPage, @RequestParam(defaultValue = "5") int size, @RequestParam("keyword") String keyword, @RequestParam("sortType") String sortType){
		try {
			List<Role> role = new ArrayList<>();
			Pageable pagingSort = PageRequest.of(currPage, size);
			Page<Role> pages = null;
			
			if(sortType.equals("ASC")) {
				pages = this.roleRepo.findByKeywordNotDelete(keyword, false, pagingSort);
			}else if(sortType.equals("DESC")) {
				pages= this.roleRepo.findByKeywordNotDeleteDesc(keyword, false,pagingSort);
			}
			
			role = pages.getContent();
			Map<String, Object> roleMap = new HashMap<>();
			roleMap.put("pages", pages.getNumber());
			roleMap.put("total", pages.getTotalElements());
			roleMap.put("total_page", pages.getTotalPages());
			roleMap.put("data", role);
			
			return new ResponseEntity<>(roleMap, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

}
