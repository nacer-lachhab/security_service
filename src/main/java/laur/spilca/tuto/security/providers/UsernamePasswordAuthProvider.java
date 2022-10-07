package laur.spilca.tuto.security.providers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import laur.spilca.tuto.security.authentications.UsernamePasswordAuthentication;
import laur.spilca.tuto.service.JpaUserDetailsService;



@Component
public class UsernamePasswordAuthProvider implements AuthenticationProvider{
	
	private final JpaUserDetailsService userDetailsService;
	
	private final PasswordEncoder passwordEncoder;

	public UsernamePasswordAuthProvider(JpaUserDetailsService userDetailsService,
			PasswordEncoder passwordEncoder) {
		this.userDetailsService = userDetailsService;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// TODO Auto-generated method stub
		String username = authentication.getName();
		String password =(String) authentication.getCredentials();
		UserDetails user = userDetailsService.loadUserByUsername(username);
		if (passwordEncoder.matches(password,user.getPassword())) {
			return new UsernamePasswordAuthentication(username,password,user.getAuthorities());
		}
		throw new BadCredentialsException("**** loging incorrect....");
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// authentication: represente ici l'objet d authentification.
		return UsernamePasswordAuthentication.class.equals(authentication);
	}

}
