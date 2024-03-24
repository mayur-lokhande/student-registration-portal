package com.cts.controller;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.entity.Document_OF_Student;
import com.cts.service.Documentservice;
import com.cts.service.StudentService;




@RestController
@RequestMapping("/api/document")
public class DoucumentController {
	
	@Autowired
	private StudentService studentService;

	@Autowired
	private Documentservice documentService;

	
	@GetMapping("/student/{sid}")
	public ResponseEntity<ByteArrayResource> getdocumentBysid(@PathVariable Long sid) {
		List<Document_OF_Student> document = documentService.getByStudentID(sid);
		if (!document.isEmpty()) {
			try {
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ZipOutputStream zipOut = new ZipOutputStream(baos);

				for (Document_OF_Student documents : document) {
					zipOut.putNextEntry(new ZipEntry(documents.getFileName()));
					zipOut.write(documents.getDocument());
					zipOut.closeEntry();
				}

				zipOut.finish();
				zipOut.close();

				ByteArrayResource arrayResource = new ByteArrayResource(baos.toByteArray());

				HttpHeaders headers = new HttpHeaders();
				
				headers.add(HttpHeaders.CONTENT_DISPOSITION,
						"attachment; filename=\"" + sid + "_documents.zip\"");

				return ResponseEntity.ok().headers(headers).contentLength(arrayResource.contentLength()).contentType(MediaType.APPLICATION_OCTET_STREAM).body(arrayResource);
			} catch (IOException e) {
				e.printStackTrace();
				return ResponseEntity.internalServerError().build();
			}
		} else {
			return ResponseEntity.notFound().build();
		}
	}


}