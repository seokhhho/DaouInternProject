package com.daou.daoushop.domain.user;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.daou.daoushop.domain.coupon.CouponEntity;
import com.daou.daoushop.domain.point.PointEntity;
import com.daou.daoushop.domain.userMoney.UserMoneyEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity(name="user")
public class UserEntity {

	@Id
	@GeneratedValue
	private Integer userNumber;
	private String id;
	private String name;
	
	@OneToMany(mappedBy = "user")
	private Set<CouponEntity> coupons = new HashSet<>();
	
	@OneToMany(mappedBy = "user")
	private Set<PointEntity> points = new HashSet<>();

	@OneToOne(mappedBy = "user")
	private UserMoneyEntity userMoney;
	
	@Builder
	public UserEntity(String id, String name) {
		this.id = id;
		this.name = name;
	}
	
	
	
}
