package com.daou.daoushop.domain.point;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity(name="point")
public class PointEntity {

	@Id
	@GeneratedValue
	private Integer pointId;
}
