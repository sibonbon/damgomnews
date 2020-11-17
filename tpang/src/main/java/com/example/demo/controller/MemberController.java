package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.MemberVO;
import com.example.demo.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MemberController {
	@Autowired
	MemberService memberService; 
	
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	@RequestMapping("/user/login")
	public String loginPage(Model model) {
		model.addAttribute("user", MemberVO.builder());
		return "login";
	}
	@GetMapping("/user/login/result")
	public String loginSuccessPage(Model model) {
	
		model.addAttribute("user", MemberVO.builder());
		return "loginsuccess";
	}
	@GetMapping("/user/logout")
	public String logoutPage(Model model) {
		
		return "logout";
	}
	@GetMapping("/user/logout/result")
	public String logoutSuccessPage(Model model) {
		return "logoutsuccess";
	}
	@GetMapping("/user/denied")
	public String deniedPage(Model model) {
		return "403";
	}
	@GetMapping("user/join")
	public String joinPage(Model model) {
		model.addAttribute("user", MemberVO.builder().build());
		return "join";
	}
	@PostMapping("user/join")
	public String join(MemberVO memberVO) {
		log.info(memberVO.toString());
		memberService.save(memberVO);
		return "joinsuccess";
	}
	@GetMapping("user/user")
	public String userPage() {
		return "user";
	}
	@GetMapping("user/seller")
	public String sellerPage() {
		return "seller";
	}
	@GetMapping("user/admin")
	public String adminPage() {
		return "admin";
	}

}
