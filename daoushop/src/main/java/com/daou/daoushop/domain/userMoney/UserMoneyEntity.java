package com.daou.daoushop.domain.userMoney;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity(name="user_money")
public class UserMoneyEntity {
	
	@Id
	@GeneratedValue
	private Integer userMoneyId;
}
