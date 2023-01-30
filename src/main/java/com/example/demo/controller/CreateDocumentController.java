package com.example.demo.controller;

import com.example.demo.model.Document;
import com.example.demo.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class CreateDocumentController {

    @Autowired
    private DocumentService documentService;

    @GetMapping("/document/new")
    public String createDocument() {
        return "CreateDocument";
    }


    @PostMapping("/document/new")
    public String createDocument(Document document, Model model, @RequestParam("files") MultipartFile[] files) {
        for (MultipartFile m : files) {
            System.out.println(m.getOriginalFilename());
        }
        documentService.createDocument(document,files);
        model.addAttribute("documentList",documentService.getDocuments());
        return "ReadDocuments";
    }
}
