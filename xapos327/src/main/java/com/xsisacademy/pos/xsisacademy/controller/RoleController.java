package com.xsisacademy.pos.xsisacademy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/role")
public class RoleController {
	
	@GetMapping("/indexapi")
	public ModelAndView index() {
		ModelAndView view = new ModelAndView("roles/indexapi.html");
		return view;
	}
	
}
