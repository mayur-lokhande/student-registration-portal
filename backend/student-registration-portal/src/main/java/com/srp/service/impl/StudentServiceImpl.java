package com.srp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srp.entity.Student;
import com.srp.repository.StudentRepository;
import com.srp.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService{
	
	@Autowired
	private StudentRepository studentRepository;

	@Override
	public Student createStudent(Student student) {
		Student std=studentRepository.save(student);
		return std;
		
	}

	@Override
	public List<Student> getAllStudents() {
		List<Student> students=studentRepository.findAll();
		return students;
	}

	@Override
	public Student getStudentById(Long id) {
		Student student=studentRepository.findById(id).get();
		return student;
	}

	@Override
	public Student updateStudent(Student student) {
		Student existingStudent = studentRepository.findById(student.getId()).get();
		
//		existingStudent.setName(student.getName());
//		existingStudent.setEmailId(student.getEmailId());
//		existingStudent.setMobileNumber(student.getMobileNumber());
//		existingStudent.setAddress(student.getAddress());
//		existingStudent.setMarks(student.getMarks());
		existingStudent.setStatus(student.getStatus());
		existingStudent.setComment(student.getComment());
		
		Student updatedStudent=studentRepository.save(existingStudent);
		return updatedStudent;
	}

	@Override
	public void delete(Long id) {
		studentRepository.deleteById(id);
		
	}

	@Override
	public List<Student> getStudentByEmailId(String emailId) {
		
		return studentRepository.findByEmailId(emailId);
	}

}
