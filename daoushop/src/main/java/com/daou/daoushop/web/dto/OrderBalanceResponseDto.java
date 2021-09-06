package com.daou.daoushop.web.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OrderBalanceResponseDto {
	
	private List<CouponDto> coupons;
	private List<PointDto> points;
	private int fund;
	
	@Builder
	public OrderBalanceResponseDto(List<PointDto> points, List<CouponDto> coupons, int fund) {
		this.points = points;
		this.coupons = coupons;
		this.fund = fund;
	}
	
	
}
