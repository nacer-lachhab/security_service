package laur.spilca.tuto.security.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import laur.spilca.tuto.security.authentications.TokenAuthentication;
import laur.spilca.tuto.security.manager.TokenManager;

@Component
public class TokenAuthenticationFilter extends OncePerRequestFilter{

	/*filter for all endPoints except "/login"*/
	
	@Autowired
	private TokenManager tokenManager;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response,
			FilterChain filterChain)
					throws ServletException, IOException {
		
		// TODO Auto-generated method stub
		var token = request.getHeader("Authorization");
		
		TokenAuthentication tokenAuthentication = new TokenAuthentication(token,null);
		//Authentication
		
		var a = authenticationManager.authenticate(tokenAuthentication);
		//va soit passer ou gennerer une exception
		
		SecurityContextHolder.getContext().setAuthentication(a);
		filterChain.doFilter(request, response);
	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		return request.getServletPath().equals("/login");
	}
}
