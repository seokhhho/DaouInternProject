package com.daou.daoushop.web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daou.daoushop.service.ProductService;
import com.daou.daoushop.web.dto.ProductResponseDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/product")
public class ProductController {

	private final ProductService productService;
	
	@GetMapping(value = "/list")
	public List<ProductResponseDto> findAll(){
		return productService.ReadProductList();
	}
}
