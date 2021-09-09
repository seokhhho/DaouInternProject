package com.daou.daoushop.web.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
public class RefundResponseDto {

	private List<CouponDto> coupons;
	private List<PointDto> points;
	private int fund;
	private int giveBackMoney;
	
	@Builder
	public RefundResponseDto(List<PointDto> points, List<CouponDto> coupons, int fund , int giveBackMoney) {
		this.points = points;
		this.coupons = coupons;
		this.fund = fund;
		this.giveBackMoney = giveBackMoney;
	}
	
}
