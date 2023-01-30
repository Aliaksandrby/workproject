package com.example.demo.controller;

import com.example.demo.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReadDocumentListController {

    @Autowired
    private DocumentService documentService;

    @GetMapping
    public String readDocuments(Model model) {
        model.addAttribute("documentList",documentService.getDocuments());
        return "ReadDocuments";
    }
}
