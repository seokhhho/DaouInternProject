package com.daou.daoushop.domain.usedPoint;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.daou.daoushop.domain.payment.PaymentEntity;
import com.daou.daoushop.domain.point.PointEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity(name="used_point")
public class UsedPointEntity {

	@Id
	@GeneratedValue
	private Integer usedPointId;
	
	@ManyToOne
	@JoinColumn(name="payment_id")
	private PaymentEntity payment;
	
	@ManyToOne
	@JoinColumn(name="point_id")
	private PointEntity point;
	
	private int usedMoney;

	@Builder
	public UsedPointEntity(Integer usedPointId, PaymentEntity payment, PointEntity point, int usedMoney) {
		this.payment = payment;
		this.point = point;
		this.usedMoney = usedMoney;
	}
	
	
	
}
