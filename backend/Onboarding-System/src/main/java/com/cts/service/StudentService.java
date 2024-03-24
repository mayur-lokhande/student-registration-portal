package com.cts.service;

import java.io.IOException;
import java.util.List;


import org.springframework.web.multipart.MultipartFile;

import com.cts.entity.Student;



public interface StudentService {
	public Student createStudent(Integer marks,List<MultipartFile> files) throws IOException;

	public List<Student> getAllStudents();

	public Student getStudentById(Long id);

	Student updateStudent(Student student);

	void delete(Long id);
	
	public List<Student> updateMarks();

}
