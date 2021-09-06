package com.daou.daoushop.web.dto;

import lombok.Builder;

public class UsedPointDto {
	private int usedPointId;
	private int pointId;
	private int usedMoney;
	private String pointName;
	private String valid;

	@Builder
	public UsedPointDto(int usedPointId , int pointId, int usedMoney, String pointName, String valid) {
		this.usedPointId = usedPointId;
		this.pointId = pointId;
		this.usedMoney = usedMoney;
		this.pointName = pointName;
		this.valid = valid;
	}
	
	
}
