package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.MemberVO;

public interface MemberRepository extends JpaRepository<MemberVO, Long>{
	MemberVO findByUserId(String username);
}
