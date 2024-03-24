package com.cts.entity;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
@Table(name = "USER")
public class User {
	
	@NotBlank(message = "First Name is mandatory field")
	@Size(min = 5,max=50,message = "Full Name shall be between 5 and 50 chars in length")
	
	private String firstName;
	@NotBlank(message = "Full Name is mandatory field")
	@Size(min = 5,max=50,message = "Full Name shall be between 5 and 50 chars in length")
	private String lastName;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@NotBlank(message = "Mobile is mandatory field")
	@Pattern(regexp = "[1-9][0-9]{9}",message = "Mobile number is a 10 digit number")

	private String phoneNumber;
	
	private String username;
	@NotBlank(message = "Mail Id is mandatory field")
	@Email(message = "Valid mail id expected")
	private String emailId;
	@Size(min = 5,max=50,message = "Password shall be between 5 and 50 chars in length")
	private String password;
	private String role = "Student";
	private boolean enabled = true;

}
