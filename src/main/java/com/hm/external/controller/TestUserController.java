package com.hm.external.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hm.external.model.User;
import com.hm.external.service.UserServiceImp;

@RestController
public class TestUserController {

	@Autowired
	UserServiceImp userService;

	@PostMapping("/user")
	public @ResponseBody User addUser(@RequestParam Integer userId) throws Exception {
		return userService.addUser(userId);	
	}
	@GetMapping("/user")
	public @ResponseBody User getUser() throws Exception {
		return userService.getUser();	
	}
	
}
