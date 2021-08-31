package com.daou.daoushop.web.dto;

import com.daou.daoushop.domain.user.UserEntity;

import lombok.Getter;

@Getter
public class UserResponseDto {

	private Integer userNumber;
	private String id;
	private String name;
	
	public UserResponseDto(UserEntity entity) {
		this.userNumber = entity.getUserNumber();
		this.id = entity.getId();
		this.name = entity.getName();
	}
	
	
}
