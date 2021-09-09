package com.daou.daoushop.web.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PayRequestDto {
	
	private int totalPrice;
	private int discountedPrice;
	private int couponId;
	private List<UsingPointDto> usingPoints;
	private int usingFund;
	private int pgPayMoney;
	private int userNumber;
	private List<ProductAmountDto> products;

	@Builder
	public PayRequestDto(int totalPrice, int discountedPrice, int couponId, List<UsingPointDto> usingPoints,
			int usingFund, int pgPayMoney, int userNumber, List<ProductAmountDto> products) {
		this.totalPrice = totalPrice;
		this.discountedPrice = discountedPrice;
		this.couponId = couponId;
		this.usingPoints = usingPoints;
		this.usingFund = usingFund;
		this.pgPayMoney = pgPayMoney;
		this.userNumber = userNumber;
		this.products = products;
	}
	
	
}

