package com.pkharat.springboot.service;


import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.pkharat.springboot.model.User;
import com.pkharat.springboot.repository.UserRepository;



@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public void saveUser(User user) {
		String test = user.getPassword();
		user.setPassword(passwordEncoder.encode(test));
		this.userRepository.save(user);
		
	}

	@Override
	public User getUserById(long id) {
		Optional<User> optional = userRepository.findById(id);
		User user = null;
		if(optional.isPresent()) {
			user = optional.get();
		} else {
			throw new RuntimeException("user not found for id:"+id);
		}
		return user;
			
	}

	@Override
	public void deleteUserById(long id) {
		this.userRepository.deleteById(id);		
	}

	@Override
	public Page<User> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo -1,pageSize);
		return this.userRepository.findAll(pageable);

	}
	
	

}
