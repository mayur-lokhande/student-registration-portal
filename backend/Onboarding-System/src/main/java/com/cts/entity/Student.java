package com.cts.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity

@Data
@Getter
@Setter
@NoArgsConstructor
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private Integer marks;
	private String status="panding";
	private String comment="";
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_sid")
	private List<Document_OF_Student> document_OF_Student;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "sId")
	private List<User> user;
}