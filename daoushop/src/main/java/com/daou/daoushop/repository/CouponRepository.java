package com.daou.daoushop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daou.daoushop.entity.CouponEntity;

public interface CouponRepository extends JpaRepository<CouponEntity, String>{
	

}
