package com.daou.daoushop.domain.payment;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.daou.daoushop.domain.coupon.CouponEntity;
import com.daou.daoushop.domain.payedProduct.PayedProductEntity;
import com.daou.daoushop.domain.usedPoint.UsedPointEntity;
import com.daou.daoushop.domain.user.UserEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity(name="payment")
public class PaymentEntity {

	@Id
	@GeneratedValue
	private Integer paymentId;
	
	@ManyToOne
	@JoinColumn(name="user_number")
	private UserEntity user;
	
	@OneToOne
	@JoinColumn(name="coupon_id")
	private CouponEntity coupon;
	
	private int usedFund;
	private int usedMoney;
	
	@OneToMany(mappedBy = "payment")
	private Set<UsedPointEntity> usedPoints = new HashSet<>();
	
	@OneToMany(mappedBy = "payment")
	private Set<PayedProductEntity> payedProducts = new HashSet<>();
	
	@Builder
	public PaymentEntity(UserEntity user, CouponEntity coupon, int usedFund, int usedMoney) {
		this.user = user;
		this.coupon = coupon;
		this.usedFund = usedFund;
		this.usedMoney = usedMoney;
	}
	
	
	
}
