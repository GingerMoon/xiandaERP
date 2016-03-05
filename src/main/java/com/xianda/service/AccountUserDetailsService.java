package com.xianda.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.xianda.domain.Account;
import com.xianda.domain.AccountUserDetails;
import com.xianda.domain.Role;
import com.xianda.domain.Permission;

@Service
public class AccountUserDetailsService implements UserDetailsService {

	@Autowired
	private AccountService accountService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if (username.isEmpty()) {
			throw new UsernameNotFoundException("Username was empty");
		}

		Account account = accountService.getAccount(username);

		if (account == null) {
			throw new UsernameNotFoundException("Username not found");
		}

		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		if (!account.getRoles().isEmpty()) {
			for (Role role : account.getRoles()) {
				grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole()));
				for (Permission permission : role.getPermissions()) {
					grantedAuthorities.add(new SimpleGrantedAuthority(permission.getPermission()));
				}
			}
		}

		return new AccountUserDetails(accountService.getAccount(username), grantedAuthorities);
	}
}
