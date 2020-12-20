package com.pkharat.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pkharat.springboot.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	List<User> findByEmailLike(String string);
	
	//Query("SELECT u FROM User u WHERE u.email = ?1")
	//public User findByEmail(String email);

}
