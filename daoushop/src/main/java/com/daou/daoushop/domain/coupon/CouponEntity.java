package com.daou.daoushop.domain.coupon;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity(name="coupon")
public class CouponEntity {
	
	@Id
	private String couponCode;
	private String id;
	private int discountRate;
	private int minAmount;
	
	@Builder
	public CouponEntity(String couponCode, String id, int discountRate, int minAmount) {
		this.couponCode = couponCode;
		this.id = id;
		this.discountRate = discountRate;
		this.minAmount = minAmount;
	}
	



}
