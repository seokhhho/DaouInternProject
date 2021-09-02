package com.daou.daoushop.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daou.daoushop.service.OrderService;
import com.daou.daoushop.web.dto.OrderRequestDto;
import com.daou.daoushop.web.dto.OrderResponseDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/order")
public class OrderController {

	private final OrderService orderService;
	
	@GetMapping(value = "/getAutoPay")
	public OrderResponseDto findAutoPayInfo(@RequestBody OrderRequestDto requestDto) {
		return orderService.findAutoPayInfo(requestDto);
	}
}
