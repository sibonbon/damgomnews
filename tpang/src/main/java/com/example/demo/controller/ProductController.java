package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.NewsArticleVO;
import com.example.demo.entity.NewsReplyVO;
import com.example.demo.entity.ProductVO;
import com.example.demo.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/product")
public class ProductController{
	
	@Autowired
	ProductService productService;

	@RequestMapping("")
	public String productHome(Model model,HttpServletRequest request) {
					model.addAttribute("productList",productService.findAll());
					log.info(productService.findAll().toString());
					
					return "product/main";
	}
	@RequestMapping("detail")
	public String ProductDetail(Model model, @RequestParam("idx") Long idx) {
		
		if(idx != null) {
			log.info(idx.toString());
			model.addAttribute("msgName", idx);
			ProductVO product = productService.findById(idx);
			if(product != null) {
				log.info(product.toString());
				model.addAttribute("product", product);
			}
		}
		return "product/detail";
	}

	@GetMapping("add")
	public String addproduct(Model model) {
		model.addAttribute("product", ProductVO.builder().build());
		return "product/add";
	}
	@PostMapping("add")
	String addproductsuccess(Model model, @ModelAttribute ProductVO product) {
        productService.save(product);
		return "redirect:/product";
	}
	@RequestMapping(path="delete", method=RequestMethod.GET)
	public String deleteproduct(Model model, @RequestParam(value="idx")Long idx) {
		log.info("삭제클릭"+idx.toString());
		productService.deleteById(idx);
		return "redirect:/product";
	}

	
}
