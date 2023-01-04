package com.amtrp.youtube.pt.service;

import com.amtrp.youtube.pt.dto.InventoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amtrp.youtube.pt.repository.InventoryRepository;

import java.util.List;

@Service
public class InventoryService {

	@Autowired
	private InventoryRepository inventoryRepository;

	@Transactional(readOnly = true)
	public List<InventoryResponse> isInStock(List<String> skucode) {

		return inventoryRepository.findBySkuCodeIn(skucode).stream()
				.map(inventory -> InventoryResponse.builder()
						.skuCode(inventory.getSkuCode())
						.isInStock(inventory.getQuantity() > 0)
						.build()
				).toList();
	}



}
