package com.spring3.web.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring3.web.dao.FormValidationGroup;
import com.spring3.web.dao.User;
import com.spring3.web.service.UserService;

@Controller
public class LoginController {

	private UserService userService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping("/login")
	public String showLogin() {
		return "login";
	}

	@RequestMapping("/denied")
	public String showDenied() {
		return "denied";
	}

	@RequestMapping("/admin")
	public String showAdmin(Model model) {

		List<User> users = userService.getAllUsers();

		model.addAttribute("users", users);
		return "admin";
	}

	@RequestMapping("/loggedout")
	public String showLoggedout() {
		return "loggedout";
	}

	@RequestMapping("/newaccount")
	public String showNewAccount(Model model) {
		model.addAttribute("user", new User());
		return "newaccount";
	}

	@RequestMapping(value = "/createaccount", method = RequestMethod.POST)
	public String doCreate(@Validated(FormValidationGroup.class) User user, BindingResult result) {

		if (result.hasErrors()) {

			return "newaccount";
		}
		user.setAuthority("ROLE_USER");
		user.setEnabled(true);

		if (userService.exists(user.getUsername())) {
			result.rejectValue("username", "DuplicateKdy.User.username");
			return "newaccount";
		}

		try {
			userService.create(user);
		} catch (DuplicateKeyException ex) {
			result.rejectValue("username", "DuplicateKdy.User.username");
			return "newaccount";
		}
		return "accountcreated";
	}

}
