//
//package com.hm.external.service;
//
//import java.time.Duration;
//
//import org.springframework.stereotype.Component;
//import org.springframework.web.client.HttpClientErrorException;
//import org.springframework.web.client.HttpServerErrorException.InternalServerError;
//
//import io.github.resilience4j.retry.Retry;
//import io.github.resilience4j.retry.RetryConfig;
//import io.github.resilience4j.retry.RetryRegistry;
//
//@Component
//public class ResilienceConfig {
//
////	@Autowired
////	UserServiceImp userServiceImp = new UserServiceImp();
////	RetryConfig config = RetryConfig.custom().maxAttempts(2).build();
////	RetryRegistry registry = RetryRegistry.of(config);
////	Retry retry = registry.retry("userRetry");
////	private Integer userId;
////
////	Function<Integer, Object> a=Retry.decorateFunction(retry,(Integer uerId)->{try{userServiceImp.getUserById(userId);}catch(
////	Exception e)
////	{ //
////	  TODO Auto-generated catch block e.printStackTrace(); }return null;
////	});
////
////	public Supplier<User> getSupplierUser() {
////
////		Supplier<User> supplier = () -> {
////			try {
////				return userServiceImp.getUserById(userId);
////			} catch (Exception e) {
////				e.printStackTrace();
////			}
////		};
////		CheckedFunction0<User> retryingUser = Retry.decorateCheckedSupplier(retry, null);
////	}
//
//	public Retry getRetry() {
//		RetryConfig config = RetryConfig.custom().maxAttempts(2).waitDuration(Duration.ofMillis(1000))
//				.retryOnException(e -> e instanceof InternalServerError)
//				.retryExceptions(InternalServerError.class, HttpClientErrorException.class, ArithmeticException.class)
//				.build();
//
//		RetryRegistry registry = RetryRegistry.of(config);
//		return registry.retry("retryUser", config);
//
//	}
//
//}
