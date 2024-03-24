package com.cts.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cts.dto.UserDto;

import com.cts.entity.JwtResponse;
import com.cts.entity.Student;
import com.cts.entity.User;
import com.cts.exception.StudentAlreadyExistException;
import com.cts.exception.EmailIdAlreadyExistException;
import com.cts.exception.PhoneNumberAlreadyExistException;
import com.cts.exception.UserNameAlreadyExistException;
import com.cts.respository.UserRepository;
import com.cts.service.JWTUserService;
import com.cts.service.StudentService;
import com.cts.service.Impl.JWTUserServiceImpl;
import com.cts.service.Impl.UserDetailsServiceImpl;
import com.cts.util.JwtUtil;

//@EnableGlobalMethodSecurity(prePostEnabled = true)
@RestController
@CrossOrigin(origins = "*")

public class AuthController {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UserRepository repo;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JWTUserServiceImpl jWtUserServiceIMpl;

	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	

	@Autowired
	private JWTUserService jWTUserService;
	
	//To Get current user details
	//showing all details accepts Password
	@GetMapping("/user-detail")
	public ResponseEntity<UserDto> currentUserName(Principal principal) {
		UserDto userDto;
		String userName = principal.getName();

		try {
			userDto = jWTUserService.getUserByUsername(userName);
		} catch (Exception e) {

			return ResponseEntity.noContent().build();

		}

		if (userDto == null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(userDto);
	}
	
	
	// Gating all Student 
	@GetMapping("/Student")
	public List<User> getAllStudent() {

		return userDetailsServiceImpl.getAllStudent();
	}

	//update Student details
	@PatchMapping("/update")
	public User updateStudent(@RequestBody User signupDetails) {

		return userDetailsServiceImpl.updateStudent(signupDetails);
	}
	// For create  new user
	@PostMapping("/add-user")
	public ResponseEntity<User> createAddUser(@RequestBody User user) throws StudentAlreadyExistException, EmailIdAlreadyExistException, PhoneNumberAlreadyExistException, UserNameAlreadyExistException {
		User user1 = userDetailsServiceImpl.adduser(user);
		return new ResponseEntity<>(user1, HttpStatus.CREATED);

	}

	//For generating JWT token
	@PostMapping("/token")
	public ResponseEntity<?> generateToken(@RequestBody User user) {
		try {
			authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		} catch (BadCredentialsException e) {

			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid User");
		}

		UserDetails userDetails = this.userDetailsServiceImpl.loadUserByUsername(user.getUsername());

		String token = this.jwtUtil.generateToken(userDetails);
		return ResponseEntity.ok(new JwtResponse(token));

	}
}
