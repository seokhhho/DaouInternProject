package com.daou.daoushop.domain.coupon;

import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import com.daou.daoushop.domain.point.PointRepository;
import com.daou.daoushop.domain.product.ProductRepository;
import com.daou.daoushop.domain.user.UserEntity;
import com.daou.daoushop.domain.user.UserRepository;
import com.daou.daoushop.domain.userMoney.UserMoneyEntity;

import lombok.RequiredArgsConstructor;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserRepositoryTest {

	@Autowired
	UserRepository userRepository;
	
	@AfterEach
	public void cleanup() {
	}
	
	@Transactional
	@Test
	@DisplayName("유저 정보 불러오기 테스트")
	public void findAllUserInfo() {
		UserEntity user = userRepository.findById(1)
		.orElseThrow(() -> new IllegalArgumentException( "해당 유저가 존재 하지 않습니다."));
		Set<CouponEntity> coupons = user.getCoupons();
		for(CouponEntity c : coupons) {
			System.out.println("쿠폰 : " + c.getCouponId());
		}
		UserMoneyEntity userMoney = user.getUserMoney();
		System.out.println("유저돈 : " + userMoney.getUserMoneyId());
	}
}
