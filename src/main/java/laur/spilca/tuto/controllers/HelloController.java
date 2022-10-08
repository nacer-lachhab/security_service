package laur.spilca.tuto.controllers;

import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import laur.spilca.tuto.security.authentications.TokenAuthentication;

@RestController
public class HelloController {

	@GetMapping("/hello")
	@Async
	public CompletableFuture<String> hello(/*Authentication authentication*/) throws InterruptedException {
		
		TokenAuthentication authentication =
				(TokenAuthentication) SecurityContextHolder
											.getContext()
											.getAuthentication();
		System.out.println(authentication);
		
		return CompletableFuture
				.completedFuture("hi there, your authorization token:\t\n"
				+ authentication.getName());
				//;
	}
}
