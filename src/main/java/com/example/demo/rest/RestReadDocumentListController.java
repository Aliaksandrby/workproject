package com.example.demo.rest;

import com.example.demo.model.Document;
import com.example.demo.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestReadDocumentListController {
    @Autowired
    private DocumentService documentService;

    @GetMapping(value = "/rest")
    public List<Document> readDocuments(Model model) {
        return documentService.getDocuments();
    }
}
