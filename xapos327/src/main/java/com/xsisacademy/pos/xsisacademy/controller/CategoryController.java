package com.xsisacademy.pos.xsisacademy.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.xsisacademy.pos.xsisacademy.model.Category;
import com.xsisacademy.pos.xsisacademy.model.VMCategory;
import com.xsisacademy.pos.xsisacademy.repository.CategoryRepository;

@Controller
@RequestMapping("/category/")
public class CategoryController {
	@Autowired
	private CategoryRepository categoryRepository;

	public static String uploadDirectory = System.getProperty("user.dir")+ "/src/main/webapp/img";

	@GetMapping("index")
	public ModelAndView index() {
		ModelAndView view = new ModelAndView("category/index.html");
		// get all data from database
		List<Category> listCategory = this.categoryRepository.findAll();
		// push it to frontend
		view.addObject("listCategory", listCategory);
		return view;
	}

	@GetMapping("addform")
	public ModelAndView addform() {
		ModelAndView view = new ModelAndView("category/addform.html");
		Category category = new Category();
		view.addObject("category", category);
		return view;
	}

	@PostMapping("save")
	public ResponseEntity<Object> save(VMCategory vmcategory, BindingResult result) { // @ModelAttribute Category category, BindingResult result
		if (!result.hasErrors()) {
			LocalDateTime datetime = LocalDateTime.now();
			DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
			
			String filename = datetime.format(formatDate) + "_" + vmcategory.file.getOriginalFilename();
			Path filenameAndPath = Paths.get(uploadDirectory, filename);
			try {
				Files.write(filenameAndPath, vmcategory.file.getBytes());
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			Category category = new Category();
			//untuk create
			if (vmcategory.id == null) {
				category.categoryInitial = vmcategory.categoryInitial;
				category.categoryName = vmcategory.categoryName;
				category.isActive = vmcategory.isActive;
				category.createBy = "admin1";
				category.createDate = new Date();
				category.sphoto = filename;
			} else { //untuk edit
				Category tempCategory = this.categoryRepository.findById(vmcategory.id).orElse(null);
				if (tempCategory != null) {
					category = tempCategory; 
					category.categoryInitial = vmcategory.categoryInitial;
					category.categoryName = vmcategory.categoryName;
					category.sphoto = filename;
					category.modifyBy = "admin1";
					category.modifyDate = new Date();
				}
			}
			this.categoryRepository.save(category);
			return new ResponseEntity<>(category, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("edit/{id}")
	public ModelAndView edit(@PathVariable("id") Long id) {
		ModelAndView view = new ModelAndView("category/addform.html");

		Category category = this.categoryRepository.findById(id).orElse(null);
		view.addObject("category", category);
		return view;
	}

	@GetMapping("delete/{id}")
	public ModelAndView delete(@PathVariable("id") Long id) {

		Category category = this.categoryRepository.findById(id).orElse(null);
		if (category != null) {
			this.categoryRepository.delete(category);
		}
		return new ModelAndView("redirect:/category/index");
	}
	
	@GetMapping("checkNameIfExist")
	public ResponseEntity<Boolean> checkIfNameExist(@RequestParam("categoryName") String categoryName, @RequestParam("id") Long id){
		Boolean isExist = false;
		Category category = new Category();
		if(id == 0) {
			category = this.categoryRepository.findByIdName(categoryName);
		}else {
			category = this.categoryRepository.findByIdNameForEdit(categoryName, id);
		}
		
		if(category != null) {
			isExist = true;
		}
		
		return new ResponseEntity<>(isExist, HttpStatus.OK);
	}

	// using api
	@GetMapping("indexapi")
	public ModelAndView indexapi() {
		ModelAndView view = new ModelAndView("category/indexapi.html");
		return view;
	}

	// using api + paging (pagging, sorting)
	@GetMapping("indexapi_pg")
	public ModelAndView indexapi_pg() {
		ModelAndView view = new ModelAndView("category/indexapi_pg.html");
		return view;
	}
}
