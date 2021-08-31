package com.daou.daoushop.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daou.daoushop.domain.coupon.CouponRepository;
import com.daou.daoushop.domain.point.PointRepository;
import com.daou.daoushop.domain.product.ProductEntity;
import com.daou.daoushop.domain.product.ProductRepository;
import com.daou.daoushop.domain.user.UserRepository;
import com.daou.daoushop.web.dto.OrderRequestDto;
import com.daou.daoushop.web.dto.OrderResponseDto;
import com.daou.daoushop.web.dto.ProductAmountDto;

import lombok.RequiredArgsConstructor;

//@RequiredArgsConstructor
//@Service
public class OrderService {

//	private final ProductRepository productRepository;
//	private final UserRepository userRepository;
//	private final PointRepository pointRepository;
//	private final CouponRepository couponRepository;
//	
//	@Transactional
//	public OrderResponseDto findAutoPayInfo(OrderRequestDto requestDto) {
//		
//		int totalPrice = 0;
//		List<ProductAmountDto> products = requestDto.getProducts();
//		
//		for(ProductAmountDto p : products) {
//			ProductEntity product = productRepository.findById(p.getProductId())
//					.orElseThrow(() -> new IllegalArgumentException( p.getProductId() +"번 상품이 존재 하지 않습니다."));
//			if(product.getStock() < p.getAmount()) {
//				throw new IllegalArgumentException("재고가 부족합니다.");
//			}
//			totalPrice += product.getPrice() * p.getAmount();
//		}
//		
//		couponRepository.findAllById(requestDto.getUserNumber());
//	}

}
