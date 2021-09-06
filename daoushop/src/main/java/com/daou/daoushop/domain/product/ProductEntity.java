package com.daou.daoushop.domain.product;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.daou.daoushop.domain.payedProduct.PayedProductEntity;

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
	
	@OneToMany(mappedBy = "product")
	private Set<PayedProductEntity> payedProducts = new HashSet<>();
	
	@Builder
	public ProductEntity(String productName, int price, int stock) {
		this.productName = productName;
		this.price = price;
		this.stock = stock;
	}
	
	public int buyProduct(int amount) {
		this.stock -= amount;
		return stock;
	}
	
}
