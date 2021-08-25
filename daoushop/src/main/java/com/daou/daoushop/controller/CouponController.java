package com.daou.daoushop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.daou.daoushop.domain.coupon.CouponEntity;
import com.daou.daoushop.domain.coupon.CouponRepository;

@RestController
@RequestMapping("/coupon")
public class CouponController {
	
	@Autowired
	CouponRepository couponRepository;
	
//	@GetMapping(value = "/read")
//	public ResponseEntity read(@RequestParam String user) {
//		ResponseEntity entity = null;
//		CouponEntity coupon = new CouponEntity("SD", 0, 0, 0);
//		coupon.get
//	}
	
//	@PostMapping(value = "/create")
//	public ResponseEntity<CouponEntity> create(@RequestBody CouponEntity coupon){
//		
//		ResponseEntity<CouponEntity> entity = null;
//		
//		try {
//			
//		}
//	}
}
