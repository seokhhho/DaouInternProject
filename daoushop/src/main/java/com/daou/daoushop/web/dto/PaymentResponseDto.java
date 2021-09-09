package com.daou.daoushop.web.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class PaymentResponseDto {

	private int paymentId;
	private int couponId;
	private String couponName;
	private int usedFund;
	private int usedMoney;
	private int totalPrice;
	private int discountedPrice;
	private List<UsedPointDto> usedPoints;
	private List<PayedProductDto> payedProducts;

	@Builder
	public PaymentResponseDto(int paymentId, int couponId, String couponName, int usedFund, int usedMoney,
			int totalPrice, int discountedPrice, List<UsedPointDto> usedPoints, List<PayedProductDto> payedProducts) {
		this.paymentId = paymentId;
		this.couponId = couponId;
		this.couponName = couponName;
		this.usedFund = usedFund;
		this.usedMoney = usedMoney;
		this.totalPrice = totalPrice;
		this.discountedPrice = discountedPrice;
		this.usedPoints = usedPoints;
		this.payedProducts = payedProducts;
	}
	

	
	
	
}
