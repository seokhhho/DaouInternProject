package com.daou.daoushop.web.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderRequestDto {
	
	private Integer userNumber;
	private List<ProductAmountDto> products;

	@Builder
	public OrderRequestDto(Integer userNumber, List<ProductAmountDto> products) {
		this.userNumber = userNumber;
		this.products = products;
	}
	
}
