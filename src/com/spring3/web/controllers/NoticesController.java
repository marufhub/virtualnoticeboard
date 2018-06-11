package com.spring3.web.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring3.web.dao.FormValidationGroup;
import com.spring3.web.dao.Notice;
import com.spring3.web.service.NoticeService;

@Controller
public class NoticesController {

	private NoticeService noticeService;

	@Autowired
	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}

	@RequestMapping("/test")
	public String showNotice(Model model, @RequestParam("id") String id) {
		System.out.println("Id is:" + id);

		return "home";
	}

	
	
	@RequestMapping("/notice")
	public String showNotice(HttpSession session) {

		//noticeService.thorwTestException();
		
		List<Notice> notices = noticeService.getCurrent();

		session.setAttribute("notices", notices);
		return "notice";
	}

	@RequestMapping("/createnotice")
	public String createNotice(Model model, Principal principal) {

		Notice notice = null;
		if(principal != null){
			String username = principal.getName();
			
			notice = noticeService.getNotice(username);
		}
		if(notice == null){
			notice = new Notice();
		}
		model.addAttribute(notice);
		return "createnotice";
	}

	@RequestMapping(value = "/docreate", method = RequestMethod.POST)
	public String doCreate(Model model, @Validated(value= FormValidationGroup.class) Notice notice,
			BindingResult result, Principal principal, @RequestParam(value = "delete", required = false) String delete ) {

		if (result.hasErrors()) {
			
			return "createnotice";
		}
		
		if(delete == null){
			String username = principal.getName();
			notice.getUser().setUsername(username);
			noticeService.saveOrUpdate(notice);
			return "noticecreated";
			
		}else{
			noticeService.delete(notice.getId());
			return "noticedeleted";
		}
		
		
		
		
	}

}
