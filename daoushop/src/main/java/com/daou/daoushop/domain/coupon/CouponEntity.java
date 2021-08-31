package com.daou.daoushop.domain.coupon;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity(name="coupon")
public class CouponEntity {
	
	@Id
	@GeneratedValue
	private Integer couponId;
	private int userNumber;
	@Enumerated(EnumType.STRING)
	private DiscountRate discountRate;
	private int isUsed;
	
	@Builder
	public CouponEntity(int userNumber, DiscountRate discountRate, int isUsed) {
		this.userNumber = userNumber;
		this.discountRate = discountRate;
		this.isUsed = isUsed;
	}
	
	

	



}
