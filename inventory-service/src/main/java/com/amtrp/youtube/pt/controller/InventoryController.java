package com.amtrp.youtube.pt.controller;

import com.amtrp.youtube.pt.dto.InventoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.amtrp.youtube.pt.service.InventoryService;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
	
	@Autowired
	private InventoryService inventoryService;
	
	@GetMapping()
	@ResponseStatus(HttpStatus.OK)
	public List<InventoryResponse> isInStock(@RequestParam List<String> skucode) {
		return inventoryService.isInStock(skucode);
	}

}
