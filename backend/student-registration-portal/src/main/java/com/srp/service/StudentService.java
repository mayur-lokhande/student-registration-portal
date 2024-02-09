package com.srp.service;

import java.util.List;

import com.srp.entity.Student;

public interface StudentService {
	
	public Student createStudent(Student student);
	public List<Student> getAllStudents();
	public Student getStudentById(Long id);
	Student updateStudent(Student student);
	void delete(Long id);
	
	public List<Student> getStudentByEmailId(String emailId);

}
