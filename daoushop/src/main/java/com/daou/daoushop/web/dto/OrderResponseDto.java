package com.daou.daoushop.web.dto;

import java.util.ArrayList;
import java.util.List;

import com.daou.daoushop.domain.coupon.CouponEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
public class OrderResponseDto {

	private int totalPrice;
	private int discountedPrice;
	private int couponId;
	private String couponName;
	private int discountRate;
	private List<UsingPointDto> usingPoints;
	private int usingFund;
	private int pgPayMoney;
	
	
	@Builder
	public OrderResponseDto(int totalPrice, int discountedPrice, int couponId, String couponName, int discountRate,
			List<UsingPointDto> usingPoints, int usingFund, int pgPayMoney) {
		
		this.totalPrice = totalPrice;
		this.discountedPrice = discountedPrice;
		this.couponId = couponId;
		this.couponName = couponName;
		this.discountRate = discountRate;
		this.usingPoints = usingPoints;
		this.usingFund = usingFund;
		this.pgPayMoney = pgPayMoney;
	}
	
}
