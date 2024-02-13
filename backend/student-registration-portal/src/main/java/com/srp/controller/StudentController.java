package com.srp.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.srp.entity.Student;
import com.srp.exception.ResourceNotFoundException;
import com.srp.service.StudentService;

@RestController
@RequestMapping("/api/students")
@CrossOrigin("http://localhost:3000")
public class StudentController {
	@Autowired
	private StudentService studentService;

//	@PostMapping
//	public ResponseEntity<Student> create(@RequestBody Student student){
//		Student saveStudent=studentService.createStudent(student);
//		return new ResponseEntity<>(saveStudent,HttpStatus.CREATED);
//	}

	@PostMapping
	public ResponseEntity<Student> create(@RequestParam String name, String emailId, Long mobileNumber, String address,
			Integer marks, @RequestParam("files") List<MultipartFile> files) throws IOException {
		try {
			Student student = studentService.createStudent(name, emailId, mobileNumber, address, marks, files);
			return new ResponseEntity<>(student, HttpStatus.CREATED);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("{id}")
	public ResponseEntity<Student> getSingleStudent(@PathVariable Long id) {
		try {
			return new ResponseEntity<Student>(studentService.getStudentById(id), HttpStatus.OK);
		} catch (ResourceNotFoundException e) {
			throw new ResourceNotFoundException("Student", "ID", id);
		}
	}

	@GetMapping
	public ResponseEntity<List<Student>> findAllStudents() {
		List<Student> allStudents = studentService.getAllStudents();
		return new ResponseEntity<>(allStudents, HttpStatus.OK);
	}

	@GetMapping("/email/{email}")
	public ResponseEntity<List<Student>> getStudentByEmail(@PathVariable String email) {
		System.out.println(email);
		List<Student> student = studentService.getStudentByEmailId(email);
		return new ResponseEntity<>(student, HttpStatus.OK);
	}

	@PutMapping("{id}")
	public ResponseEntity<Student> updateStudent(@RequestBody Student student, @PathVariable Long id) {
		student.setId(id);
		Student update = studentService.updateStudent(student);
		return new ResponseEntity<>(update, HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
		studentService.delete(id);
		return new ResponseEntity<>(" User Deleted successfully!!!", HttpStatus.OK);

	}

	@GetMapping("location/{address}")
	public ResponseEntity<List<Student>> findByAddress() {
		List<Student> student = studentService.getStudentByLocation();
		return new ResponseEntity<>(student, HttpStatus.OK);
	}

	@GetMapping("get")
	public ResponseEntity<List<Student>> findAllStudent() {
		List<Student> allStudents = studentService.getStudentByLocation();
		return new ResponseEntity<>(allStudents, HttpStatus.OK);
	}

}
