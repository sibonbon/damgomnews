package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.MemberRole;
import com.example.demo.entity.MemberVO;
import com.example.demo.repository.MemberRepository;

@Service
public class MemberService implements UserDetailsService {

	@Autowired
	MemberRepository memberRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MemberVO member = memberRepository.findByUserId(username);
		List<MemberRole> roles = member.getRoles();
		List<GrantedAuthority> authorities = new ArrayList<>();
		for(MemberRole role : roles ) {
			authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
		}
		return new User(member.getUserId(), member.getPassword(), authorities);
	}

	public void save(MemberVO memberVO) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		memberVO.setPassword(passwordEncoder.encode(memberVO.getPassword()));
		memberRepository.save(memberVO);
	}

}
