package com.srp.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.srp.entity.Student;

public interface StudentService {
	
	public Student createStudent(String name,String emailId,Long mobileNumber,String address,Integer marks,List<MultipartFile> files) throws IOException;
	public List<Student> getAllStudents();
	public Student getStudentById(Long id);
	Student updateStudent(Student student);
	void delete(Long id);
	
	public List<Student> getStudentByLocation();
	
	public List<Student> getStudentByEmailId(String emailId);

}
