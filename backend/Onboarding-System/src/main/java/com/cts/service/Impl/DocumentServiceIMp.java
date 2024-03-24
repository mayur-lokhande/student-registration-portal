package com.cts.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.entity.Document_OF_Student;
import com.cts.respository.DocumentRepo;
import com.cts.service.Documentservice;

//import com.cts.sdu.entity.Document_OF_Student;
//import com.cts.sdu.repositery.DocumentRepo;
//import com.cts.sdu.service.Documentservice;

@Service
public class DocumentServiceIMp implements Documentservice {

	@Autowired
	private DocumentRepo documentrepo;
	
	@Override
	public List<Document_OF_Student> getByStudentID(Long sid) {
		// TODO Auto-generated method stub
		return documentrepo.findBysid(sid);
	}

}
