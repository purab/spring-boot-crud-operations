package com.pkharat.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.pkharat.springboot.service.UserService;


@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	//Display list of Users - //you can also use @requestmapping instead of getmapping
	@GetMapping("/")
	public String ViewHomePage(Model model) {
		model.addAttribute("listUsers", userService.getAllUsers());
		return "index";
	}

}
