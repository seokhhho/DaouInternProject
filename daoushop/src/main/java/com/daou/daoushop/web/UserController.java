package com.daou.daoushop.web;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daou.daoushop.service.UserService;
import com.daou.daoushop.web.dto.BalanceResponseDto;
import com.daou.daoushop.web.dto.UserRequestDto;
import com.daou.daoushop.web.dto.UserResponseDto;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:8080")
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

	private final UserService userService;
	
	@PostMapping(value= "/join")
	public Integer join(@RequestBody UserRequestDto requestDto) {
		return userService.createUserWithMoneyInfo(requestDto);
	}
	
	@GetMapping(value = "/list")
	public List<UserResponseDto> findAll(){
		return userService.readUserList();
	}
	
	@GetMapping(value = "/balance/{userNumber}")
	public BalanceResponseDto findBalance(@PathVariable Integer userNumber) {
		return userService.findBalance(userNumber);
	}
	
	
}
