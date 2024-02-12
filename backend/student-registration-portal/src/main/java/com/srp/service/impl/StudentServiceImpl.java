package com.srp.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.srp.entity.Documents;
import com.srp.entity.Student;
import com.srp.exception.ResourceNotFoundException;
import com.srp.repository.DocumentsRepository;
import com.srp.repository.StudentRepository;
import com.srp.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService{
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private DocumentsRepository documentsRepository;

//	@Override
//	public Student createStudent(Student student) {
//		Student std=studentRepository.save(student);
//		return std;
//		
//	}

	@Override
	public Student createStudent(String name, String emailId, Long mobileNumber, String address, Integer marks,
			List<MultipartFile> files) throws IOException {
		Student student=new Student();
		student.setName(name);
		List<Student> studentMail=studentRepository.findByEmailId(emailId);
		student.setEmailId(emailId);
		student.setMobileNumber(mobileNumber);
		student.setAddress(address);
		student.setMarks(marks);
		
		List<Documents> docs=new ArrayList<>();
		for(MultipartFile file:files) {
			Documents documents=new Documents();
			documents.setFileName(file.getOriginalFilename());
			documents.setDocument(file.getBytes());
			docs.add(documentsRepository.save(documents));
		}
		student.setDocuments(docs);
		return studentRepository.save(student);
	}

	@Override
	public List<Student> getAllStudents() {
		List<Student> students=studentRepository.findAll();
		return students;
	}

	@Override
	public Student getStudentById(Long id) {
		Optional<Student> student=studentRepository.findById(id);
		if(student.isPresent()) {
		return student.get();
		}
		else {
			throw new ResourceNotFoundException("Student", "ID", id);
		}
		
	}

	@Override
	public Student updateStudent(Student student) {
		Student existingStudent = studentRepository.findById(student.getId()).orElseThrow(()->new ResourceNotFoundException("Student", "ID", student.getId()));
		
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
		Student student=studentRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Student", "ID", id));
		studentRepository.deleteById(id);
		
	}

	@Override
	public List<Student> getStudentByEmailId(String emailId) {
		
		return studentRepository.findByEmailId(emailId);
	}

	@Override
	public List<Student> getStudentByLocation() {
			List<Student> std= studentRepository.findAll();
			return std.stream().filter(student->student.getAddress().equalsIgnoreCase("PUNE")).collect(Collectors.toList());
		}

	}
