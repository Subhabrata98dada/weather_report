package com.qsp.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.qsp.entity.Users;
import com.qsp.repository.UserDetailsRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class FirstAdminCreatteRunner implements CommandLineRunner {
	
	private final UserDetailsRepository userRepository;
    private final PasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) throws Exception {
		if (userRepository.count() == 0) {
			Users user=new Users();
			user.setUsername("admin");
			user.setPassword(passwordEncoder.encode("admin123"));
			user.setRole("ADMIN");

			userRepository.save(user);
			System.out.println("Default ADMIN user inserted!");
		}

	}

}
