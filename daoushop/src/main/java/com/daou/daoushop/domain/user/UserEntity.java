package com.daou.daoushop.domain.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity(name="user")
public class UserEntity {

	@Id
	@GeneratedValue
	private Integer userNumber;
	private String id;
	private String name;
	
	@Builder
	public UserEntity(Integer userNumber, String id, String name) {
		this.id = id;
		this.name = name;
	}
	
	
	
}
