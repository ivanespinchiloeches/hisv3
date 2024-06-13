package com.ivan.thehealthsoftwarecompany.hisv3.service.impl;

import com.ivan.thehealthsoftwarecompany.hisv3.data.entity.User;
import com.ivan.thehealthsoftwarecompany.hisv3.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
	
	@Override
	public UserDetails loadUserByUsername(String username) 
			throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username).orElse(null);
		
		if (user == null) {
			throw new UsernameNotFoundException("Could not find user");
		}
		
		return new MyUserDetails(user);
	}


}
