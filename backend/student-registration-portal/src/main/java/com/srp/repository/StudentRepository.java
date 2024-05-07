package com.srp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.srp.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
	public List<Student> findByEmailId(String emailId);

	public List<Student> findByMobileNumber(Long mobileNumber);

}
