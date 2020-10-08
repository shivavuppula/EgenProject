package com.egen.spring.service;

import java.util.Optional;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egen.spring.exceptions.ResourceNotFoundException;
import com.egen.spring.model.AddressEntity;
import com.egen.spring.repository.AddressRepository;

@Service
public class AddressService {
	
	@Autowired
	private AddressRepository addressRepository;

	public AddressEntity getOrderAddressById(Long orderAddressId) throws ResourceNotFoundException {
		AddressEntity addressEntity = addressRepository.findById(orderAddressId)
				.orElseThrow(() -> new ResourceNotFoundException("Order Not Found for this id ::" + orderAddressId));
		return addressEntity;
	}

	public AddressEntity createAddress(AddressEntity address) throws ResourceNotFoundException {
		try {
			return this.addressRepository.save(address);
		}
		catch(ConstraintViolationException exception) {
			throw new ResourceNotFoundException("User Already exists");
		}
		
	}
	
	public AddressEntity updateAddress(Long orderAddressId, AddressEntity address) throws ResourceNotFoundException {
		Optional<AddressEntity> addressEntity = this.addressRepository.findById(orderAddressId);
		if(addressEntity.isEmpty()) {
			throw new ResourceNotFoundException("Address Not Found for the order with id ::" + orderAddressId);
		}
		
		return this.addressRepository.save(address);
	}

}
