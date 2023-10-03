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
import com.xsisacademy.pos.xsisacademy.model.Variant;
import com.xsisacademy.pos.xsisacademy.repository.CategoryRepository;
import com.xsisacademy.pos.xsisacademy.repository.VariantRepository;

@Controller
@RequestMapping("/variant")
public class VariantController {
	@Autowired
	private VariantRepository variantRepository;
	@Autowired
	private CategoryRepository categoryRepository;

	@GetMapping("/index")
	public ModelAndView index() {
		ModelAndView view = new ModelAndView("variant/index.html");
		// get all data from database
		List<Variant> listVariant = this.variantRepository.findAll();
		// push it to frontend
		view.addObject("listVariant", listVariant);
		return view;
	}

	@GetMapping("/addform")
	public ModelAndView addform() {
		ModelAndView view = new ModelAndView("variant/addform.html");

		Variant variant = new Variant();
		view.addObject("variant", variant);

		List<Category> listCategory = this.categoryRepository.findAll();
		view.addObject("listCategory", listCategory);
		return view;
	}

	@PostMapping("save")
	public ModelAndView save(@ModelAttribute Variant variant, BindingResult result) {
		if (!result.hasErrors()) {
			if (variant.id == null) {
				variant.createBy = "admin1";
				variant.createDate = new Date();
			} else {
				Variant tempVariant = this.variantRepository.findById(variant.id).orElse(null);
				if (tempVariant != null) {
					variant.createBy = tempVariant.createBy;
					variant.createDate = tempVariant.createDate;
					variant.modifyBy = "admin1";
					variant.modifyDate = new Date();
				}
			}
			this.variantRepository.save(variant);
			return new ModelAndView("redirect:/variant/index");
		} else {
			return new ModelAndView("redirect:/variant/index");
		}
	}

	@GetMapping("edit/{id}")
	public ModelAndView edit(@PathVariable("id") Long id) {
		ModelAndView view = new ModelAndView("variant/addform.html");
		List<Category> listCategory = this.categoryRepository.findAll();
		view.addObject("listCategory", listCategory);
		Variant variant = this.variantRepository.findById(id).orElse(null);
		view.addObject("variant", variant);
		return view;
	}

	@GetMapping("delete/{id}")
	public ModelAndView delete(@PathVariable("id") Long id) {
		Variant variant = this.variantRepository.findById(id).orElse(null);
		if (variant != null) {
			this.variantRepository.delete(variant);
		}

		return new ModelAndView("redirect:/variant/index");
	}
	
	// with api
	@GetMapping("indexapi")
	public ModelAndView indexapi() {
		ModelAndView view = new ModelAndView("variant/indexapi.html");
		return view;
	}
}
