package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.service.UserService;


@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService us;
	
	//Korisceno pri registraciji
	@PostMapping ("/addUser")
	public String addUser(@RequestParam String email, @RequestParam String username, @RequestParam String password, Model m) {	
		us.addUser(email, username, password, m);
		
		return "index";
	}
		
}
