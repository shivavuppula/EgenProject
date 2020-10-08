package com.egen.spring.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class OrderEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "order_id")
	private Long orderId;
	
	@Column(name = "order_status")
	private String orderStatus;
	
	@Column(name = "order_customer_id")
	private Long orderCustomerId;
	
	@Column(name = "order_subtotal")
	private Double orderSubtotal;
	
	@Column(name = "order_tax")
	private Double orderTax;
	
	@Column(name = "order_shipping_charges")
	private Double orderShippingCharges;
	
	@Column(name = "order_total")
	private Double orderTotal;
	
	@Column(name = "order_shipping_address_id")
	private Long orderShippingAddressId;
	
	@ManyToOne
    @JoinColumn(name = "order_shipping_address_id", insertable = false, updatable = false)
	private AddressEntity orderShippingAddressEntity;
	
	@OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
	private List<ItemEntity> orderItemEntity;
	
	@Column(name = "order_created_timestamp")
    private Date orderCreatedTimestamp;

    @Column(name = "order_last_updated_timestamp")
    private Date orderLastUpdatedTimestamp;
	
	public Date getOrderCreatedTimestamp() {
		return orderCreatedTimestamp;
	}

	public void setOrderCreatedTimestamp(Date orderCreatedTimestamp) {
		this.orderCreatedTimestamp = orderCreatedTimestamp;
	}

	public Date getOrderLastUpdatedTimestamp() {
		return orderLastUpdatedTimestamp;
	}

	public void setOrderLastUpdatedTimestamp(Date orderLastUpdatedTimestamp) {
		this.orderLastUpdatedTimestamp = orderLastUpdatedTimestamp;
	}

	public AddressEntity getOrderShippingAddressEntity() {
		return orderShippingAddressEntity;
	}

	public void setOrderShippingAddressEntity(AddressEntity orderShippingAddressEntity) {
		this.orderShippingAddressEntity = orderShippingAddressEntity;
	}

	public List<ItemEntity> getOrderItemEntity() {
		return orderItemEntity;
	}

	public void setOrderItemEntity(List<ItemEntity> orderItemEntity) {
		this.orderItemEntity = orderItemEntity;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Long getOrderCustomerId() {
		return orderCustomerId;
	}

	public void setOrderCustomerId(Long orderCustomerId) {
		this.orderCustomerId = orderCustomerId;
	}

	public Double getOrderSubtotal() {
		return orderSubtotal;
	}

	public void setOrderSubtotal(Double orderSubtotal) {
		this.orderSubtotal = orderSubtotal;
	}

	public Double getOrderTax() {
		return orderTax;
	}

	public void setOrderTax(Double orderTax) {
		this.orderTax = orderTax;
	}

	public Double getOrderShippingCharges() {
		return orderShippingCharges;
	}

	public void setOrderShippingCharges(Double orderShippingCharges) {
		this.orderShippingCharges = orderShippingCharges;
	}

	public Double getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(Double orderTotal) {
		this.orderTotal = orderTotal;
	}

	public Long getOrderShippingAddressId() {
		return orderShippingAddressId;
	}

	public void setOrderShippingAddressId(Long orderShippingAddressId) {
		this.orderShippingAddressId = orderShippingAddressId;
	}
	
}
