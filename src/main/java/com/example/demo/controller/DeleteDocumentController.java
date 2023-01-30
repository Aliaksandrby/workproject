package com.example.demo.controller;

import com.example.demo.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class DeleteDocumentController {

    @Autowired
    private DocumentService documentService;

    @GetMapping("/delete/{id}")
    public String deleteDocument(Model model, @PathVariable("id") Long id) {
        documentService.deleteDocument(id);
        model.addAttribute("documentList",documentService.getDocuments());
        return "ReadDocuments";
    }
}