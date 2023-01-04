package com.amtrp.youtube.pt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.support.Repositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.amtrp.youtube.pt.dto.ProductRequest;
import com.amtrp.youtube.pt.dto.ProductResponse;
import com.amtrp.youtube.pt.model.Product;
import com.amtrp.youtube.pt.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/product")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public void createProduct(@RequestBody ProductRequest productRequest) {
		productService.createProduct(productRequest);
	}
	
	@GetMapping()
	public ResponseEntity<List<ProductResponse>> getProducts() {
		List<ProductResponse> products = productService.getProducts();
		return ResponseEntity.status(HttpStatus.OK).body(products);
	}
}
