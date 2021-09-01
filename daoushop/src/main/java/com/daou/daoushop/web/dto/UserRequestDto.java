package com.daou.daoushop.web.dto;

import com.daou.daoushop.domain.user.UserEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserRequestDto {

	private String id;
	private String name;
	
	@Builder
	public UserRequestDto(String id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public UserEntity toEntity() {
		return UserEntity.builder()
				.id(id)
				.name(name)
				.build();
	}

}
