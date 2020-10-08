package com.egen.spring.service.tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.egen.spring.repository.AddressRepository;
import com.egen.spring.service.AddressService;

@ExtendWith(MockitoExtension.class)
public class AddressServiceTest {

    @Mock
    AddressRepository addressRepository;
    
    @InjectMocks
    AddressService mockAddressService;
    
    @Test
    void getOrderAddressById() {
        
    }
    
    @Test
    void createAddress() {
        
    }
    
    @Test
    void updateAddress() {
        
    }
    
}
