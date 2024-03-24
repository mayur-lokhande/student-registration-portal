package com.cts.service;

import org.springframework.stereotype.Service;

import com.cts.dto.UserDto;

public interface JWTUserService {

	public UserDto getUserByUsername(String usename);

}
