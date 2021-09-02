package com.daou.daoushop.service;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daou.daoushop.domain.coupon.CouponEntity;
import com.daou.daoushop.domain.coupon.CouponRepository;
import com.daou.daoushop.domain.coupon.DiscountRate;
import com.daou.daoushop.domain.coupon.IsUsed;
import com.daou.daoushop.domain.point.PointEntity;
import com.daou.daoushop.domain.point.PointRepository;
import com.daou.daoushop.domain.user.UserEntity;
import com.daou.daoushop.domain.user.UserRepository;
import com.daou.daoushop.domain.userMoney.UserMoneyEntity;
import com.daou.daoushop.domain.userMoney.UserMoneyRepository;
import com.daou.daoushop.web.dto.UserRequestDto;
import com.daou.daoushop.web.dto.UserResponseDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

	private final UserRepository userRepository;
	private final PointRepository pointRepository;
	private final CouponRepository couponRepository;
	private final UserMoneyRepository userMoneyRepository;
	
	@Transactional
	public Integer createUserWithMoneyInfo(UserRequestDto requestDto) {
		UserEntity user =  userRepository.save(requestDto.toEntity()); // 유저 정보 저장

		Calendar calendar = Calendar.getInstance(); // 유효기간 현재에서 +1개월로 설정 후 10000 포인트 저장
		calendar.add(Calendar.MONTH, +1);
		Date validDay = calendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String valid = sdf.format(validDay);
		pointRepository.save(PointEntity.builder()
				.user(user)
				.valid(valid)
				.pointMoney(10000)
				.build());
		
		CouponEntity coupon1 = CouponEntity.builder()   //5,10,20프로  할인쿠폰 저장
				.user(user)
				.discountRate(DiscountRate.FIVE)
				.isUsed(IsUsed.FALSE)
				.build();
		CouponEntity coupon2 = CouponEntity.builder()
				.user(user)
				.discountRate(DiscountRate.TEN)
				.isUsed(IsUsed.FALSE)
				.build();
		CouponEntity coupon3 = CouponEntity.builder()
				.user(user)
				.discountRate(DiscountRate.TWENTY)
				.isUsed(IsUsed.FALSE)
				.build();
		List<CouponEntity> coupons = Arrays.asList(coupon1,coupon2,coupon3);
		couponRepository.saveAll(coupons);
		
		userMoneyRepository.save(UserMoneyEntity.builder()// 적립금 0원 초기화
				.user(user)
				.fund(0)
				.build());
		
		return user.getUserNumber();
	}
	
	@Transactional(readOnly = true)
	public List<UserResponseDto> readUserList(){
		return userRepository.findAll().stream()
				.map(UserResponseDto::new)
				.collect(Collectors.toList());
	}
}
