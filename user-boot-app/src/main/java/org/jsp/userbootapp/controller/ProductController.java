package org.jsp.userbootapp.controller;

import java.util.List;

import org.jsp.userbootapp.dto.Product;
import org.jsp.userbootapp.dto.ResponseStructure;
import org.jsp.userbootapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {
	@Autowired
	private ProductService productService;

	@PostMapping("{user_id}")
	public ResponseEntity<ResponseStructure<Product>> saveProduct(@RequestBody Product product,
			@PathVariable int user_id) {
		return productService.saveProduct(product, user_id);
	}

	@GetMapping("/find-by-brand/{brand}")
	public ResponseEntity<ResponseStructure<List<Product>>> findByBrand(@PathVariable String brand) {
		return productService.findByBrand(brand);
	}

	@GetMapping("/find-by-category/{category}")
	public ResponseEntity<ResponseStructure<List<Product>>> findByCategory(@PathVariable String category) {
		return productService.findByCategory(category);
	}
	
	@GetMapping("/find-by-name/{name}")
	public ResponseEntity<ResponseStructure<List<Product>>> findByName(@PathVariable String name) {
		return productService.findByName(name);
	}

	@GetMapping("{user_id}")
	public ResponseEntity<ResponseStructure<List<Product>>> findByUserId(@PathVariable int user_id) {
		return productService.findByUserId(user_id);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Product>>> findAll() {
		return productService.findAll();
	}
}
