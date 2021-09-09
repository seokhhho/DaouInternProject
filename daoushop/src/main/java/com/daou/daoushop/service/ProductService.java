package com.daou.daoushop.service;

import java.util.List;
import java.util.stream.Collectors;

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
}
