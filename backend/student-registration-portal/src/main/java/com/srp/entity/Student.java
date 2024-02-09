package com.srp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String emailId;
	@Column(nullable = false)
	private Long mobileNumber;
	@Column(nullable = false)
	private String address;
	@Column(nullable = false)
	private Integer marks;

	
	//@Column(columnDefinition = "varchar(255) default 'In Progress'")
	private String status;
	private String comment;
	
//	private String documentName;
//	private String documentType;
//	@Lob
//	private byte[] document;

	
}
