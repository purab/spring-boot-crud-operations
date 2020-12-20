package com.pkharat.springboot.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.pkharat.springboot.model.User;

public interface UserService {
	
	List<User> getAllUsers(); //list all users
	void saveUser(User user); //save user
	User getUserById(long id);
	void deleteUserById(long id);
	Page<User> findPaginated(int pageNo,int pageSize,String sortField, String sortDirection);
	List<User> findByemail(String email);

}
