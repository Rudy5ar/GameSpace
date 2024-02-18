package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.example.demo.repository.UserRepository;

import model.Role;
import model.User;

@Service
public class UserService {

	@Autowired
	UserRepository ur;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public void addUser(String email, String username, String password, Model m) {
		User u = new User();
		if(email.isEmpty() || username.isEmpty() || password.isEmpty()) {
			m.addAttribute("registerUspeh", "error");
			return;
		}
		else {
			u.setUsername(username);
			u.setEmail(email);
	     	u.setPassword(passwordEncoder.encode(password));
	     	u.setRole(new Role("user", 1));
			User added = ur.save(u);
			
			if(added != null) {
				m.addAttribute("registerUspeh", "success");
			}
			else {
				m.addAttribute("registerUspeh", "error");
			}
			return;
		}
	}
	
	public User getUserByName(String username) {
		User user = ur.findByUsername(username);
		return user;
	}
	
	
}
