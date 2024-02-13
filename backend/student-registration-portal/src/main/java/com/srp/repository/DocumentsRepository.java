package com.srp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.srp.entity.Documents;

@Repository
public interface DocumentsRepository extends JpaRepository<Documents, Long> {

	@Query(nativeQuery = true, value = "SELECT * FROM documents d WHERE d.student_id= :studentId")
	public List<Documents> findByStudentId(@Param("studentId") Long studentId);
}
