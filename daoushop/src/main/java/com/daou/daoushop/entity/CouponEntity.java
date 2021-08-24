package com.daou.daoushop.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity(name="Coupon")
public class CouponEntity {
	
	@Id
	private String user;
	private int fivePercent;
	private int tenPercent;
	private int twentyPercent;

}
