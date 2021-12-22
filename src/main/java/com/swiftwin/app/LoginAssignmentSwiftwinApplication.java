package com.swiftwin.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.swiftwin.app.entities.Authority;
import com.swiftwin.app.entities.User;
import com.swiftwin.app.repository.UserDetailsRepository;

@SpringBootApplication
public class LoginAssignmentSwiftwinApplication {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserDetailsRepository userDetailsRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(LoginAssignmentSwiftwinApplication.class, args);
	}

	@PostConstruct
	protected void init() {
		
		List<Authority> authorityList=new ArrayList<>();
		
		authorityList.add(createAuthority("USER","User role"));
		// orr authorityList.add(createAuthority("ADMIN","Admin role"));
		
		User user=new User();
		
		user.setUserName("ajikya2828");
		user.setFirstName("Ajinkya");
		user.setLastName("M");
		
		user.setPassword(passwordEncoder.encode("ajinkya@123"));
		user.setEnabled(true);
		user.setAuthorities(authorityList);  // automatically insert in the authority tble
		
		userDetailsRepository.save(user);
		
		
	}
	
	private Authority createAuthority(String roleCode,String roleDescription) {
		Authority authority=new Authority();
		authority.setRoleCode(roleCode);
		authority.setRoleDescription(roleDescription);
		return authority;
	}
	
}
