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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
//@NoArgsConstructor
@AllArgsConstructor
//@Builder
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
//	@NotEmpty(message = "User Email shuld not be null or empty") //validation
//	@Email(message = "Email address should be valid") //validation
	private String emailId;
	@Column(nullable = false)
	private Long mobileNumber;
	@Column(nullable = false)
	private String address;
	@Column(nullable = false)
	private Integer marks;
	
	//@Column(columnDefinition = "varchar(255) default 'In Progress'")
	private String status="Pending";
	private String comment="NA";
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "student_id")
	private List<Documents> documents;
	
	public Student() {
		this.documents=new ArrayList<>();
	}
	
}
