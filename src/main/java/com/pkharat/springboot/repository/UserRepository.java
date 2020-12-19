package com.pkharat.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pkharat.springboot.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	//@Query("select u from users u where u.email=?1")
	//User FindByEmail(String email);

}
