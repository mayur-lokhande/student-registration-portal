package com.srp.service;

import java.util.List;

import com.srp.entity.Documents;

public interface DocumentsService {

	public List<Documents> getByStudentId(Long studentId);
}
