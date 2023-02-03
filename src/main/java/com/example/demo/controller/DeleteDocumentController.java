package com.example.demo.controller;

import com.example.demo.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
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
    @Secured(value = {"ROLE_ADMIN"})
    public String deleteDocument(@PathVariable("id") Long id) {
        documentService.deleteDocument(id);
        return "redirect:/";
    }
}
