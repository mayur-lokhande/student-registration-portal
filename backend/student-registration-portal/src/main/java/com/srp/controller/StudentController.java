package com.srp.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.srp.entity.Student;
import com.srp.service.StudentService;

@RestController
@RequestMapping("/api/students")
@CrossOrigin("http://localhost:3000")
public class StudentController {

	@Autowired
	private StudentService studentService;
	
//	@Autowired
//	private StudentRepository studentrepository;
	
	
	@PostMapping
	public ResponseEntity<Student> create(@RequestBody Student student){
		Student saveStudent=studentService.createStudent(student);
		return new ResponseEntity<>(saveStudent,HttpStatus.CREATED);
	}
	
	
//	@PostMapping
//	public ResponseEntity<Student> create( @RequestParam("file") MultipartFile file, @RequestParam String name,String emailId,Long mobileNumber,String address,Integer marks) throws IOException{
//		Student s=new Student();
//		s.setName(name);
//		s.setEmailId(emailId);
//		s.setAddress(address);
//		s.setMobileNumber(mobileNumber);
//		s.setDocument(file.getBytes());
//		s.setMarks(marks);
//		Student saveStudent=studentService.createStudent(s);
//		return new ResponseEntity<>(saveStudent,HttpStatus.CREATED);
//	}
//	
//	
//    @GetMapping("/pdf/{id}")
//    public ResponseEntity<byte[]> getPdf(@PathVariable Long id) {
//
//        Optional<Student> optionalPdfDocument = studentrepository.findById(id);
//        if (optionalPdfDocument.isPresent()) {
//        	Student pdfDocument = optionalPdfDocument.get();
//            return ResponseEntity.ok()
//                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + pdfDocument.getName() + "\"")
//                    .body(pdfDocument.getDocument());
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
	
	
	@GetMapping
	public ResponseEntity<List<Student>> findAllStudents(){
		List<Student> allStudents=studentService.getAllStudents();
		return new ResponseEntity<>(allStudents,HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Student> findStudentById(@PathVariable Long id){
		Student student = studentService.getStudentById(id);
		return new ResponseEntity<>(student, HttpStatus.OK);
	}
	
	@GetMapping("/email/{email}")
	public ResponseEntity<List<Student>> getStudentByEmail(@PathVariable String email){
		System.out.println(email);
		List<Student> student=studentService.getStudentByEmailId(email);
		return new ResponseEntity<>(student,HttpStatus.OK);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Student> updateStudent(@RequestBody Student student,@PathVariable Long id){
		student.setId(id);
		Student update = studentService.updateStudent(student);
		return new ResponseEntity<>(update,HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteStudent(@PathVariable Long id){
		studentService.delete(id);
		return new ResponseEntity<>(" User Deleted successfully!!!",HttpStatus.OK);
		
	}
	
}
