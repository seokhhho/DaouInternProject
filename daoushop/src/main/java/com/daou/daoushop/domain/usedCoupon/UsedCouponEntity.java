package com.daou.daoushop.domain.usedCoupon;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity(name="used_coupon")
public class UsedCouponEntity {

	@Id
	@GeneratedValue
	private Integer couponId;
}
