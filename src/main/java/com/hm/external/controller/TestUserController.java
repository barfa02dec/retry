package com.hm.external.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hm.external.model.User;
import com.hm.external.service.UserServiceInf;
@RestController
public class TestUserController {

	@Autowired
	UserServiceInf userService;

	@PostMapping("/user")
	public @ResponseBody User addUser(@RequestParam Integer userId) throws Exception {
		return userService.addUser(userId);	
	}
	
}
