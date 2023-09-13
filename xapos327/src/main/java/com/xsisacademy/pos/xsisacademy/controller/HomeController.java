package com.xsisacademy.pos.xsisacademy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
	// rule: the default is index, but when there's more than two method, it will look for the index first for the default /
//	@GetMapping("index") // name for the query
//	public String index() {
//		return "index.html"; // file name 
//	}
	
	@GetMapping("home")
	public String home() {
		return "home.html";
	}
	
	@GetMapping("kalkulator")
	public String kalkulator() {
		return "kalkulator.html";
	}
}
