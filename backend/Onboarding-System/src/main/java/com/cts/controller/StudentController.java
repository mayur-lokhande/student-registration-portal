package com.cts.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import com.cts.entity.Student;
import com.cts.exception.ResourceNotFoundException;
import com.cts.service.StudentService;

@RestController
//@RequestMapping("/Student")
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	@GetMapping("/student")
	public ResponseEntity<List<Student>> findAllStudents() {
		List<Student> allStudents = studentService.getAllStudents();
		return new ResponseEntity<>(allStudents, HttpStatus.OK);
	}
	
	@DeleteMapping("/studentdelete/{id}")
	public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
		studentService.delete(id);
		return new ResponseEntity<>(" User Deleted successfully!!!", HttpStatus.OK);

	}
	
	
	@GetMapping("/student/{id}")
	public ResponseEntity<Student> getSingleStudent(@PathVariable Long id) {
		try {
			return new ResponseEntity<Student>(studentService.getStudentById(id), HttpStatus.OK);
		} catch (ResourceNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	@PostMapping("/student/add")
	public ResponseEntity<Student> create(@RequestParam Integer marks, @RequestParam("files") List<MultipartFile> files) throws IOException {
		try {
			Student student = studentService.createStudent(marks, files);
			return new ResponseEntity<>(student, HttpStatus.CREATED);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@PutMapping("/student/{id}")
	public ResponseEntity<Student> updateStudent(@RequestBody Student student, @PathVariable Long id) {
		student.setId(id);
		Student update = studentService.updateStudent(student);
		return new ResponseEntity<>(update, HttpStatus.OK);
	}
	
	@GetMapping("/student/updatemarks")
	public ResponseEntity<List<Student>> findAllStudent(){
		List<Student> updat = studentService.updateMarks();
		return new ResponseEntity<>(updat, HttpStatus.OK);
	
}
}