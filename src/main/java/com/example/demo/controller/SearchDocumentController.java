package com.example.demo.controller;

import com.example.demo.model.Document;
import com.example.demo.search.SearchObject;
import com.example.demo.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Objects;

@Controller
public class SearchDocumentController {

    @Autowired
    private DocumentService documentService;

    @PostMapping("/search")
    public String searchDocuments(SearchObject searchObject, Model model) {
        model.addAttribute("documentList",documentService.searchDocuments(searchObject));
        return "SearchDocuments";
    }
}
