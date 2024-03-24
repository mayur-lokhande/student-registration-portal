package com.cts.service.Impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.dto.UserDto;
import com.cts.entity.User;
import com.cts.exception.ResourceNotFound;
import com.cts.respository.UserRepository;
import com.cts.service.JWTUserService;

@Service
public class JWTUserServiceImpl implements JWTUserService {

	@Autowired
	private UserRepository repository;

	@Override
	// This method get user data by using username this prvoide all details not
	// showing Password.
	public UserDto getUserByUsername(String username) {
		User thisUser = repository.getUserByUsername(username);
		UserDto userDto = new UserDto();

		BeanUtils.copyProperties(thisUser, userDto);

		return userDto;

	}

}
