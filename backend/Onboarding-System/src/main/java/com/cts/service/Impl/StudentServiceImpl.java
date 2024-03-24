package com.cts.service.Impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cts.entity.Document_OF_Student;
import com.cts.entity.Student;
import com.cts.exception.ResourceNotFoundException;
import com.cts.respository.DocumentRepo;
import com.cts.respository.StudentRepo;
import com.cts.service.StudentService;

//import com.cts.sdu.entity.Document_OF_Student;
//
//import com.cts.sdu.entity.Student;
//import com.cts.sdu.exception.ResourceNotFoundException;
//import com.cts.sdu.repositery.DocumentRepo;
//import com.cts.sdu.repositery.StudentRepo;
//import com.cts.sdu.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepo studentrepo;
	
	@Autowired
	private DocumentRepo documentrepo;

	@Override
	public List<Student> getAllStudents() {
		// TODO Auto-generated method stub
		List<Student>  students=studentrepo.findAll();
		return students;
	}

	@Override
	public Student getStudentById(Long id) {
		// TODO Auto-generated method stub
		Optional<Student> student = studentrepo.findById(id);
		if(student.isPresent()) {
			return student.get();
		}
		else {
			throw new  ResourceNotFoundException(id);
		}
	}

	@Override
	public Student updateStudent(Student student) {
		Student student_IS_Avaliable = studentrepo.findById(student.getId()).orElseThrow(() -> new ResourceNotFoundException(student.getId()));
		
		// TODO Auto-generated method stub
		student_IS_Avaliable.setStatus(student.getStatus());
		student_IS_Avaliable.setComment(student.getComment());
		
		Student updatedStudent = studentrepo.save(student_IS_Avaliable);
		return  updatedStudent;
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method
		studentrepo.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		
		studentrepo.deleteById(id);

	}

	@Override
	public Student createStudent(Integer marks, List<MultipartFile> files) throws IOException {
		Student student = new Student();
		
		student.setMarks(marks);
		
		List<Document_OF_Student> docs = new ArrayList<>();
		for (MultipartFile file : files) {
			Document_OF_Student document_OF_Student = new Document_OF_Student();
			document_OF_Student.setFileName(file.getOriginalFilename());
			document_OF_Student.setDocument(file.getBytes());
			docs.add(documentrepo.save(document_OF_Student));
		}
		student.setDocument_OF_Student(docs);
		return studentrepo.save(student);
	}

	@Override
	public List<Student> updateMarks() {
		List<Student>  students=studentrepo.findAll();

		students.stream().forEach(s->{s.setMarks(s.getMarks()+2);studentrepo.save(s);});
		//System.out.println("This is updated marks"+students);
		return students;
	}



}
