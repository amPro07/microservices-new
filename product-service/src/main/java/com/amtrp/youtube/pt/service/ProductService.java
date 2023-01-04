package com.amtrp.youtube.pt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amtrp.youtube.pt.dto.ProductRequest;
import com.amtrp.youtube.pt.dto.ProductResponse;
import com.amtrp.youtube.pt.model.Product;
import com.amtrp.youtube.pt.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public void createProduct(ProductRequest productRequest) {
		Product product = Product.builder()
		.name(productRequest.getName())
		.description(productRequest.getDescription())
		.price(productRequest.getPrice()).build();
		productRepository.save(product);
		
	}

	public List<ProductResponse> getProducts() {
		List<Product> products = productRepository.findAll();
		return products.stream().map(product -> myProductResponse(product)).toList();
		 
	}
	
	private ProductResponse myProductResponse(Product product) {
		ProductResponse productR = ProductResponse.builder()
				.id(product.getId())
				.name(product.getName())
				.description(product.getDescription())
				.price(product.getPrice()).build();
		return productR;
	}
	
	
	
}
