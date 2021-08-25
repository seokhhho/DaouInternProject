package com.daou.daoushop.domain.coupon;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
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
	public void ÄíÆù_ÀÔ·ÂÇÏ±â() {
		
		String user = "Ã¶¼ö";
		int fivePercent = 1;
		int tenPercent = 2;
		int twentyPercent = 1;
		
		couponRepository.save(CouponEntity.builder()
				.user(user)
				.fivePercent(fivePercent)
				.tenPercent(tenPercent)
				.twentyPercent(twentyPercent)
				.build());
		
		List<CouponEntity> couponList = couponRepository.findAll();
		
		CouponEntity coupon = couponList.get(0);
//		assertThat(coupon.getUser());
//		assertThat(coupon.getFivePercent());
		System.out.println("Hi " + coupon.getUser());
//		assertThat(coupon.getTenPercent());
//		assertThat(coupon.getTwentyPercent());
	}
}
