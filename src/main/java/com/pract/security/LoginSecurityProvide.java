package com.pract.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.pract.model.Login;
import com.pract.model.Roles;
import com.pract.repository.LoginRepository;
@Component
public class LoginSecurityProvide implements AuthenticationProvider{

	@Autowired
	private LoginRepository loginRepo;
	@Autowired 
	private PasswordEncoder pencd;
	

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String email=authentication.getName();
		String password=authentication.getCredentials().toString();
		System.out.println("email in secuirty"+email);
		System.out.println("password in secuirty"+password);
		Login lop=loginRepo.getByEmail(email);
		
		if(lop!=null && (pencd.matches(password, lop.getPassword()))) {
			System.out.println("coming inside this shit");
			return new UsernamePasswordAuthenticationToken(lop.getEmail(),null,getGrantedAuthorities(lop.getRoles()));
		}
		System.out.println("coming outside");
		return null;
		
		
	}

	private List<GrantedAuthority>getGrantedAuthorities(Roles roles) {
		List<GrantedAuthority>getHranted=new ArrayList<>();
		if(roles!=null) {
			getHranted.add(new SimpleGrantedAuthority("ROLE_"+roles.getRoleName()));
		}
		return getHranted;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
