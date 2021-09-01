package com.daou.daoushop.domain.product;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity(name="product")
public class ProductEntity {

	@Id
	@GeneratedValue
	private Integer productId;
	private String productName;
	private int price;
	private int stock;
	
	@Builder
	public ProductEntity(String productName, int price, int stock) {
		this.productName = productName;
		this.price = price;
		this.stock = stock;
	}
	
}
