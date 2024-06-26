package com.srp.controller;

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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.srp.entity.Documents;
//import com.srp.repository.DocumentsRepository;
import com.srp.service.DocumentsService;
import com.srp.service.StudentService;

@RestController
@RequestMapping("/api/documents")
@CrossOrigin("http://localhost:3000")
public class DocumentsController {

	@Autowired
	private DocumentsService documentsService;
	@Autowired
	private StudentService studentService;

	@GetMapping("/student/{studentId}")
	public ResponseEntity<ByteArrayResource> getDocumentsByStudentId(@PathVariable Long studentId) {
		List<Documents> documents = documentsService.getByStudentId(studentId);
		// downloading files in zip format with student names
		// (replacing space with underscore using String method replace())
		String studentName = studentService.getStudentById(studentId).getName().replace(" ", "_");
		if (!documents.isEmpty()) {
			try {
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ZipOutputStream zipOut = new ZipOutputStream(baos);

				for (Documents document : documents) {
					zipOut.putNextEntry(new ZipEntry(document.getFileName()));
					zipOut.write(document.getDocument());
					zipOut.closeEntry();
				}

				zipOut.finish();
				zipOut.close();

				ByteArrayResource arrayResource = new ByteArrayResource(baos.toByteArray());

				HttpHeaders headers = new HttpHeaders();
				// downloading zip file of student documents with student names
				headers.add(HttpHeaders.CONTENT_DISPOSITION,
						"attachment; filename=\"" + studentName + "_documents.zip\"");

				return ResponseEntity.ok().headers(headers).contentLength(arrayResource.contentLength())
						.contentType(MediaType.APPLICATION_OCTET_STREAM).body(arrayResource);
			} catch (IOException e) {
				e.printStackTrace();
				return ResponseEntity.internalServerError().build();
			}
		} else {
			return ResponseEntity.notFound().build();
		}
	}

// This code downloads single file at a time
//    @GetMapping("/std/{id}")
//    public ResponseEntity<byte[]> getPdf(@PathVariable Long id) {
//        Optional<Documents> optionalPdfDocument = documentsRepository.findById(id);
//        if (optionalPdfDocument.isPresent()) {
//            Documents pdfDocument = optionalPdfDocument.get();
//            return ResponseEntity.ok()
//                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + pdfDocument.getFileName() + "\"")
//                    .body(pdfDocument.getDocument());
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
}