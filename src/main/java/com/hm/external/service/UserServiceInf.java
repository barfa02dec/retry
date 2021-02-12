package com.hm.external.service;

import com.hm.external.model.User;

public interface UserServiceInf {
	
	public User getUserById(int userId);
	public User addUser(Integer userId) throws Exception;
	
}
