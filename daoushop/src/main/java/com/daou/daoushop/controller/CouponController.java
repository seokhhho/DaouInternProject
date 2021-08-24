package com.daou.daoushop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.daou.daoushop.entity.CouponEntity;
import com.daou.daoushop.repository.CouponRepository;

@RestController
@RequestMapping("/coupon")
public class CouponController {
	
	@Autowired
	CouponRepository couponRepository;
	
	@GetMapping(value = "/read")
	public ResponseEntity read(@RequestParam String user) {
		ResponseEntity entity = null;
		CouponEntity coupon = couponRepository.findById(user);
	}
	
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
