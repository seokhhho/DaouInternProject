package com.daou.daoushop.domain.coupon;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum IsUsed {

	TRUE(true),
	FALSE(false);
	
	private final boolean isUsed;
}
