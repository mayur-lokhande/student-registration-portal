package com.cts.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.entity.Document_OF_Student;



@Repository
public interface DocumentRepo extends JpaRepository<Document_OF_Student, Long> {

	List<Document_OF_Student> findBysid(Long sid);

}
