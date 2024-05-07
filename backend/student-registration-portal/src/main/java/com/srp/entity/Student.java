package com.srp.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Entity
@Data
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false, unique = true)
	@NotEmpty(message = "User Email shuld not be null or empty") // validation
	@Email(message = "Email address should be valid") //validation
	private String emailId;
	@Column(nullable = false, unique = true)
	private Long mobileNumber;
	@Column(nullable = false)
	private String address;
	@Column(nullable = false)
	private Integer marks;
	
	private String status="PENDING";
	private String comment="NA";

//	public enum Status{
//		PENDING
//	}
//	public enum Comment{
//		NA
//	}
//	private Status status=Status.PENDING;
//	private Comment comment=Comment.NA;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "student_id")
	private List<Documents> documents;

	public Student() {
		this.documents = new ArrayList<>();
	}

}
