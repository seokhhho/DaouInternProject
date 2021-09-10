package com.daou.daoushop.domain.coupon;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DiscountRate {
	
	FIVE(5,10000),
	TEN(10,20000),
	TWENTY(20,30000);
	
	
	private final Integer rate;
	private final Integer minPrice;


}
