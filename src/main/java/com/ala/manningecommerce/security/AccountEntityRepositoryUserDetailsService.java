package com.ala.manningecommerce.security;

import java.util.Optional;

import com.ala.manningecommerce.repository.AccountEntityRepository;
import lombok.AllArgsConstructor;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountEntityRepositoryUserDetailsService implements UserDetailsService {

	private final AccountEntityRepository accountEntityRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return
				Optional.ofNullable(accountEntityRepository.findByUsername(username))
						.orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
	}
}
