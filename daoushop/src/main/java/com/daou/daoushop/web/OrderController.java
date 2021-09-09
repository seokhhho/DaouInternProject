package com.daou.daoushop.web;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daou.daoushop.service.OrderService;
import com.daou.daoushop.web.dto.OrderBalanceResponseDto;
import com.daou.daoushop.web.dto.OrderRequestDto;
import com.daou.daoushop.web.dto.OrderResponseDto;
import com.daou.daoushop.web.dto.PayRequestDto;
import com.daou.daoushop.web.dto.PaymentResponseDto;
import com.daou.daoushop.web.dto.RefundResponseDto;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:8080")
@RequiredArgsConstructor
@RestController
@RequestMapping("/order")
public class OrderController {

	private final OrderService orderService;
	
	@PutMapping(value = "/autoPayInfo")
	public OrderResponseDto findAutoPayInfo(@RequestBody OrderRequestDto requestDto) {
		return orderService.findAutoPayInfo(requestDto);
	}
	
	@PutMapping(value = "/autoPay")
	public OrderBalanceResponseDto autoPay(@RequestBody PayRequestDto requestDto) {
		return orderService.autoPay(requestDto);
	}
	
	@GetMapping(value = "list/{userNumber}")
	public List<PaymentResponseDto> payList(@PathVariable Integer userNumber) {
		return orderService.payList(userNumber);
	}
	
	@GetMapping(value = "/refund/{paymentId}")
	public RefundResponseDto refund(@PathVariable Integer paymentId) {
		return orderService.refund(paymentId);
	}
}
