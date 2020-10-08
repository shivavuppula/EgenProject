package com.egen.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "address")
public class AddressEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "order_address_id")
	private Long orderAddressId;
	
	@Column(name = "order_address_line_1")
	private String orderAddressLine1;
	
	@Column(name = "order_address_line_2")
	private String orderAddressLine2;
	
	@Column(name = "order_city")
	private String orderCity;
	
	@Column(name = "order_state")
	private String orderState;
	
	@Column(name = "order_zip")
	private String orderZip;
	
	public Long getOrderAddressId() {
		return orderAddressId;
	}

	public void setOrderAddressId(Long orderAddressId) {
		this.orderAddressId = orderAddressId;
	}

	public String getOrderAddressLine1() {
		return orderAddressLine1;
	}

	public void setOrderAddressLine1(String orderAddressLine1) {
		this.orderAddressLine1 = orderAddressLine1;
	}

	public String getOrderAddressLine2() {
		return orderAddressLine2;
	}

	public void setOrderAddressLine2(String orderAddressLine2) {
		this.orderAddressLine2 = orderAddressLine2;
	}

	public String getOrderCity() {
		return orderCity;
	}

	public void setOrderCity(String orderCity) {
		this.orderCity = orderCity;
	}

	public String getOrderState() {
		return orderState;
	}

	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}

	public String getOrderZip() {
		return orderZip;
	}

	public void setOrderZip(String orderZip) {
		this.orderZip = orderZip;
	}

}
