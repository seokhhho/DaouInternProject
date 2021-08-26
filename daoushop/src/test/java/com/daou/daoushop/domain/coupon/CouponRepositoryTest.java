package com.daou.daoushop.domain.coupon;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CouponRepositoryTest {

	@Autowired
	CouponRepository couponRepository;
	
	@AfterEach
	public void cleanup() {
		couponRepository.deleteAll();
	}
	
	@Test
	@DisplayName("쿠폰 저장 테스트")
	public void saveCoupon() {
		
		String couponCode = "SDF-9876";
		String id = "hi1234";
		int discountRate = 10;
		int minAmount = 20000;
		
		couponRepository.save(CouponEntity.builder()
				.couponCode(couponCode)
				.id(id)
				.discountRate(discountRate)
				.minAmount(minAmount)
				.build());
		
		List<CouponEntity> couponList = couponRepository.findAll();
		
		CouponEntity coupon = couponList.get(0);
		assertThat(coupon.getCouponCode());
		assertThat(coupon.getId());
		assertThat(coupon.getDiscountRate());
		assertThat(coupon.getMinAmount());
	}
}
