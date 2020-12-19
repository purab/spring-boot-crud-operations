package com.pkharat.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.pkharat.springboot.model.User;
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
	
	@GetMapping("showNewUserForm")
	public String showNewUserForm(Model model) {
		//create model attribute to bind form data
		User user = new User();
		model.addAttribute("user",user);
		return "new_user";
	}
	
	@PostMapping("saveUser")
	public String saveUser(@ModelAttribute("user") User user) {
		//save user to database
		userService.saveUser(user);
		return "redirect:/";		
	}
	
	@GetMapping("showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable(value="id") long id,Model model ) {
		
		//get user from service
		User user = userService.getUserById(id);
		//set user as model attribute to pre-populate the form
		model.addAttribute("user", user);
		return "update_user";
		
	}

}
