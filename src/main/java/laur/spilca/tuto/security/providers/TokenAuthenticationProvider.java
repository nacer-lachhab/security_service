package laur.spilca.tuto.security.providers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import laur.spilca.tuto.security.authentications.TokenAuthentication;
import laur.spilca.tuto.security.manager.TokenManager;

@Component
public class TokenAuthenticationProvider implements AuthenticationProvider{
	
	@Autowired
	private TokenManager tokenManager;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// TODO Auto-generated method stub
		String autorizationToken = authentication.getName();
		boolean tokenExists = tokenManager.contains(autorizationToken); 
		if(tokenExists)	
			return new TokenAuthentication(autorizationToken,null,null);
		else 
			throw new BadCredentialsException("authorization not valid!!!!");
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return TokenAuthentication.class.equals(authentication);
	}

}
