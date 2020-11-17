package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.NewsReplyVO;

public interface NewsReplyRepository extends JpaRepository<NewsReplyVO, Long> {


}
