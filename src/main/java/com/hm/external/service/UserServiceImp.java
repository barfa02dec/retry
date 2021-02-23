package com.hm.external.service;

import java.net.URI;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.hm.external.model.User;

@Service
public class UserServiceImp implements UserServiceInf{
	private static int counter = 1;
	private static int count = 0;
	
//	@Autowired
//	UserRepository userRepository;
//	@Autowired
//	ResilienceConfig resilienceConfig;
//	
//	@Override
//	@Retry(name = "retryUser",fallbackMethod = "test")
//	//@Retryable(value = {InternalServerError.class,ArithmeticException.class,HttpClientErrorException.class},maxAttempts = 3 )
//	public User getUserById(int userId) {
//		RestTemplate restTemplate = new RestTemplate();
//		ResponseEntity<User> user = restTemplate.exchange(new RequestEntity<>(userId,HttpMethod.GET,URI.create("http://localhost:8081/user/"+userId)),User.class);	
////		throw new ArithmeticException("");
//		return user.getBody();
//	}
//	@Override
//	public User addUser(Integer userId) throws Exception {
////		TestFallback fallback = new TestFallback();
////		return userRepository.save(fallback.getUserById(userId));
////		throw new ArithmeticException("");
//		return userRepository.save(getUserById(userId));
//	}
//		
//	public User test(int userId,HttpClientErrorException t) throws Exception{
//		System.out.println(1);
//		return null;
//	}
//	
//	@Recover
//	public User recover(ArithmeticException t){
//	    System.out.println("TEst");
//		return new User();
//	}

	@Override
	public User getUser() {
		System.out.println("Retry : "+ counter++);
		System.out.println("count : "+ count++);
		if(count == 1)
			throw new HttpServerErrorException(HttpStatus.TOO_MANY_REQUESTS);
		if(count == 2)
			throw new EntityNotFoundException("Not Found");
		return new User("User", "Password", "email", "contact", "address", "role");
		
	}

	@Override
	public User addUser(Integer userId) throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<User> user = restTemplate.exchange(new RequestEntity<>(userId,HttpMethod.GET,URI.create("http://localhost:8090/user/")),User.class);		
		return user.getBody();
	}

	@Override
	public User addUserFallBack(RuntimeException e) {
		System.out.println("Max attempt faild. Fall back method called");
		throw new HttpServerErrorException(HttpStatus.TOO_MANY_REQUESTS);
	}
	
}
