package com.srp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srp.entity.Documents;
import com.srp.repository.DocumentsRepository;
import com.srp.service.DocumentsService;

@Service
public class DocumentsServiceImpl implements DocumentsService {

	@Autowired
	private DocumentsRepository documentsRepository;

	@Override
	public List<Documents> getByStudentId(Long studentId) {
		return documentsRepository.findByStudentId(studentId);
	}
}
