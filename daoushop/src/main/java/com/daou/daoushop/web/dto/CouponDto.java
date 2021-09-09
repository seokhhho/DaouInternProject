package com.daou.daoushop.web.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CouponDto {

	private Integer couponId;
	private int discountRate;
	private String couponName;
	private int minPrice;
	
	@Builder
	public CouponDto(Integer couponId, int discountRate, String couponName , int minPrice) {
		this.couponId = couponId;
		this.discountRate = discountRate;
		this.couponName = couponName;
		this.minPrice = minPrice;
	}
	
	
}
