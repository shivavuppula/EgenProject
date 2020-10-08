package com.egen.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.egen.spring.exceptions.ResourceNotFoundException;
import com.egen.spring.model.AddressEntity;
import com.egen.spring.service.AddressService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("ecommerce/api/v1")
public class AddressController {
	
	@Autowired
	private AddressService addressService;
	
	@GetMapping("address/{id}")
	@ApiOperation(value = "View the address by id")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully retrieved the address"),
	        @ApiResponse(code = 400, message = "Cannot find the address with the id"),
	})
	public ResponseEntity<AddressEntity> getAddressById(@PathVariable(value="id") Long orderAddressId) throws ResourceNotFoundException{
		try{
			AddressEntity addressEntity = this.addressService.getOrderAddressById(orderAddressId);
			return ResponseEntity.ok().body(addressEntity);
		}
		catch(ResourceNotFoundException exception){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	
	@PostMapping("address")
	@ApiOperation(value = "Create a new address")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully created the address"),
	        @ApiResponse(code = 400, message = "Cannot create address, check details"),
	})
	public ResponseEntity<AddressEntity> createAddress(@RequestBody AddressEntity address) {
		try{
			return ResponseEntity.ok().body(this.addressService.createAddress(address));
		}
		catch(ResourceNotFoundException exception) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	@PutMapping("address/{id}")
	@ApiOperation(value = "Update the address by id")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully updated the address"),
	        @ApiResponse(code = 400, message = "Cannot update the address, as address with the id doesn't exist"),
	})
	public ResponseEntity<AddressEntity> updateAddress(@PathVariable(value="id") Long orderAddressId, @RequestBody AddressEntity address) throws ResourceNotFoundException{
		try {
			return ResponseEntity.ok().body(this.addressService.updateAddress(orderAddressId, address));
		}
		catch(ResourceNotFoundException exception) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	

}
