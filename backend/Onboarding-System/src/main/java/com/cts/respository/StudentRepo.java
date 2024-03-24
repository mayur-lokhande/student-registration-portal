package com.cts.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.entity.Student;



@Repository
public interface StudentRepo extends JpaRepository<Student,Long>{


}
