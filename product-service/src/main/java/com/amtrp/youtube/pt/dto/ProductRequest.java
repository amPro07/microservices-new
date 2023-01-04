package com.amtrp.youtube.pt.dto;

import com.amtrp.youtube.pt.model.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProductRequest {

	private String name;
	private String description;
	private double price;
}
