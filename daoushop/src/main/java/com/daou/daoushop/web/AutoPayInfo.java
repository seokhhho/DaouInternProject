package com.daou.daoushop.web;

import java.util.List;

import com.daou.daoushop.domain.coupon.CouponEntity;
import com.daou.daoushop.web.dto.UsingPointDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
public class AutoPayInfo {
	
	public int totalPrice;
	public int discountedPrice;
	public CouponEntity usingCoupon;
	public List<UsingPointDto> usingPoints;
	public int usingFund;
	public int pgPayMoney;
	
}
