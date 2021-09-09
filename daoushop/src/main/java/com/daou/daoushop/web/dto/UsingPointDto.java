package com.daou.daoushop.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UsingPointDto {
	
	private Integer pointId;
	private String valid;
	private int usingMoney;
	private String pointName;
	
	@Builder
	public UsingPointDto(Integer pointId, String valid, int usingMoney, String pointName) {
		this.pointId = pointId;
		this.valid = valid;
		this.usingMoney = usingMoney;
		this.pointName = pointName;
	}
	
	
}
