package com.pkharat.springboot;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;

import com.pkharat.springboot.model.User;
import com.pkharat.springboot.repository.UserRepository;


@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {

	
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository repo;
	
	@Test
	public void testCreateUser() {
		User user = new User();
		user.setEmail("prabdk@gmail.com");
		user.setPassword(passwordEncoder.encode("purab2020"));
		user.setFirstName("Purab1");
		user.setLastName("Kumar123");		
		
		User savedUser = repo.save(user);
		
		User existUser = entityManager.find(User.class, savedUser.getId());
		
		assertThat(user.getEmail()).isEqualTo(existUser.getEmail());
		
	}
	
	
}
