package com.daou.daoushop.domain.userMoney;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.daou.daoushop.domain.user.UserEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity(name="user_money")
public class UserMoneyEntity implements Serializable {
	
	@Id
	@OneToOne
	@JoinColumn(name="user_number")
	private UserEntity user;
	private int fund;
	
	public UserMoneyEntity(UserEntity user, int fund) {
		this.user = user;
		this.fund = fund;
	}
	
}
