package com.amtrp.youtube.pt.service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import com.amtrp.youtube.pt.config.WebClientConfig;
import com.amtrp.youtube.pt.dto.InventoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amtrp.youtube.pt.dto.OrderLineItemsDto;
import com.amtrp.youtube.pt.dto.OrderRequest;
import com.amtrp.youtube.pt.model.OrderLineItems;
import com.amtrp.youtube.pt.model.Orders;
import com.amtrp.youtube.pt.repository.OrderRepository;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;

	private final WebClient.Builder webClientBuilder;
	
	public void placeOrder(OrderRequest orderRequest) {
		Orders orders = new Orders();
		
		orders.setOrderNumber(UUID.randomUUID().toString());



		List<OrderLineItems> list = orderRequest.getOrderLineItemsDto()
		.stream()
		.map(orderrLineItem -> maptoDto(orderrLineItem))
		.toList();
		
		orders.setOrderLineItemsList(list);

		List<String> skuCodes = orders.getOrderLineItemsList().stream()
				.map(OrderLineItems::getSkuCode).toList();

		//call inventory to check the product exists

		InventoryResponse[] inventoryResponses = webClientBuilder.build().get()
				.uri("http://inventory-service/api/inventory",
						uriBuilder -> uriBuilder.queryParam("skucode", skuCodes).build())
				.retrieve()
				.bodyToMono(InventoryResponse[].class)
				.block();

		boolean allProdInStock = Arrays.stream(inventoryResponses)
				.allMatch(InventoryResponse::isInStock);

		if(allProdInStock){
			orderRepository.save(orders);
		}else {
			throw new IllegalArgumentException("Product is not in stock, try again later.");
		}

	}
	
	private OrderLineItems maptoDto(OrderLineItemsDto itemsDto) {
		OrderLineItems lineItems = new OrderLineItems();
		lineItems.setSkuCode(itemsDto.getSkuCode());
		lineItems.setPrice(itemsDto.getPrice());
		lineItems.setQty(itemsDto.getQty());
		return lineItems;
	}

}
