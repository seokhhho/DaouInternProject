package com.daou.daoushop.domain.usedPoint;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity(name="used_point")
public class UsedPointEntity {

	@Id
	@GeneratedValue
	private Integer usedPointId;
}
