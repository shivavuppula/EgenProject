package com.egen.spring.kafka;

import java.util.List;

import org.apache.kafka.clients.producer.Producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.egen.spring.model.OrderEntity;

@Service
public class KafkaProducer {
	
	private final Logger logger = LoggerFactory.getLogger(Producer.class);
	
	private static final String CREATE_ORDERS_TOPIC = "create_orders";
	
	private static final String CANCEL_ORDERS_TOPIC = "cancel_orders";
    
    @Autowired
    private KafkaTemplate<String, List<OrderEntity>> createKafkaTemplate;
    
    @Autowired
    private KafkaTemplate<String, List<Long>> cancelKafkaTemplate;
    
    public void createOrders(List<OrderEntity> orders) {
    	logger.info(String.format("#### -> Producing message -> %s", orders));
        this.createKafkaTemplate.send(CREATE_ORDERS_TOPIC, orders);
    }
    
    public void cancelOrders(List<Long> orderIds) {
    	logger.info(String.format("#### -> Producing message -> %s", orderIds));
    	this.cancelKafkaTemplate.send(CANCEL_ORDERS_TOPIC, orderIds);
    }

}
