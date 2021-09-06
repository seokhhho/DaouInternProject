package com.daou.daoushop.web.dto;

import java.util.List;

import lombok.Builder;

public class PaymentResponseDto {

	private int paymentId;
	private int couponId;
	private String couponName;
	private int usedFund;
	private int usedMoney;
	private List<UsedPointDto> usedPoints;
	private List<PayedProductDto> payedProducts;
	
	@Builder
	public PaymentResponseDto(int paymentId, int couponId, String couponName, int usedFund, int usedMoney,
			List<UsedPointDto> usedPoints, List<PayedProductDto> payedProducts) {
		this.paymentId = paymentId;
		this.couponId = couponId;
		this.couponName = couponName;
		this.usedFund = usedFund;
		this.usedMoney = usedMoney;
		this.usedPoints = usedPoints;
		this.payedProducts = payedProducts;
	}
	
	
}
