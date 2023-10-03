package com.xsisacademy.pos.xsisacademy;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.xsisacademy.pos.xsisacademy.controller.CategoryController;

@SpringBootApplication
public class Xapos327Application {

	public static void main(String[] args) {
		
		new File(CategoryController.uploadDirectory).mkdir(); // to create a folder
		SpringApplication.run(Xapos327Application.class, args);
	}

}
