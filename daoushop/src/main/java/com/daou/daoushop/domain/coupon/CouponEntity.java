package com.daou.daoushop.domain.coupon;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.daou.daoushop.domain.payment.PaymentEntity;
import com.daou.daoushop.domain.user.UserEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity(name="coupon")
public class CouponEntity {
	
	@Id
	@GeneratedValue
	private Integer couponId;

	@ManyToOne
	@JoinColumn(name="user_number")
	private UserEntity user;
	@Enumerated(EnumType.STRING)
	private DiscountRate discountRate;
	@Enumerated(EnumType.STRING)
	private IsUsed isUsed;
	private String couponName;
	
	@OneToOne(mappedBy = "coupon")
	private PaymentEntity payment;
	
	@Builder
	public CouponEntity(UserEntity user, DiscountRate discountRate, IsUsed isUsed , String couponName) {
		this.user = user;
		this.discountRate = discountRate;
		this.isUsed = isUsed;
		this.couponName = couponName;
	}
	
	public void usingCoupon() {
		this.isUsed = IsUsed.TRUE;
	}
	
	

	



}
