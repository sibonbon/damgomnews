package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.ProductVO;
import com.example.demo.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;
	
	public  List<ProductVO>findAll() {
		return productRepository.findAll();
	}

	public ProductVO save(ProductVO product) {
		return productRepository.save(product);
	}

	public void deleteById(Long idx) {
		productRepository.deleteById(idx);
	}

	public ProductVO findById(Long idx) {
		try {
			Optional<ProductVO> vo = productRepository.findById(idx);
			return vo.get();
		}catch(Exception e) {
			return null;
		}
	}

}
