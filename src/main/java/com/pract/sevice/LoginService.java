package com.pract.sevice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pract.model.Login;
import com.pract.model.Roles;
import com.pract.repository.LoginRepository;
import com.pract.repository.RolesRepository;
@Service
public class LoginService {

	@Autowired
	private LoginRepository loginRepo;
	
	@Autowired
	private RolesRepository rolesRepo;
	
	@Autowired
	private PasswordEncoder encoder;
	
	public boolean isSave(Login login) {
		if(login!=null) {
			Roles oles=rolesRepo.getByRoleName("STUDENT");
			System.out.println("oles"+oles);
			login.setPassword(encoder.encode(login.getPassword()));
			login.setRoles(oles);
			loginRepo.save(login);
			System.out.println("data saved");
			return true;
		}
		return false;
	}
}
