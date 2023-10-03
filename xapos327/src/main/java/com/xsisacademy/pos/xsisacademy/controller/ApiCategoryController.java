package com.xsisacademy.pos.xsisacademy.controller;

import java.util.ArrayList;
import java.util.Date;
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

import com.xsisacademy.pos.xsisacademy.model.Category;
import com.xsisacademy.pos.xsisacademy.repository.CategoryRepository;

@RestController
@RequestMapping("/api/")
public class ApiCategoryController {
	
	@Autowired
	private CategoryRepository categoryRepo;
	
	@GetMapping("category")
	public ResponseEntity<List<Category>> getAllCategory(){
	
		try {
//			List<Category> listCategory =this.categoryRepo.findAll();
			List<Category> listCategory =this.categoryRepo.findByCategories();
			return new ResponseEntity<>(listCategory, HttpStatus.OK);
		}catch(Exception err) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@PostMapping("category/add")
	public ResponseEntity<Object> saveCategory(@RequestBody Category category){
		category.createBy = "admin1";
		category.createDate = new Date();
		Category categoryData = this.categoryRepo.save(category);
		
		if(categoryData.equals(category)) {
			return new ResponseEntity<>("Save Data Success", HttpStatus.OK);
		}else {
			return new ResponseEntity<>("Save Data Failed", HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("category/{id}")
	public ResponseEntity<Object> getCategoryById(@PathVariable("id") Long id){
		try {
			System.out.println("-> " + id);
			Optional<Category> category = this.categoryRepo.findById(id);
			return new ResponseEntity<>(category, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@PutMapping("category/edit/{id}")
	public ResponseEntity<Object> editCategory(@PathVariable("id") Long id, @RequestBody Category category){
		Optional<Category> categoryData = this.categoryRepo.findById(id);
		
		if(categoryData.isPresent()) {
			category.id = id;
			category.modifyBy = "Admin1";
			category.modifyDate = new Date();
			category.createBy = categoryData.get().createBy;
			category.createDate = categoryData.get().createDate;
			this.categoryRepo.save(category);
			return new ResponseEntity<>("Updating Data Success", HttpStatus.OK);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PutMapping("category/delete/{id}")
	public ResponseEntity<Object> deleteCategory(@PathVariable("id") Long id){
		Optional<Category> categoryData = this.categoryRepo.findById(id);
		
		if(categoryData.isPresent()) {
			Category category = new Category();
			category.id = id;
			category.isActive = false;
			category.modifyBy = "Admin1";
			category.modifyDate = new Date();
			category.createBy = categoryData.get().createBy;
			category.createDate = categoryData.get().createDate;
			category.categoryInitial = categoryData.get().categoryInitial;
			category.categoryName = categoryData.get().categoryName;
			
			this.categoryRepo.save(category);
			return new ResponseEntity<>("Deleting Data Success", HttpStatus.OK);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	// PAGINATION 
	@GetMapping("category/paging")
	public ResponseEntity<Map<String, Object>> getAllCategoryPages(@RequestParam(defaultValue = "0") int currentPage, @RequestParam(defaultValue = "5") int size, @RequestParam("keyword") String keyword, @RequestParam("sortType") String sortType){
		try {
			List<Category> category = new ArrayList();
			Pageable pagingSort = PageRequest.of(currentPage, size);
			
			Page<Category> pages;
			
			if(sortType.equals("ASC")) {
				pages = this.categoryRepo.findByIsActive(keyword, true,pagingSort); // true is for isActive 
			}else {
				pages = this.categoryRepo.findByIsActiveDesc(keyword, true, pagingSort);
			}
			
			category = pages.getContent();
			Map<String, Object> response = new HashMap<>();
			response.put("pages", pages.getNumber());
			response.put("total", pages.getTotalElements());
			response.put("total_page", pages.getTotalPages() );
			response.put("data", category);
			
			return new ResponseEntity<>(response, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
