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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xsisacademy.pos.xsisacademy.model.Variant;
import com.xsisacademy.pos.xsisacademy.repository.CategoryRepository;
import com.xsisacademy.pos.xsisacademy.repository.VariantRepository;

@RestController
@RequestMapping("/api/")
public class ApiVariantController {
	@Autowired
	public VariantRepository variantRepo;

	@Autowired
	public CategoryRepository categoryRepo;

	@GetMapping("variant")
	public ResponseEntity<List<Variant>> getAllVariant() {
		try {
//			List<Variant> allDatas = this.variantRepo.findAllDataVariant();
			List<Variant> allDatas = this.variantRepo.findByIsActive(true);
			return new ResponseEntity<>(allDatas, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@PostMapping("variant/new")
	public ResponseEntity<Object> saveDataVariant(@RequestBody Variant variant) {
		
		variant.createBy = "admin2";
		variant.createDate = new Date();
		Variant variantData = this.variantRepo.save(variant);

		if (variantData.equals(variant)) {
			return new ResponseEntity<>("Create Data Success", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Create Data Failed", HttpStatus.BAD_REQUEST);
		}

	}
	
	@GetMapping("variant/{id}")
	public ResponseEntity<Object> getVariantById(@PathVariable Long id){
		try {
			Optional<Variant> variant = this.variantRepo.findById(id);
			System.out.println(variant);
			return new ResponseEntity<>(variant, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@PutMapping("variant/edit/{id}")
	public ResponseEntity<Object> saveEditedDataVariant(@PathVariable Long id, @RequestBody Variant variant){
		Optional<Variant> variantData = this.variantRepo.findById(id);

		if(variantData.isPresent()) {
			variant.id = id;
			variant.isActive = true;
			variant.setModifyBy("admin2");
			variant.setModifyDate(new Date());
			variant.createBy = variantData.get().createBy;
			variant.createDate = variantData.get().createDate;
			
			this.variantRepo.save(variant);
			
			return new ResponseEntity<>("Success Editing Data", HttpStatus.OK);
			
		}else {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	@PutMapping("variant/delete/{id}")
	public ResponseEntity<Object> deleteDataVariant(@PathVariable Long id){
		Optional<Variant> data = this.variantRepo.findById(id);
		if(data.isPresent()) {
			Variant variant = new Variant();
			variant.setId(id);
			variant.setCategoryId(data.get().categoryId);
			variant.setIsActive(false);
			variant.setVariantInitial(data.get().variantInitial);
			variant.setVariantName(data.get().variantName);
			variant.setCreateBy(data.get().createBy);
			variant.setCreateDate(data.get().createDate);
			variant.setModifyBy("admin2");
			variant.setModifyDate(new Date());
			
			this.variantRepo.save(variant);
			return new ResponseEntity<>("Success Deleting Data", HttpStatus.OK);
		}else {
			return ResponseEntity.notFound().build();
		}
		
	}
}
