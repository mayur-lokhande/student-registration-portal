package com.cts.service;

import java.util.List;

import com.cts.entity.Document_OF_Student;


public interface Documentservice {
	
	List<Document_OF_Student> getByStudentID(Long sid);

}
