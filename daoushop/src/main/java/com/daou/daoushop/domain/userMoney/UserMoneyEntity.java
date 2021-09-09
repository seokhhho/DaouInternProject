package com.daou.daoushop.domain.userMoney;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.daou.daoushop.domain.user.UserEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity(name="user_money")
public class UserMoneyEntity {
	
	@Id
	@GeneratedValue
	private Integer userMoneyId;
	@OneToOne
	@JoinColumn(name="user_number")
	private UserEntity user;
	private int fund;
	
	@Builder
	public UserMoneyEntity(UserEntity user, int fund) {
		this.user = user; 
		this.fund = fund;
	}
	
	public int deduct(int usingFund) {
		this.fund -= usingFund;
		return fund;
	}

	public int deposit(int usedMoney) {
		this.fund += usedMoney;
		return fund;
		
	}
	
}
