package com.daou.daoushop.domain.coupon;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity(name="coupon")
public class CouponEntity {
	
	@Id
	private String user;
	private int fivePercent;
	private int tenPercent;
	private int twentyPercent;
	
	@Builder
	public CouponEntity(String user, int fivePercent, int tenPercent, int twentyPercent) {
		this.user = user;
		this.fivePercent = fivePercent;
		this.tenPercent = tenPercent;
		this.twentyPercent = twentyPercent;
	}


}
