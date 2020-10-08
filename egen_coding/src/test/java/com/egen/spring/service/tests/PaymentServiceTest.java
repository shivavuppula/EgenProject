package com.egen.spring.service.tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.egen.spring.repository.OrderRepository;
import com.egen.spring.repository.PaymentRepository;
import com.egen.spring.service.PaymentService;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceTest {
	
	@Mock
    PaymentRepository paymentRepository;

    @Mock
    OrderRepository orderRepository;
    
    @InjectMocks
    PaymentService mockPaymentService;
    
    @Test
    void makePayment() {
        
    }
}
