package com.daou.daoushop.domain.payedProduct;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.daou.daoushop.domain.payment.PaymentEntity;
import com.daou.daoushop.domain.product.ProductEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity(name="payed_product")
public class PayedProductEntity {

	@Id
	@GeneratedValue
	private Integer payedProductId;
	
	@ManyToOne
	@JoinColumn(name = "payment_id")
	private PaymentEntity payment;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private ProductEntity product;
	
	private int amount;

	@Builder
	public PayedProductEntity(PaymentEntity payment, ProductEntity product, int amount) {
		this.payment = payment;
		this.product = product;
		this.amount = amount;
	}
	
	
	
}
