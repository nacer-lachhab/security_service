package laur.spilca.tuto.security.providers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import laur.spilca.tuto.entities.Otp;
import laur.spilca.tuto.repositories.OtpRepository;
import laur.spilca.tuto.security.authentications.OtpAuthentication;
import laur.spilca.tuto.service.JpaUserDetailsService;

@Component
public class OtpAuthenticationProvider implements AuthenticationProvider{
	
	@Autowired
	private OtpRepository otpRepository;

	@Override
	public Authentication authenticate(Authentication authentication) {
		// TODO Auto-generated method stub
		String username = authentication.getName();
		String otpCredentials =(String) authentication.getCredentials();
		var o = otpRepository.findOtpByUsername(username);
		if(o.isPresent())
		{
			return new OtpAuthentication(username, otpCredentials,List.of(()->"read"));
		}
		else throw new BadCredentialsException("Otp Exception !!!");
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return OtpAuthentication.class.equals(authentication);
	}

}
