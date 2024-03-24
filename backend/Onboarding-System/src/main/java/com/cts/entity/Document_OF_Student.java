package com.cts.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Document_OF_Student{
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long sid;

	private String fileName;

	@Lob
	@Column(columnDefinition = "LONGBLOB")
	private byte[] document;

	

}
