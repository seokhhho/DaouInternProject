package com.daou.daoushop.domain.point;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity(name="point")
public class PointEntity {

	@Id
	@GeneratedValue
	private Integer pointId;
	private Integer userNumber;
	@DateTimeFormat
	private String valid;
	private int pointMoney;

	@Builder
	public PointEntity(Integer pointId, Integer userNumber, String valid, int pointMoney) {
		this.userNumber = userNumber;
		this.valid = valid;
		this.pointMoney = pointMoney;
	}

	
}
