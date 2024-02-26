package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.service.UserService;

import model.User;


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
	
	@GetMapping("/admin")
    public String admin(Model model) {
        List<User> users = us.getAllUsers();
        model.addAttribute("users", users);
        return "pages/adminPage"; 
    }
	
	@PostMapping("/removeUser")
    public String removeUser(@RequestParam("userId") int userId, Model m) {
        boolean s = us.deleteUser(userId);
        if(s == true) {
			m.addAttribute("deleteUspeh", "success");
		}
		else {
			m.addAttribute("deleteUspeh", "error");
		}
        List<User> users = us.getAllUsers();
        m.addAttribute("users", users);
        return "pages/adminPage"; 
    }
		
}
