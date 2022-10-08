package laur.spilca.tuto.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import laur.spilca.tuto.security.filters.TokenAuthenticationFilter;
import laur.spilca.tuto.security.filters.UsernamePasswordAuthFilter;

import laur.spilca.tuto.security.providers.OtpAuthenticationProvider;
import laur.spilca.tuto.security.providers.TokenAuthenticationProvider;
import laur.spilca.tuto.security.providers.UsernamePasswordAuthProvider;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter{
	
	private UsernamePasswordAuthProvider usernamePasswordAuthProvider;
	
	private UsernamePasswordAuthFilter usernamePasswordAuthFilter;
	
	private OtpAuthenticationProvider otpAuthenticationProvider;
	
	private TokenAuthenticationFilter tokenAuthenticationFilter;
	
	private TokenAuthenticationProvider tokenAuthenticationProvider;
	
	
	@Autowired
	public ProjectConfig(@Lazy UsernamePasswordAuthProvider usernamePasswordAuthProvider,
			@Lazy UsernamePasswordAuthFilter usernamePasswordAuthFilter,
			@Lazy OtpAuthenticationProvider otpAuthenticationProvider,
			@Lazy TokenAuthenticationFilter tokenAuthenticationFilter,
			@Lazy TokenAuthenticationProvider tokenAuthenticationProvider) {
		
		this.usernamePasswordAuthProvider = usernamePasswordAuthProvider;
		this.usernamePasswordAuthFilter = usernamePasswordAuthFilter;
		this.otpAuthenticationProvider = otpAuthenticationProvider;
		this.tokenAuthenticationFilter = tokenAuthenticationFilter;
		this.tokenAuthenticationProvider = tokenAuthenticationProvider;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		
		return super.authenticationManagerBean();
		//super refere to WebSecurityConfigurerAdapter
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//on peut configurer le nombre qu on veut.
		auth.authenticationProvider(usernamePasswordAuthProvider)
			.authenticationProvider(otpAuthenticationProvider)
			.authenticationProvider(tokenAuthenticationProvider);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.addFilterAt(usernamePasswordAuthFilter,
				BasicAuthenticationFilter.class)
			.addFilterAfter(tokenAuthenticationFilter,
				BasicAuthenticationFilter.class);
		}

	
}
