package com.egen.spring.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egen.spring.model.PaymentEntity;
import com.egen.spring.exceptions.ResourceNotFoundException;
import com.egen.spring.model.OrderEntity;
import com.egen.spring.repository.OrderRepository;
import com.egen.spring.repository.PaymentRepository;

@Service
public class PaymentService {
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private OrderRepository orderRepository;

	public PaymentEntity makePayment(PaymentEntity payment) throws ResourceNotFoundException {
		Optional<OrderEntity> orderEntity = this.orderRepository.findById(payment.getOrderId());
		
		if(orderEntity.isEmpty()){
            throw new ResourceNotFoundException("Cannot find Orders with id ::" + payment.getOrderId());
        }
		if(orderEntity.get().getOrderTotal() - payment.getOrderPaymentAmount() > 0) {
			orderEntity.get().setOrderTotal(orderEntity.get().getOrderTotal() - payment.getOrderPaymentAmount());
			orderEntity.get().setOrderStatus("PAYMENT INCOMPLETE");
			orderEntity.get().setOrderLastUpdatedTimestamp(new Date());
		}
		else {
			orderEntity.get().setOrderTotal(0.0);
			orderEntity.get().setOrderStatus("PAYMENT COMPLETE");
			orderEntity.get().setOrderLastUpdatedTimestamp(new Date());
		}
		
		this.orderRepository.save(orderEntity.get());
		payment.setOrderPaymentDate(new Date());
		return this.paymentRepository.save(payment);
	}

}
