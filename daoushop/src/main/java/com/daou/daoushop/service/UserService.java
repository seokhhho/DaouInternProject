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
import com.daou.daoushop.domain.point.PointEntity;
import com.daou.daoushop.domain.point.PointRepository;
import com.daou.daoushop.domain.user.UserRepository;
import com.daou.daoushop.web.dto.UserRequestDto;
import com.daou.daoushop.web.dto.UserResponseDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

	private final UserRepository userRepository;
	private final PointRepository pointRepository;
	private final CouponRepository couponRepository;
	
	@Transactional
	public Integer save(UserRequestDto requestDto) {
		Integer userNumber =  userRepository.save(requestDto.toEntity()).getUserNumber(); // 유저 정보 저장

		Calendar calendar = Calendar.getInstance(); // 유효기간 +1개월로 설정 후 10000 포인트 저장
		calendar.add(Calendar.MONTH, +1);
		Date validDay = calendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String valid = sdf.format(validDay);
		pointRepository.save(PointEntity.builder()
				.userNumber(userNumber)
				.valid(valid)
				.pointMoney(10000)
				.build());
		
		CouponEntity coupon1 = CouponEntity.builder()   //5,10,20프로  할인쿠폰 저장
				.userNumber(userNumber)
				.discountRate(DiscountRate.FIVE)
				.isUsed(0)
				.build();
		CouponEntity coupon2 = CouponEntity.builder()
				.userNumber(userNumber)
				.discountRate(DiscountRate.TEN)
				.isUsed(0)
				.build();
		CouponEntity coupon3 = CouponEntity.builder()
				.userNumber(userNumber)
				.discountRate(DiscountRate.TWENTY)
				.isUsed(0)
				.build();
		List<CouponEntity> coupons = Arrays.asList(coupon1,coupon2,coupon3);
		couponRepository.saveAll(coupons);
		
		return userNumber;
	}
	
	@Transactional(readOnly = true)
	public List<UserResponseDto> findAll(){
		return userRepository.findAll().stream()
				.map(UserResponseDto::new)
				.collect(Collectors.toList());
	}
}
