package com.daou.daoushop.domain.point;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

import com.daou.daoushop.domain.user.UserEntity;

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
	
	@ManyToOne
	@JoinColumn(name="user_number")
	private UserEntity user;
	@DateTimeFormat
	private String valid;
	private int pointMoney;

	@Builder
	public PointEntity(Integer pointId, UserEntity user, String valid, int pointMoney) {
		this.user = user;
		this.valid = valid;
		this.pointMoney = pointMoney;
	}

	
}
