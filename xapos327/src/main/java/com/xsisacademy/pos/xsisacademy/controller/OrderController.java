package com.xsisacademy.pos.xsisacademy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/order")
public class OrderController {
	@GetMapping("/index")
	public static ModelAndView index() {
		ModelAndView page = new ModelAndView("orders/index.html");
		return page;
	}
}
