package com.daou.daoushop.web.dto;

import com.daou.daoushop.domain.product.ProductEntity;

import lombok.Getter;

@Getter
public class ProductResponseDto {

	private Integer productId;
	private String productName;
	private int price;
	private int stock; 
	
	public ProductResponseDto(ProductEntity entity) {
		this.productId = entity.getProductId();
		this.productName = entity.getProductName();
		this.price = entity.getPrice();
		this.stock = entity.getStock();
	}
	
}
