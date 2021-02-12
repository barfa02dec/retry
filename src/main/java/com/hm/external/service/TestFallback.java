package com.hm.external.service;

import java.net.URI;

import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.hm.external.model.User;

import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.retry.annotation.Retry;

@Component
public class TestFallback {

	@Retry(name = "retryUser", fallbackMethod = "testfallback")
	//@CircuitBreaker(name = "retryUser",fallbackMethod="testfallback")
	public User getUserById(int userId) throws Exception{
		System.out.println("Get method");
//		CircuitBreakerRegistry circuitBreakerRegistry = CircuitBreakerRegistry.ofDefaults();
//		CircuitBreakerConfig config = CircuitBreakerConfig.custom()
//				  .failureRateThreshold(20)
//				  .ringBufferSizeInClosedState(5)
//				  .build();
//		
//		CircuitBreakerRegistry registry = CircuitBreakerRegistry.of(config);
//		CircuitBreaker circuitBreaker = registry.circuitBreaker("retryUser");
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<User> user = restTemplate.exchange(new RequestEntity<>(userId,HttpMethod.GET,URI.create("http://localhost:8081/user/"+userId)),User.class);	
		return user.getBody();
	}
	
	public User testfallback(int userId,HttpServerErrorException t) throws Exception{
		System.out.println("Fallback method1");
		return new User();
	}
	public User testfallback(int userId,CallNotPermittedException t) throws Exception{
		System.out.println("Fallback method2");
		return new User();
	}
	public User testfallback(int userId,ResourceAccessException t) throws Exception{
		System.out.println("Fallback method3");
		return new User();
	}
}
