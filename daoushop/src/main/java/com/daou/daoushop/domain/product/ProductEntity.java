package com.daou.daoushop.domain.product;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity(name="product")
public class ProductEntity {

	@Id
	@GeneratedValue
	private Integer productId;
}
