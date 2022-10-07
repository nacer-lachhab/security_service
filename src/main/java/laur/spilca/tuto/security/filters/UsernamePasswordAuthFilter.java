package laur.spilca.tuto.security.filters;

import java.io.IOException;
import java.util.Random;
import java.util.UUID;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import laur.spilca.tuto.entities.Otp;
import laur.spilca.tuto.repositories.OtpRepository;
import laur.spilca.tuto.security.authentications.OtpAuthentication;
import laur.spilca.tuto.security.authentications.UsernamePasswordAuthentication;

@Component
public class UsernamePasswordAuthFilter extends OncePerRequestFilter{

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private OtpRepository otpRepository;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		var username = request.getHeader("username");
		var password = request.getHeader("password");
		var otp = request.getHeader("otp");

		if (otp==null) {
			//step 1: username & password
			
			//**********************************
			Authentication a = new UsernamePasswordAuthentication(username,password);
			a = authenticationManager.authenticate(a);
			//*******************************************
			
			//we generate otp
			String code = String.valueOf(new Random().nextInt(9999) + 1000);
			//Random().nextInt(9999): nombre entre 0 et 9999
			Otp newOtp = Otp.builder()
							.username(username)
							.otp(code)
							.build();
			otpRepository.save(newOtp);
			//SecurityContextHolder.getContext().setAuthentication(a);
		}else {
			//step 2: username & otp
			
			Authentication a = new OtpAuthentication(username,otp);
			a = authenticationManager.authenticate(a);
			
			////we issue a token
			response.setHeader("Authorization",UUID.randomUUID().toString());
		}
	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		// TODO Auto-generated method stub
		return !request.getServletPath().equals("/login");
	}

}
