package com.egen.spring.kafka;

import java.util.List;

import org.apache.kafka.clients.producer.Producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.egen.spring.exceptions.ResourceNotFoundException;
import com.egen.spring.model.OrderEntity;
import com.egen.spring.service.OrderService;

@Service
public class KafkaConsumer {
	
	private final Logger logger = LoggerFactory.getLogger(Producer.class);
	
	@Autowired
	private OrderService orderService;

    @KafkaListener(topics = "create_orders", groupId = "group_id")
    public void createOrders(List<OrderEntity> orders) throws ResourceNotFoundException {
        logger.info(String.format("#### -> Consumed message -> %s", orders));
        orderService.addOrderBulk(orders);
    }
    
    @KafkaListener(topics = "cancel_orders", groupId = "group_id")
    public void CancelOrders(List<Long> orderIds) throws ResourceNotFoundException {
        logger.info(String.format("#### -> Consumed message -> %s", orderIds));
        orderService.cancelOrderBulk(orderIds);
    }
    

}
