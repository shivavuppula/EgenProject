package com.egen.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.egen.spring.exceptions.ResourceNotFoundException;
import com.egen.spring.kafka.KafkaProducer;
import com.egen.spring.model.OrderEntity;
import com.egen.spring.service.OrderService;

import io.swagger.annotations.*;

@RestController
@RequestMapping("ecommerce/api/v1")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private KafkaProducer kafkaProducer;
	
	@GetMapping("orders")
	@ApiOperation(value = "View list of all orders")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully retrieved list of all orders"),
//	        @ApiResponse(code = 401, message = "You are not authorized to view the orders"),
//	        @ApiResponse(code = 403, message = "Accessing the orders you were trying to reach is forbidden"),
//	        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})
	public List<OrderEntity> getAllOrders(){
		return this.orderService.getAllOrders();
	}
	
	@GetMapping("orders/{id}")
	@ApiOperation(value = "View the order by id")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully retrieved the order"),
	        @ApiResponse(code = 400, message = "Cannot find the order with the id"),
	})
	public ResponseEntity<OrderEntity> getOrderById(@PathVariable(value="id") Long orderId) throws ResourceNotFoundException{
		try{
			OrderEntity orderEntity = this.orderService.getOrderById(orderId);
			return ResponseEntity.ok().body(orderEntity);
		}
		catch(ResourceNotFoundException exception){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		
	}
	
	@PostMapping("orders")
	@ApiOperation(value = "Create a new order")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully created the order"),
	        @ApiResponse(code = 400, message = "Cannot create order, check details"),
	})
	public ResponseEntity<OrderEntity> createOrder(@RequestBody OrderEntity order) {
		try{
			return ResponseEntity.ok().body(this.orderService.createOrder(order));
		}
		catch(ResourceNotFoundException exception) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	@PutMapping("orders/{id}")
	@ApiOperation(value = "Update the order by id")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully updated the order"),
	        @ApiResponse(code = 400, message = "Cannot update the order, as order with the id doesn't exist"),
	})
	public ResponseEntity<OrderEntity> updateOrder(@PathVariable(value="id") Long orderId, @RequestBody OrderEntity order) throws ResourceNotFoundException{
		try {
			return ResponseEntity.ok().body(this.orderService.updateOrder(orderId, order));
		}
		catch(ResourceNotFoundException exception) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	@DeleteMapping("orders/{id}")
	@ApiOperation(value = "Cancel the order by id")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully cancelled the order"),
	        @ApiResponse(code = 400, message = "Cannot cancel the order, as order with the id doesn't exist"),
	})
	public ResponseEntity<OrderEntity> cancelOrder(@PathVariable(value="id") Long orderId) throws ResourceNotFoundException{
		try {
			this.orderService.cancelOrder(orderId);
			return ResponseEntity.ok().body(null);
		}
		catch(ResourceNotFoundException exception) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	@PostMapping("orders/bulk")
	@ApiOperation(value = "Create orders in bulk")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully created orders in bulk"),
	})
	public void createOrderBulk(@RequestBody List<OrderEntity> orders) {
		kafkaProducer.createOrders(orders);
	}
	
	@DeleteMapping("orders/{ids}")
	@ApiOperation(value = "Cancel orders in bulk")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully cancelled orders in bulk"),
	})
	public void cancelOrderBulk(@PathVariable List<Long> ids) {
		kafkaProducer.cancelOrders(ids);
		
	}

}
