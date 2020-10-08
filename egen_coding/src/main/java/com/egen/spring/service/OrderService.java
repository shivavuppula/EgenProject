package com.egen.spring.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egen.spring.exceptions.ResourceNotFoundException;
import com.egen.spring.model.AddressEntity;
import com.egen.spring.model.OrderEntity;
import com.egen.spring.repository.AddressRepository;
import com.egen.spring.repository.OrderRepository;


@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private AddressRepository addressRepository;

	public OrderEntity getOrderById(Long orderId) throws ResourceNotFoundException {
		OrderEntity orderEntity = orderRepository.findById(orderId)
				.orElseThrow(() -> new ResourceNotFoundException("Order Not Found for this id ::" + orderId));
		return orderEntity;
	}

	public List<OrderEntity> getAllOrders() {
		return this.orderRepository.findAll();
	}

	public OrderEntity createOrder(OrderEntity order) throws ResourceNotFoundException {
		Optional<AddressEntity> addressEntity = this.addressRepository.findById(order.getOrderShippingAddressId());
		if(addressEntity.isEmpty()) {
			throw new ResourceNotFoundException("Address Not Found for the order with id ::" + order.getOrderId());
		}
		
		order.setOrderSubtotal(getOrderSubTotal(order));
		order.setOrderTax(getOrderTax(order));
		order.setOrderShippingCharges(getOrderShippingCharges(order));
		order.setOrderTotal(getOrderTotal(order));
		order.setOrderStatus("NEW");
		order.setOrderCreatedTimestamp(new Date());
		order.setOrderLastUpdatedTimestamp(null);

		return this.orderRepository.save(order);
	}
	
	private Double getOrderSubTotal(OrderEntity order) {
        return order.getOrderItemEntity().stream().map(itemEntity -> itemEntity.getOrderItemPrice() * itemEntity.getOrderItemQuantity()).reduce(0.0, Double::sum);
    }

	private Double getOrderTax(OrderEntity order) {
		return order.getOrderItemEntity().stream().map(itemEntity -> itemEntity.getOrderItemPrice() * itemEntity.getOrderItemQuantity() * 0.02).reduce(0.0, Double::sum);
	}
	
	private Double getOrderShippingCharges(OrderEntity order) {
		return order.getOrderItemEntity().stream().map(itemEntity -> itemEntity.getOrderItemQuantity() * 2.0).reduce(0.0, Double::sum);
	}
	
    private Double getOrderTotal(OrderEntity order){
        return getOrderSubTotal(order) + getOrderTax(order) + getOrderShippingCharges(order);
    }

	public OrderEntity updateOrder(Long orderId, OrderEntity order) throws ResourceNotFoundException {
		Optional<OrderEntity> orderEntity = this.orderRepository.findById(orderId);
		if(orderEntity.isEmpty()) {
			throw new ResourceNotFoundException("Order Not Found with id ::" + orderId);
		}
		Optional<AddressEntity> addressEntity = this.addressRepository.findById(orderEntity.get().getOrderShippingAddressId());
		if(addressEntity.isEmpty()) {
			throw new ResourceNotFoundException("Address Not Found for the order with id ::" + orderId);
		}
		order.setOrderSubtotal(getOrderSubTotal(order));
		order.setOrderTax(getOrderTax(order));
		order.setOrderShippingCharges(getOrderShippingCharges(order));
		order.setOrderTotal(getOrderTotal(order));
		order.setOrderStatus("UPDATED");
		order.setOrderLastUpdatedTimestamp(new Date());

		return this.orderRepository.save(order);
	}

	public void cancelOrder(Long orderId) throws ResourceNotFoundException {
		Optional<OrderEntity> orderEntity = this.orderRepository.findById(orderId);
		if(orderEntity.isEmpty()) {
			throw new ResourceNotFoundException("Order Not Found with id ::" + orderId);
		}
		orderEntity.get().setOrderStatus("CANCELLED");
		this.orderRepository.save(orderEntity.get());
	}

	public void addOrderBulk(List<OrderEntity> orders) throws ResourceNotFoundException {
		orders.stream().forEach((order) -> {
			try{
				createOrder(order);
			}
			catch(ResourceNotFoundException exception) {
				System.out.println(exception);
			}
		});
	}
	
	public void cancelOrderBulk(List<Long> orderIds) throws ResourceNotFoundException {
		orderIds.stream().forEach((orderId) -> {
			try {
				cancelOrder(orderId);
			}
			catch(ResourceNotFoundException exception) {
				System.out.println(exception);
			}
		});
	}
	
	
	
	

}
