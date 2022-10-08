package laur.spilca.tuto.security.manager;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

@Component
public class TokenManager {//class to store Token in memory
	
	private Set<String> tokens = new HashSet<>();
	
	public void add(String token) {
		tokens.add(token);
	}
	
	public boolean contains(String token) {
		return tokens.contains(token);
	}
}
