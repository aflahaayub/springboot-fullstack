package com.xsisacademy.pos.xsisacademy.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xsisacademy.pos.xsisacademy.model.Category;
import com.xsisacademy.pos.xsisacademy.repository.CategoryRepository;

@Controller
@RequestMapping("/category/")
public class CategoryController {
	@Autowired
	private CategoryRepository categoryRepository;
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
		ModelAndView view = new ModelAndView("/category/addform.html");
		Category category = new Category();
		view.addObject("category",category);
		return view;			
	}
	
	@PostMapping("save")
	public ModelAndView save(@ModelAttribute Category category, BindingResult result) {
		if(!result.hasErrors()) {
			if(category.id == null) {
				category.createBy = "admin1";
				category.createDate = new Date();
			}else {
				Category tempCategory = this.categoryRepository.findById(category.id).orElse(null);
				if(tempCategory != null) {
					category.createBy = tempCategory.createBy;
					category.createDate = tempCategory.createDate;
					category.modifyBy = "admin1";
					category.modifyDate = new Date();
				}
			}
			this.categoryRepository.save(category);
			return new ModelAndView("redirect:/category/index");
		}else {
			return new ModelAndView("redirect:/category/index");
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
		if(category != null) {
			this.categoryRepository.delete(category);
		}
		return new ModelAndView("redirect:/category/index");
	}
}
