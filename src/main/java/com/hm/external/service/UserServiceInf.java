package com.hm.external.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import com.hm.external.model.User;

@Service
public interface UserServiceInf {
	
	public User getUser();
	
	@Retryable(value = {HttpServerErrorException.class,EntityNotFoundException.class},maxAttempts = 3,backoff = @Backoff(delay = 100))
	public User addUser(Integer userId) throws Exception;
	
	@Recover
	public User addUserFallBack(RuntimeException e);
}
