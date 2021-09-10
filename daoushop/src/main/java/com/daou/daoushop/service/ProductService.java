package com.daou.daoushop.service;

import java.util.List;
import java.util.stream.Collectors;

<<<<<<< Updated upstream
=======
import com.daou.daoushop.domain.product.ProductEntity;
import com.daou.daoushop.web.dto.ProductAmountDto;
import org.jetbrains.annotations.NotNull;
>>>>>>> Stashed changes
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daou.daoushop.domain.product.ProductRepository;
import com.daou.daoushop.web.dto.ProductResponseDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductService {

	private final ProductRepository productRepository;
	
	@Transactional(readOnly = true)
	public List<ProductResponseDto> readProductList(){
		return productRepository.findAll().stream()
				.map(ProductResponseDto::new)
				.collect(Collectors.toList());
	}
<<<<<<< Updated upstream
=======

	public  int getTotalPrice(@NotNull List<ProductAmountDto> buyedProducts, int totalPrice) {
		for(ProductAmountDto p : buyedProducts) {
			ProductEntity product = productRepository.findById(p.getProductId())
					.orElseThrow(() -> new IllegalArgumentException( p.getProductId() +"번 상품이 존재 하지 않습니다."));
			if(product.getStock() < p.getAmount()) {
				throw new IllegalArgumentException("재고가 부족합니다.");
			}
			totalPrice += product.getPrice() * p.getAmount();
		}
		return totalPrice;
	}


>>>>>>> Stashed changes
}
