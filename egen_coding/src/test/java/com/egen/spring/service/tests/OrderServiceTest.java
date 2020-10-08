package com.egen.spring.service.tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.egen.spring.repository.OrderRepository;
import com.egen.spring.repository.AddressRepository;
import com.egen.spring.service.OrderService;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {
	
	@Mock
    OrderRepository orderRepository;

    @Mock
    AddressRepository addressRepository;
    
    @InjectMocks
    OrderService mockOrderService;
    
    @Test
    void getOrderById() {
        
    }
    
    @Test
    void getAllOrders() {
        
    }
    
    @Test
    void createOrder() {
        
    }
    
    @Test
    void updateOrder() {
        
    }
    
    @Test
    void cancelOrder() {
        
    }
    
}
