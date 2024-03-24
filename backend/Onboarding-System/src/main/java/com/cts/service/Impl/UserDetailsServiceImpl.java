package com.cts.service.Impl;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.cts.dto.UserDto;
import com.cts.entity.JwtResponse;
import com.cts.entity.User;
import com.cts.exception.StudentAlreadyExistException;
import com.cts.exception.StudentNotFoundException;
import com.cts.exception.EmailIdAlreadyExistException;
import com.cts.exception.PhoneNumberAlreadyExistException;
import com.cts.exception.ServiceOrderNotFoundException;
import com.cts.exception.UserNameAlreadyExistException;
import com.cts.respository.UserRepository;
import com.cts.service.JWTUserService;
import com.cts.service.UsersDetailsService;
import com.cts.service.Impl.MyUserDetails;
import com.cts.util.JwtUtil;

@Service
public class UserDetailsServiceImpl implements UsersDetailsService, UserDetailsService {

	@Autowired
	private UserRepository repo;

	@Autowired
	private JWTUserService jWTUserService;

	@Autowired
	private JwtUtil jwtUtil;
      
	@Autowired
	private UserDetailsServiceImpl myUserDetailsService;

//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//		// fetching user from database
//
//		User user = repo.getUserByUsername(username);
//
//		if (user == null) {
//			throw new UsernameNotFoundException("Could not found user !!");
//		}
//
//		MyUserDetails UserDetails = new MyUserDetails(user);
//
//		return UserDetails;
//	}
	// create Student

	@Override
	public User adduser(User user) throws StudentAlreadyExistException, EmailIdAlreadyExistException,
			PhoneNumberAlreadyExistException, UserNameAlreadyExistException {

//		   user.setPassword(passwordencoder.encode(user.getPassword()));

		User existingStudent = repo.findById(user.getId()).orElse(null);
		User existingPhoneNumber = repo.findByphoneNumber(user.getPhoneNumber()).orElse(null);
		User existingUserName = repo.findByusername(user.getUsername()).orElse(null);
		User existingemailId = repo.findByemailId(user.getPhoneNumber()).orElse(null);

		if (existingStudent == null && existingPhoneNumber == null && existingUserName == null
				&& existingemailId == null) {
			repo.save(user);
			return repo.save(user);
		} else if (existingStudent != null && existingPhoneNumber == null && existingUserName == null
				&& existingemailId == null) {
			throw new StudentAlreadyExistException("Student Id already exists!!");
		} else if (existingStudent == null && existingPhoneNumber != null && existingUserName == null
				&& existingemailId == null) {
			throw new PhoneNumberAlreadyExistException("PhoneNumber Already exists!!");
		} else if (existingStudent == null && existingPhoneNumber == null && existingUserName != null
				&& existingemailId == null) {
			throw new UserNameAlreadyExistException("UserName Already exists!!");
		} else if (existingStudent == null && existingPhoneNumber == null && existingUserName == null
				&& existingemailId != null) {
			throw new EmailIdAlreadyExistException("EmailId Already exists!!");
		} else if (existingStudent != null && existingPhoneNumber != null && existingUserName == null
				&& existingemailId == null) {
			throw new StudentAlreadyExistException("Student ID and Phone Number already exists!!");
		} else if (existingStudent != null && existingPhoneNumber == null && existingUserName != null
				&& existingemailId == null) {
			throw new StudentAlreadyExistException("Student ID and UserName already exists!!");
		} else if (existingStudent != null && existingPhoneNumber == null && existingUserName == null
				&& existingemailId != null) {
			throw new StudentAlreadyExistException("Student ID and Email Id already exists!!");
		} else {
			throw new StudentAlreadyExistException(
					"Try to provide Unique Student Id, Phone Number, UserName and Email Id.");
		}
	}

	// update Student

	@Override
	public User updateStudent(User signupDetails) {
		User user = repo.findById((long) signupDetails.getId()).get();
		if (user.equals(null)) {
			throw new ServiceOrderNotFoundException("User not exist  : " + signupDetails.getId());
		}
		user.setId(signupDetails.getId());
		user.setEmailId(signupDetails.getEmailId());
		user.setPassword(signupDetails.getPassword());
		user.setUsername(signupDetails.getUsername());
		return repo.save(user);
	}

	@Override
	public List<User> getAllStudent() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public User getUserById(long id) {
		return repo.findById(id).orElse(null);
	}
	  @Override
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	        // Load user from the database
	        User user = repo.getUserByUsername(username);

	        if (user == null) {
	            throw new UsernameNotFoundException("User not found");
	        }

	        
	        List<GrantedAuthority> authorities = new ArrayList<>();
	        if (user.getRole().equals("Admin")) {
	            authorities.add(new SimpleGrantedAuthority("ROLE_Admin"));
	        } else {
	            authorities.add(new SimpleGrantedAuthority("ROLE_User"));
	        }

	        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
	    }
	}


