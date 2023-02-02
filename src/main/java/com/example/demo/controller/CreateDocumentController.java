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

    @GetMapping("/create")
    public String createDocument() {
        return "CreateDocument";
    }


    @PostMapping("/create")
    public String createDocument(
            Document document,
            Model model,
            @RequestParam("files") MultipartFile[] files
    ) { // todo file upload last
        model.addAttribute("document",documentService.createDocument(document,files));
        return "UpdateDocument";
    }
}
