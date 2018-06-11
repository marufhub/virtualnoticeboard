package com.spring3.web.controllers;

import java.security.Principal;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring3.web.dao.Notice;
import com.spring3.web.service.NoticeService;

@Controller
public class HomeController {

	private static Logger logger = Logger.getLogger(HomeController.class);
	
	@Autowired
	private NoticeService noticeService;
	
	
	@RequestMapping("/")
	public String showHome(Model model, Principal principal){
		
		logger.info("Showing home...");
		List<Notice> notices = noticeService.getCurrent();

		model.addAttribute("notices", notices);
		
		Boolean hasNotice = false;
		if(principal!=null){
			hasNotice = noticeService.hasNotice(principal.getName());
		}
		
		model.addAttribute("hasNotice", hasNotice);
		return "home";
		
	}
	
	 
}
