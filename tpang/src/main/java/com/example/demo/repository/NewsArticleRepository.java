package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.NewsArticleVO;

public interface NewsArticleRepository extends JpaRepository<NewsArticleVO, Long> {

	List<NewsArticleVO> findByCategory(String category);





}