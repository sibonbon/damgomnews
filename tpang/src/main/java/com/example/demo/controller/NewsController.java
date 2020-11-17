package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.MemberVO;
import com.example.demo.entity.NewsArticleVO;
import com.example.demo.entity.NewsReplyVO;
import com.example.demo.service.NewsArticleService;
import com.example.demo.service.NewsReplyService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/news")
public class NewsController {
	@Autowired
	NewsArticleService newsArticleService;
	@Autowired
	NewsReplyService newsReplyService;

	@RequestMapping("")
	public String newsHome(Model model,HttpServletRequest request,
		@RequestParam(value = "cat", required = false) String category,
		@RequestParam(value = "log", required = false) String logMsg){
		HttpSession session = request.getSession();
		session.setAttribute("id", "damgom");
			if(category != null && !category.equals("")) {
				model.addAttribute("category",category);
				model.addAttribute("newsArticleList",newsArticleService.findByCategory(category));
				log.info(newsArticleService.findByCategory(category).toString());
			} else {
				model.addAttribute("category","���� Ȩ");
			
				model.addAttribute("newsArticleList",newsArticleService.findAll());
				log.info(newsArticleService.findAll().toString());
				
			}

			return "news/index2";
		
	}
	@GetMapping("add")
	public String addnews(Model model) {
		model.addAttribute("newsArticle", NewsArticleVO.builder().build());
		return "news/add";
	}
	@PostMapping("add")
	String addNewsPage(Model model, @ModelAttribute NewsArticleVO newsArticle) {
		log.info(newsArticle.toString());
        NewsArticleVO oldNews = newsArticleService.findById(newsArticle.getId());
        if(oldNews != null) {
           newsArticle.setReplies(oldNews.getReplies());
        }
        newsArticleService.save(newsArticle);
		return "redirect:/news";
	}
	@RequestMapping("modify")
		public String modifynews(Model model, @RequestParam(value="idx")Long idx) {
		log.info("����Ŭ��"+idx.toString());
		NewsArticleVO newsArticle = newsArticleService.findById(idx);
		model.addAttribute("newsArticle", newsArticle);
		return "news/add";
	}
	@RequestMapping(path="delete", method=RequestMethod.GET)
	public String deletenews(Model model, @RequestParam(value="idx")Long idx) {
		log.info("����Ŭ��"+idx.toString());
		newsArticleService.deleteById(idx);
		return "redirect:/news";
	}
	@RequestMapping("detail2")
	public String newsDetail(Model model, @RequestParam("idx") Long idx) {
		
		if(idx != null) {
			log.info(idx.toString());
			model.addAttribute("msgName", idx);
			NewsArticleVO newsArticle = newsArticleService.findById(idx);
			if(newsArticle != null) {
				log.info(newsArticle.toString());
				model.addAttribute("newsArticle", newsArticle);
			}
			NewsReplyVO newsReply = NewsReplyVO.builder().build();
			model.addAttribute("newsReply",newsReply);
			}
		return "news/detail";
	}
	@PostMapping("reply/add")
	String addReply(Model model,Long newsArticleId, @ModelAttribute NewsReplyVO newsReply) {
		log.info(newsArticleId + ":" + newsReply.toString());
		
		NewsArticleVO newsArticle = newsArticleService.findById(newsArticleId);
		newsArticle.getReplies().add(newsReply);
		
		newsArticleService.save(newsArticle);
		return "redirect:/news/detail2?idx="+newsArticleId;
	}

	@RequestMapping(path="reply/delete", method=RequestMethod.GET)
	public String deletereply(Model model,Long newsArticleId, @RequestParam(value="idx")Long idx) {
		log.info("����Ŭ��"+idx.toString());
		log.info(newsArticleId + ":" );
		newsReplyService.deleteById(idx);
		return "redirect:/news/detail2?idx="+newsArticleId;
	}
	
	@GetMapping("reply/modify")
	public String modifyreply(Model model,Long newsArticleId, @RequestParam(value="idx")Long idx) {
	log.info("����Ŭ��"+idx.toString());
	NewsReplyVO newsReply = newsReplyService.findById(idx);
	model.addAttribute("newsReply", newsReply);
	model.addAttribute("newsArticleId", newsArticleId);

	return "/news/modify";
	}
	
	@PostMapping("reply/save")
	String saveReply(Model model,Long newsArticleId, @ModelAttribute NewsReplyVO newsReply) {
		log.info(newsArticleId + ":" + newsReply.toString());
		
//		newsReply.getReplies().add(newsReply);
		log.info(newsArticleId + ":" );
		newsReplyService.save(newsReply);
		return "redirect:/news/detail2?idx="+newsArticleId;
	}
}

////ROLE_ADMIN ������ �ִ� ��츸 ǥ�õ�
//<li sec:authorize="hasRole('ROLE_ADMIN')"><a th:href="@{/admin}">������ ������</a></li>
////ROLE_USER ������ �ִ� ��츸 ǥ�õ�
//<li sec:authorize="hasRole('ROLE_USER')"><a th:href="@{/books}">å ���</a></li>
////�α����� ���� ���� ��� ǥ��
//<li sec:authorize="!isAuthenticated()"><a th:href="@{/users}">ȸ������</a></li>
////�α��� �� ����� username�� ���
//<li sec:authorize="isAuthenticated()"><span sec:authentication="principal.username"></span></li>
////����ó�� ����ϰ� ���� ���
//<a th:href="@{'/users/'+ ${#authentication.principal.id}}">����������</a>
