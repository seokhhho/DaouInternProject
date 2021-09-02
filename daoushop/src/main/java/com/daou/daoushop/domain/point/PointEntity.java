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
public class PointEntity implements Comparable<PointEntity> {

	@Id
	@GeneratedValue
	private Integer pointId;
	
	@ManyToOne
	@JoinColumn(name="user_number")
	private UserEntity user;
	@DateTimeFormat
	private String valid;
	private int pointMoney;
	private String pointName;

	@Builder
	public PointEntity(Integer pointId, UserEntity user, String valid, int pointMoney , String pointName) {
		this.user = user;
		this.valid = valid;
		this.pointMoney = pointMoney;
		this.pointName = pointName;
	}

	@Override
	public int compareTo(PointEntity o) {
		return this.valid.compareTo(o.valid);
	}

	
}
