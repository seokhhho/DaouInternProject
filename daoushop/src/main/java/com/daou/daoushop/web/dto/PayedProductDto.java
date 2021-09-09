package com.daou.daoushop.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PayedProductDto {

	private int payedProductId;
	private int productId;
	private String productName;
	private int amount;

	@Builder
	public PayedProductDto(int payedProductId, int productId, int amount, String productName) {
		this.productName = productName;
		this.payedProductId = payedProductId;
		this.productId = productId;
		this.amount = amount;
	}
	
	
	
}
