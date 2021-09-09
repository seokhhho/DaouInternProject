package com.daou.daoushop.web.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PointDto {

	private Integer pointId;
	private String valid;
	private int pointMoney;
	private String pointName;
	
	@Builder
	public PointDto(Integer pointId, String valid, int pointMoney, String pointName) {
		this.pointId = pointId;
		this.valid = valid;
		this.pointMoney = pointMoney;
		this.pointName = pointName;
	}
	
}
