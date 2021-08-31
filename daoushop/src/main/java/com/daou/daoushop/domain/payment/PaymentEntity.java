package com.daou.daoushop.domain.payment;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity(name="payment")
public class PaymentEntity {

	@Id
	@GeneratedValue
	private Integer paymentId; 
}
