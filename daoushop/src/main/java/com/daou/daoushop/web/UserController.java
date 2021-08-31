package com.daou.daoushop.web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daou.daoushop.service.UserService;
import com.daou.daoushop.web.dto.UserRequestDto;
import com.daou.daoushop.web.dto.UserResponseDto;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

	private final UserService userService;
	
	@PostMapping(value= "/join")
	public Integer join(@RequestBody UserRequestDto requestDto) {
		System.out.println("하이하이" + requestDto.getId() +"ㅋㅋ" + requestDto.getName());
		return userService.save(requestDto);
	}
	
	@GetMapping(value = "/list")
	public List<UserResponseDto> findAll(){
		return userService.findAll();
	}
	
	
}
