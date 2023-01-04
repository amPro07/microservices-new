package com.amtrp.youtube.pt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.amtrp.youtube.pt.dto.OrderRequest;
import com.amtrp.youtube.pt.service.OrderService;

@RestController
@RequestMapping("/api/order")
public class OrderServiceController {
	
	@Autowired
	private OrderService orderService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	private String placeOrder(@RequestBody OrderRequest orderReq) {
		orderService.placeOrder(orderReq);
		return "Order Placed Successfully";

	}
}
