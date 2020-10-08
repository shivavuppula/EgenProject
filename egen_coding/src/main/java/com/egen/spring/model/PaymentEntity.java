package com.egen.spring.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "payments")
public class PaymentEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "order_payment_confirmation_number")
	private Long orderPaymentConfirmationNumber;
	
	@Column(name = "order_payment_method")
	private String orderPaymentMethod;
	
	@Column(name = "order_payment_amount")
	private Double orderPaymentAmount;
	
	@Column(name = "order_payment_date")
	private Date orderPaymentDate;

	@Column(name = "order_id")
	private Long orderId;
	
	@ManyToOne
    @JoinColumn(name = "order_id", insertable = false, updatable = false)
	private OrderEntity orderEntity;
	
	@Column(name = "order_payment_address_id")
	private Long orderPaymentAddressId;
	
	@ManyToOne
    @JoinColumn(name = "order_payment_address_id", insertable = false, updatable = false)
	private AddressEntity orderPaymentAddressEntity;

	public Long getOrderPaymentConfirmationNumber() {
		return orderPaymentConfirmationNumber;
	}

	public void setOrderPaymentConfirmationNumber(Long orderPaymentConfirmationNumber) {
		this.orderPaymentConfirmationNumber = orderPaymentConfirmationNumber;
	}

	public String getOrderPaymentMethod() {
		return orderPaymentMethod;
	}

	public void setOrderPaymentMethod(String orderPaymentMethod) {
		this.orderPaymentMethod = orderPaymentMethod;
	}

	public Double getOrderPaymentAmount() {
		return orderPaymentAmount;
	}

	public void setOrderPaymentAmount(Double orderPaymentAmount) {
		this.orderPaymentAmount = orderPaymentAmount;
	}

	public Date getOrderPaymentDate() {
		return orderPaymentDate;
	}

	public void setOrderPaymentDate(Date orderPaymentDate) {
		this.orderPaymentDate = orderPaymentDate;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public OrderEntity getOrderEntity() {
		return orderEntity;
	}

	public void setOrderEntity(OrderEntity orderEntity) {
		this.orderEntity = orderEntity;
	}

	public Long getOrderPaymentAddressId() {
		return orderPaymentAddressId;
	}

	public void setOrderPaymentAddressId(Long orderPaymentAddressId) {
		this.orderPaymentAddressId = orderPaymentAddressId;
	}

	public AddressEntity getOrderPaymentAddressEntity() {
		return orderPaymentAddressEntity;
	}

	public void setOrderPaymentAddressEntity(AddressEntity orderPaymentAddressEntity) {
		this.orderPaymentAddressEntity = orderPaymentAddressEntity;
	}

}
