package com.xsisacademy.pos.xsisacademy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/products")
public class ProductsController {
	@GetMapping("/indexapi")
	public ModelAndView index() {
		ModelAndView view = new ModelAndView("products/indexapi.html");
		return view;
	}
}
