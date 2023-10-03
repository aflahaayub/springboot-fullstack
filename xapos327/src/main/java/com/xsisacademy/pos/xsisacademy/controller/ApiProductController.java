package com.xsisacademy.pos.xsisacademy.controller;

import java.io.File;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.xsisacademy.pos.xsisacademy.model.Products;
import com.xsisacademy.pos.xsisacademy.model.Variant;
import com.xsisacademy.pos.xsisacademy.repository.ProductRepository;
import com.xsisacademy.pos.xsisacademy.repository.VariantRepository;

@RestController
@RequestMapping("/api")
public class ApiProductController {
	private String imgPath = null;
	private static String directory = System.getProperty("user.dir") + "/src/main/resources/static/images/";
	
	@Autowired
	private ProductRepository productRepo;

	@Autowired
	private VariantRepository variantRepo;

	@GetMapping("/products")
	public ResponseEntity<List<Products>> getAllProducts() {
		try {
			List<Products> datas = this.productRepo.findByisActive(true);
			// List<Products> datas = this.productRepo.findByisActive(true, "a");
			return new ResponseEntity<>(datas, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/products/category/{categoryId}")
	public ResponseEntity<List<Variant>> variantByCategoryId(@PathVariable Long categoryId) {
		try {
			List<Variant> datas = this.variantRepo.findByCategoryId(categoryId);
			return new ResponseEntity<>(datas, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@PostMapping("/uploadImage")
	public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
		try {
			String fileName = file.getOriginalFilename();
			
			String filePath = directory + "productImage/" + fileName; // Define your image upload directory
			file.transferTo(new File(filePath));
			
			imgPath = "/images/productImage/" + fileName;
			
			System.out.println(imgPath);
			// Set the image path in the product object
			// product.setImagePath(filePath);
			return new ResponseEntity<>("Success Upload Image", HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>("Failed Upload Image", HttpStatus.BAD_REQUEST);
		}

	}

	@PostMapping("/products/new")
	public ResponseEntity<Object> saveNewProduct(@RequestBody Products product) {
		if (imgPath == null) {
			imgPath = "/images/default.jpg";
		}

		product.createBy = "admin2";
		product.createDate = new Date();
		product.setImagePath(imgPath);

		Products productData = this.productRepo.save(product);
		if (productData.equals(product)) {
			return new ResponseEntity<>("Success Make New Data", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Failed Make New Data", HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/products/{id}")
	public ResponseEntity<Object> getProductById(@PathVariable Long id) {
		try {
			Optional<Products> datas = this.productRepo.findById(id);
			return new ResponseEntity<>(datas, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@PutMapping("/products/edit/{id}")
	public ResponseEntity<Object> saveEditedProduct(@PathVariable Long id, @RequestBody Products product) {
		Optional<Products> datas = this.productRepo.findById(id);
		if (datas.isPresent()) {
			product.id = id;
			product.setModifyBy("admin2");
			product.setModifyDate(new Date());
			product.setCreateBy(datas.get().createBy);
			product.setCreateDate(datas.get().createDate);
			this.productRepo.save(product);
			return new ResponseEntity<>(product, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@PutMapping("/products/delete/{id}")
	public ResponseEntity<Object> deleteProduct(@PathVariable Long id) {
		Optional<Products> datas = this.productRepo.findById(id);
		if (datas.isPresent()) {
			Products product = new Products();
			product.id = id;
			product.setVariantId(datas.get().variantId);
			product.setIsActive(false);
			product.setDesc(datas.get().desc);
			product.setProductName(datas.get().productName);
			product.setProductInitial(datas.get().productInitial);
			product.setPrice(datas.get().price);
			product.setStock(datas.get().stock);
			product.setModifyBy("admin2");
			product.setModifyDate(new Date());
			product.setCreateBy(datas.get().createBy);
			product.setCreateDate(datas.get().createDate);
			this.productRepo.save(product);
			return new ResponseEntity<>(product, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
}
