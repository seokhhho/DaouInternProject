package com.daou.daoushop.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;
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
import com.daou.daoushop.web.dto.BalanceResponseDto;
import com.daou.daoushop.web.dto.CouponDto;
import com.daou.daoushop.web.dto.PointDto;
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
				.pointName("가입기념 포인트")
				.build());
		
		CouponEntity coupon1 = CouponEntity.builder()   //5,10,20프로  할인쿠폰 저장
				.user(user)
				.discountRate(DiscountRate.FIVE)
				.isUsed(IsUsed.FALSE)
				.couponName("가입기념 5% 할인 쿠폰")
				.build();
		CouponEntity coupon2 = CouponEntity.builder()
				.user(user)
				.discountRate(DiscountRate.TEN)
				.isUsed(IsUsed.FALSE)
				.couponName("가입기념 10% 할인 쿠폰")
				.build();
		CouponEntity coupon3 = CouponEntity.builder()
				.user(user)
				.discountRate(DiscountRate.TWENTY)
				.isUsed(IsUsed.FALSE)
				.couponName("가입기념 20% 할인 쿠폰")
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

	@Transactional(readOnly = true)
	public BalanceResponseDto findBalance(Integer userNumber) {
		
		UserEntity user = userRepository.findById(userNumber)
				.orElseThrow(() -> new IllegalArgumentException( "해당 유저가 존재 하지 않습니다."));
		
		Set<CouponEntity> coupons = user.getCoupons();
		List<CouponDto> couponsDto = new ArrayList<CouponDto>();
		for(CouponEntity c : coupons) {
			if(!c.getIsUsed().isUsed()) {
				couponsDto.add(CouponDto.builder()
						.couponId(c.getCouponId())
						.discountRate(c.getDiscountRate().getRate())
						.couponName(c.getCouponName())
						.minPrice(c.getDiscountRate().getMinPrice())
						.build());
			}
		}
		
		
		Set<PointEntity> pointsSet = user.getPoints();
		List<PointEntity> points = new ArrayList<PointEntity>(pointsSet);
		Collections.sort(points);
		
		List<PointDto> pointsDto = new ArrayList<PointDto>();
		
		Calendar calendar = Calendar.getInstance();
		Date validDay = calendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String valid = sdf.format(validDay);
		
		for(PointEntity p : points) {
			if(p.getPointMoney() > 0 && p.getValid().compareTo(valid) > 0) {
			pointsDto.add(PointDto.builder()
					.pointId(p.getPointId())
					.valid(p.getValid())
					.pointMoney(p.getPointMoney())
					.pointName(p.getPointName())
					.build());
			}
		}
		
		int fund = user.getUserMoney().getFund();
		
		return BalanceResponseDto.builder()
				.coupons(couponsDto)
				.points(pointsDto)
				.fund(fund)
				.build();
	}
}
