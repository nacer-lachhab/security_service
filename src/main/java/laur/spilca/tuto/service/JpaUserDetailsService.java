package laur.spilca.tuto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import laur.spilca.tuto.entities.User;
import laur.spilca.tuto.repositories.UserRepository;
import laur.spilca.tuto.security.model.SecurityUser;

@Service
public class JpaUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		var u = userRepository.findUserByUsername(username);
		User user = u.orElseThrow(() -> new UsernameNotFoundException("userDb not found!!"));
		return new SecurityUser(user);
	}

}
