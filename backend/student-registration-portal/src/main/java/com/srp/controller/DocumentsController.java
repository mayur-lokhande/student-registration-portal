package com.srp.controller;
// zip downloader
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
import com.srp.service.DocumentsService;

@RestController
@RequestMapping("/api/documents")
@CrossOrigin("http://localhost:3000")
public class DocumentsController {
    
    @Autowired
    private DocumentsService documentsService;
    
    @GetMapping("/student/{studentId}")
    public ResponseEntity<ByteArrayResource> getDocumentsByStudentId(@PathVariable Long studentId) {
        List<Documents> documents = documentsService.getByStudentId(studentId);
        
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
                headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"documents.zip\"");
                
                return ResponseEntity.ok()
                        .headers(headers)
                        .contentLength(arrayResource.contentLength())
                        .contentType(MediaType.APPLICATION_OCTET_STREAM)
                        .body(arrayResource);
            } catch (IOException e) {
                e.printStackTrace();
                return ResponseEntity.internalServerError().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.srp.entity.Documents;
//import com.srp.service.DocumentsService;
//
//@RestController
//@RequestMapping("/api/documents")
//@CrossOrigin("http://localhost:3000")
//public class DocumentsController {
//    
//    @Autowired
//    private DocumentsService documentsService;
//    
//    @GetMapping("/student/{studentId}")
//    public ResponseEntity<List<ResponseEntity<byte[]>>> getDocumentsByStudentId(@PathVariable Long studentId) {
//        List<Documents> documents = documentsService.getByStudentId(studentId);
//        
//        if (!documents.isEmpty()) {
//            // Assuming each document has a unique file name
//            List<ResponseEntity<byte[]>> responses = new ArrayList<>();
//            for (Documents document : documents) {
//                HttpHeaders headers = new HttpHeaders();
//                headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + document.getFileName() + "\"");
//                
//                ResponseEntity<byte[]> response = ResponseEntity.ok()
//                        .headers(headers)
//                        .contentLength(document.getDocument().length)
//                        .contentType(MediaType.APPLICATION_OCTET_STREAM)
//                        .body(document.getDocument());
//                
//                responses.add(response);
//            }
//            
//            return ResponseEntity.ok().body(responses);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//}
