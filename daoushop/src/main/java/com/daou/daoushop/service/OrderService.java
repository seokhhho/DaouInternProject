package com.daou.daoushop.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daou.daoushop.domain.coupon.CouponEntity;
import com.daou.daoushop.domain.coupon.CouponRepository;
import com.daou.daoushop.domain.payedProduct.PayedProductEntity;
import com.daou.daoushop.domain.payedProduct.PayedProductRepository;
import com.daou.daoushop.domain.payment.PaymentEntity;
import com.daou.daoushop.domain.payment.PaymentRepository;
import com.daou.daoushop.domain.point.PointEntity;
import com.daou.daoushop.domain.point.PointRepository;
import com.daou.daoushop.domain.product.ProductEntity;
import com.daou.daoushop.domain.product.ProductRepository;
import com.daou.daoushop.domain.usedPoint.UsedPointEntity;
import com.daou.daoushop.domain.usedPoint.UsedPointRepository;
import com.daou.daoushop.domain.user.UserEntity;
import com.daou.daoushop.domain.user.UserRepository;
import com.daou.daoushop.domain.userMoney.UserMoneyEntity;
import com.daou.daoushop.domain.userMoney.UserMoneyRepository;
import com.daou.daoushop.web.AutoPayInfo;
import com.daou.daoushop.web.dto.RefundResponseDto;
import com.daou.daoushop.web.dto.CouponDto;
import com.daou.daoushop.web.dto.OrderBalanceResponseDto;
import com.daou.daoushop.web.dto.OrderRequestDto;
import com.daou.daoushop.web.dto.OrderResponseDto;
import com.daou.daoushop.web.dto.PayRequestDto;
import com.daou.daoushop.web.dto.PayedProductDto;
import com.daou.daoushop.web.dto.PaymentResponseDto;
import com.daou.daoushop.web.dto.PointDto;
import com.daou.daoushop.web.dto.ProductAmountDto;
import com.daou.daoushop.web.dto.UsedPointDto;
import com.daou.daoushop.web.dto.UsingPointDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OrderService {

	private final ProductRepository productRepository;
	private final UserRepository userRepository;
	private final PointRepository pointRepository;
	private final CouponRepository couponRepository;
	private final UserMoneyRepository userMoneyRepository;
	private final PaymentRepository paymentRepository;
	private final PayedProductRepository payedProductRepository;
	private final UsedPointRepository usedPointRepository;
	
	
	/*자동 결제 시 결제 정보 가져오기*/
	@Transactional
	public OrderResponseDto findAutoPayInfo(OrderRequestDto requestDto) {
		
		List<ProductAmountDto> buyedProducts = requestDto.getProducts();
		
		/*반환해 줄 객체 관련 데이터*/
		int totalPrice = 0;
		int discountedPrice = 10;
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
		
		Calendar calendar = Calendar.getInstance();
		Date validDay = calendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String now = sdf.format(validDay);
		
		for(PointEntity p : points) {
			if(p.getPointMoney() > 0 && p.getValid().compareTo(now) > 0) {
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
		
		if(usingCoupon == null) {
			return OrderResponseDto.builder()
				.totalPrice(totalPrice)
				.discountedPrice(discountedPrice)
				.usingPoints(usingPoints)
				.usingFund(usingFund)
				.pgPayMoney(pgPayMoney)
				.build();
		}
		else {
			return OrderResponseDto.builder()
					.totalPrice(totalPrice)
					.discountedPrice(discountedPrice)
					.couponId(usingCoupon.getCouponId())
					.couponName(usingCoupon.getCouponName())
					.discountRate(usingCoupon.getDiscountRate().getRate())
					.usingPoints(usingPoints)
					.usingFund(usingFund)
					.pgPayMoney(pgPayMoney)
					.build();
		}
	}

	@Transactional
	public OrderBalanceResponseDto autoPay(PayRequestDto requestDto) {
		
		UserEntity user = userRepository.findById(requestDto.getUserNumber())
				.orElseThrow(() -> new IllegalArgumentException( "해당 유저가 존재 하지 않습니다."));
		
		CouponEntity coupon = null;
		if(requestDto.getCouponId() != 0) {
			coupon = couponRepository.findById(requestDto.getCouponId())
					.orElseThrow(() -> new IllegalArgumentException( "해당 쿠폰이 존재 하지 않습니다."));
			coupon.usingCoupon();
			couponRepository.save(coupon);
		}

		
		for(UsingPointDto p : requestDto.getUsingPoints()) {
			PointEntity point = pointRepository.findById(p.getPointId())
					.orElseThrow(() -> new IllegalArgumentException( "해당 포인트가 존재 하지 않습니다."));
			point.deduct(p.getUsingMoney());
			pointRepository.save(point);
		}
		
		UserMoneyEntity userMoney = user.getUserMoney();
		userMoney.deduct(requestDto.getUsingFund());
		userMoneyRepository.save(userMoney);
		PaymentEntity payment;
		if(coupon == null) {
			payment =PaymentEntity.builder()
					.user(user)
					.usedFund(requestDto.getUsingFund())
					.usedMoney(requestDto.getPgPayMoney())
					.build();
		}else {
			payment =PaymentEntity.builder()
					.user(user)
					.coupon(coupon)
					.usedFund(requestDto.getUsingFund())
					.usedMoney(requestDto.getPgPayMoney())
					.build();
		}
		
		paymentRepository.save(payment);
		
		for(ProductAmountDto buyedProductInfo :requestDto.getProducts()) {
			 ProductEntity buyedProduct =  productRepository.findById(buyedProductInfo.getProductId())
						.orElseThrow(() -> new IllegalArgumentException( "해당 상품이 존재 하지 않습니다."));
			 buyedProduct.buyProduct(buyedProductInfo.getAmount());
			 
			 payedProductRepository.save(PayedProductEntity.builder()
					 .payment(payment)
					 .product(buyedProduct)
					 .amount(buyedProductInfo.getAmount())
					 .build());
		}
		
		for(UsingPointDto p : requestDto.getUsingPoints()) {
			PointEntity point = pointRepository.findById(p.getPointId())
					.orElseThrow(() -> new IllegalArgumentException( "해당 포인트가 존재 하지 않습니다."));
			usedPointRepository.save(UsedPointEntity.builder()
					.payment(payment)
					.point(point)
					.usedMoney(p.getUsingMoney())
					.build());
		}
		
		
		
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
		
		Calendar calendar = Calendar.getInstance();
		Date validDay = calendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String valid = sdf.format(validDay);
		
		List<PointDto> pointsDto = new ArrayList<PointDto>();
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
		
		return OrderBalanceResponseDto.builder()
				.coupons(couponsDto)
				.points(pointsDto)
				.fund(fund)
				.build();
	}

	@Transactional
	public List<PaymentResponseDto> payList(Integer userNumber) {
		
		UserEntity user = userRepository.findById(userNumber)
				.orElseThrow(() -> new IllegalArgumentException( "해당 유저가 존재 하지 않습니다."));
		
		Set<PaymentEntity> payments = user.getPayments();
		List<PaymentResponseDto> paymentsDto = new ArrayList<PaymentResponseDto>();
		
		for(PaymentEntity p : payments) {
			
			CouponEntity coupon = p.getCoupon();
			Set<UsedPointEntity> usedPointsSet = p.getUsedPoints();
			Set<PayedProductEntity> productsSet = p.getPayedProducts();
			List<UsedPointDto> usedPoints = new ArrayList<UsedPointDto>();
			
			for(UsedPointEntity usedP : usedPointsSet) {
				usedPoints.add(UsedPointDto.builder()
						.usedPointId(usedP.getUsedPointId())
						.pointId(usedP.getPoint().getPointId())
						.usedMoney(usedP.getUsedMoney())
						.pointName(usedP.getPoint().getPointName())
						.valid(usedP.getPoint().getValid())
						.build());
			}
			
			List<PayedProductDto> payedProducts = new ArrayList<PayedProductDto>();
			for(PayedProductEntity payedP : productsSet) {
				payedProducts.add(PayedProductDto.builder()
						.payedProductId(payedP.getPayedProductId())
						.productId(payedP.getProduct().getProductId())
						.productName(payedP.getProduct().getProductName())
						.amount(payedP.getAmount())
						.build());
			}
			
			if(coupon == null) {
				paymentsDto.add(PaymentResponseDto.builder()
						.paymentId(p.getPaymentId())
						.usedFund(p.getUsedFund())
						.usedMoney(p.getUsedMoney())
						.usedPoints(usedPoints)
						.payedProducts(payedProducts)
						.build());
			}else {
				paymentsDto.add(PaymentResponseDto.builder()
						.paymentId(p.getPaymentId())
						.couponId(coupon.getCouponId())
						.couponName(coupon.getCouponName())
						.usedFund(p.getUsedFund())
						.usedMoney(p.getUsedMoney())
						.usedPoints(usedPoints)
						.payedProducts(payedProducts)
						.build());
			}
			
		}
		return paymentsDto;
	}

	@Transactional
	public RefundResponseDto refund(Integer paymentId) {
		
		PaymentEntity payment = paymentRepository.getById(paymentId);
		
		UserEntity user = userRepository.findById(payment.getUser().getUserNumber())
				.orElseThrow(() -> new IllegalArgumentException( "해당 유저가 존재 하지 않습니다."));
		
		
		UserMoneyEntity userMoney = user.getUserMoney();
		userMoney.deposit(payment.getUsedFund());
		userMoneyRepository.save(userMoney);
		
		Calendar calendar = Calendar.getInstance();
		Date validDay = calendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String now = sdf.format(validDay);
		
		for(UsedPointEntity usedP : payment.getUsedPoints()) {
			if(usedP.getPoint().getValid().compareTo(now) > 0) {
				PointEntity point = usedP.getPoint();
				point.deposit(usedP.getUsedMoney());
				pointRepository.save(point);
				usedPointRepository.delete(usedP);
			}
		}
		
		CouponEntity coupon = null;
		if(payment.getCoupon() != null) {
			coupon = payment.getCoupon();
			coupon.giveBackCoupon();
			couponRepository.save(coupon);
		}
		
		for(PayedProductEntity payedP :payment.getPayedProducts()) {
			 ProductEntity product =  payedP.getProduct();
			 product.giveBackProduct(payedP.getAmount());
			 productRepository.save(product);
			 payedProductRepository.delete(payedP);
		}
		
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
		for(PointEntity p : points) {
			if(p.getPointMoney() > 0 && p.getValid().compareTo(now) > 0) {
			pointsDto.add(PointDto.builder()
					.pointId(p.getPointId())
					.valid(p.getValid())
					.pointMoney(p.getPointMoney())
					.pointName(p.getPointName())
					.build());
			}
		}
		
		int fund = user.getUserMoney().getFund();
		int giveBackMoney = payment.getUsedMoney();
		
		paymentRepository.delete(payment);
		return RefundResponseDto.builder()
				.coupons(couponsDto)
				.points(pointsDto)
				.fund(fund)
				.giveBackMoney(giveBackMoney)
				.build();
	}

}
