package com.pkharat.springboot.service;

import java.util.List;

import com.pkharat.springboot.model.User;

public interface UserService {
	
	List<User> getAllUsers(); //list all users
	void saveUser(User user); //save user
	User getUserById(long id);
	void deleteUserById(long id);

}
