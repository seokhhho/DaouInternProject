package com.daou.daoushop.web.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderRequestDto {
	
	private String userNumber;
	private List<ProductAmountDto> products;

	@Builder
	public OrderRequestDto(String id, List<ProductAmountDto> products) {
		this.userNumber = userNumber;
		this.products = products;
	}
	
}
