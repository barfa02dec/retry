package com.hm.external.service;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;
import org.springframework.web.client.RestTemplate;

import com.hm.external.model.User;
import com.hm.external.repository.UserRepository;

import io.github.resilience4j.retry.annotation.Retry;

@Service
public class UserServiceImp implements UserServiceInf{
	@Autowired
	UserRepository userRepository;
	@Autowired
	ResilienceConfig resilienceConfig;
	
	@Override
	@Retry(name = "retryUser",fallbackMethod = "test")
	@Retryable(value = {InternalServerError.class,ArithmeticException.class,HttpClientErrorException.class},maxAttempts = 3 )
	public User getUserById(int userId) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<User> user = restTemplate.exchange(new RequestEntity<>(userId,HttpMethod.GET,URI.create("http://localhost:8081/user/"+userId)),User.class);	
//		throw new ArithmeticException("");
		return user.getBody();
	}
	@Override
	public User addUser(Integer userId) throws Exception {
//		TestFallback fallback = new TestFallback();
//		return userRepository.save(fallback.getUserById(userId));
//		throw new ArithmeticException("");
		return userRepository.save(getUserById(userId));
	}
		
	public User test(int userId,HttpClientErrorException t) throws Exception{
		System.out.println(1);
		return null;
	}
	
	@Recover
	public User recover(ArithmeticException t){
	    System.out.println("TEst");
		return new User();
	}
	
}
