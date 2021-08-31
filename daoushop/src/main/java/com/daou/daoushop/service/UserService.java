package com.daou.daoushop.service;

import java.util.List;
import java.util.stream.Collectors;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daou.daoushop.domain.user.UserRepository;
import com.daou.daoushop.web.dto.UserResponseDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

	private final UserRepository userRepository;
	
	@Transactional(readOnly = true)
	public List<UserResponseDto> findAll(){
		return userRepository.findAll().stream()
				.map(UserResponseDto::new)
				.collect(Collectors.toList());
	}
}
