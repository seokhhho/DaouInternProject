package com.daou.daoushop.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daou.daoushop.domain.coupon.CouponEntity;
import com.daou.daoushop.domain.coupon.CouponRepository;
import com.daou.daoushop.domain.point.PointEntity;
import com.daou.daoushop.domain.point.PointRepository;
import com.daou.daoushop.domain.product.ProductEntity;
import com.daou.daoushop.domain.product.ProductRepository;
import com.daou.daoushop.domain.user.UserEntity;
import com.daou.daoushop.domain.user.UserRepository;
import com.daou.daoushop.web.AutoPayInfo;
import com.daou.daoushop.web.dto.OrderRequestDto;
import com.daou.daoushop.web.dto.OrderResponseDto;
import com.daou.daoushop.web.dto.ProductAmountDto;
import com.daou.daoushop.web.dto.UsingPointDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OrderService {

	private final ProductRepository productRepository;
	private final UserRepository userRepository;
	private final PointRepository pointRepository;
	private final CouponRepository couponRepository;
	
	
	/*자동 결제 시 결제 정보 가져오기*/
	@Transactional
	public OrderResponseDto findAutoPayInfo(OrderRequestDto requestDto) {
		
		List<ProductAmountDto> buyedProducts = requestDto.getProducts();
		
		/*반환해 줄 객체 관련 데이터*/
		int totalPrice = 0;
		int discountedPrice = 0;
		CouponEntity usingCoupon = null;
		List<UsingPointDto> usingPoints = new ArrayList<>();
		int usingFund = 0;
		int pgPayMoney = 0;
		
		AutoPayInfo autoPayInfo = new AutoPayInfo(0,0,null,new ArrayList<>(),0,0);
		
		/*상품 정보를 통해 총 가격 구하기*/
		for(ProductAmountDto p : buyedProducts) {
			ProductEntity product = productRepository.findById(p.getProductId())
					.orElseThrow(() -> new IllegalArgumentException( p.getProductId() +"번 상품이 존재 하지 않습니다."));
			if(product.getStock() < p.getAmount()) {
				throw new IllegalArgumentException("재고가 부족합니다.");
			}
			totalPrice += product.getPrice() * p.getAmount();
//			autoPayInfo.setTotalPrice(autoPayInfo.getTotalPrice() + (product.getPrice() * p.getAmount()));
		}
		
		/*유저 정보 불러오기*/
		UserEntity user = userRepository.findById(requestDto.getUserNumber())
					.orElseThrow(() -> new IllegalArgumentException( "해당 유저가 존재 하지 않습니다."));
		
		
		/*쿠폰 정보 불러와 가장 적합한 쿠폰 선택 후 할인 가격 적용*/
		Set<CouponEntity> coupons = user.getCoupons();
		int discountRate = 0;
		
		for(CouponEntity c:coupons) {
			if(!c.getIsUsed().isUsed() && c.getDiscountRate().getMinPrice() <= totalPrice 
					&& discountRate < c.getDiscountRate().getRate()) { 
				usingCoupon = c;
				discountRate = c.getDiscountRate().getRate();
			}
		}
		
		discountedPrice = totalPrice;
		
		if(usingCoupon != null) {
			discountedPrice = (int)(totalPrice - ((float)totalPrice / 100 * usingCoupon.getDiscountRate().getRate()));
		}
		
		
		/*사용 할 포인트 적용*/
		List<PointEntity> points = null;
		points = new ArrayList(user.getPoints());
		Collections.sort(points);
		
		int remainMoney = discountedPrice;
		
		for(PointEntity p : points) {
			if(p.getPointMoney() >= remainMoney) {
				usingPoints.add(UsingPointDto.builder()
						.pointId(p.getPointId())
						.valid(p.getValid())
						.usingMoney(remainMoney)
						.pointName(p.getPointName())
						.build());
				remainMoney = 0;
				break;
			}else {
				usingPoints.add(UsingPointDto.builder()
						.pointId(p.getPointId())
						.valid(p.getValid())
						.usingMoney(p.getPointMoney())
						.pointName(p.getPointName())
						.build());
				remainMoney -= p.getPointMoney();
			}
		}
		
		/*남은 돈 있을 시 적립금 적용*/
		if(remainMoney > 0) {
			if(user.getUserMoney().getFund() >= remainMoney) {
				usingFund = remainMoney;
				remainMoney = 0;
			}else {
				usingFund = user.getUserMoney().getFund();
				remainMoney -= usingFund;
			}
		}
		
		/*남은 돈 있을 시 결제금액*/
		pgPayMoney = remainMoney;
		
		return OrderResponseDto.builder()
				.totalPrice(totalPrice)
				.discountedPrice(discountedPrice)
				.couponId(usingCoupon.getCouponId())
				.discountRate(usingCoupon.getDiscountRate().getRate())
				.usingPoints(usingPoints)
				.usingFund(usingFund)
				.pgPayMoney(pgPayMoney)
				.build();
	}

}
