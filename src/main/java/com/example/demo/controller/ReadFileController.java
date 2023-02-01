package com.example.demo.controller;

import com.example.demo.service.FileDocumentService;
import com.example.demo.service.StringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Controller
public class ReadFileController {
    @Autowired
    private FileDocumentService fileDocumentService;

    @GetMapping("/{id}")
    public ResponseEntity<InputStreamResource> getFile(@PathVariable("id") Long id) throws IOException {
        File file = new File("storage/"+id + "." + StringService.getTypeOfFile(fileDocumentService.getFileById(id).getFileName()));
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
                .body(resource);
    }
}
