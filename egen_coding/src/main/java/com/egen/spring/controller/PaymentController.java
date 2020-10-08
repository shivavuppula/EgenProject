package com.egen.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.egen.spring.exceptions.ResourceNotFoundException;
import com.egen.spring.model.PaymentEntity;
import com.egen.spring.service.PaymentService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("ecommerce/api/v1")
public class PaymentController {
	
	@Autowired
	private PaymentService paymentService;
	
	@PostMapping("payment")
	@ApiOperation(value = "Make a new payment")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully made new payment"),
	        @ApiResponse(code = 400, message = "Cannot make payment, check details"),
	})
	public ResponseEntity<PaymentEntity> makePayment(@RequestBody PaymentEntity payment) {
		try{
			return ResponseEntity.ok().body(this.paymentService.makePayment(payment));
		}
		catch(ResourceNotFoundException exception) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	

}
