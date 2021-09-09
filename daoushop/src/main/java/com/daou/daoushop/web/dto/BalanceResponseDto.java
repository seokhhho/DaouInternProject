package com.daou.daoushop.web.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
public class BalanceResponseDto {

	private List<CouponDto> coupons;
	private List<PointDto> points;
	private int fund;
	
	@Builder
	public BalanceResponseDto(List<PointDto> points, List<CouponDto> coupons, int fund) {
		this.points = points;
		this.coupons = coupons;
		this.fund = fund;
	}
	
}
