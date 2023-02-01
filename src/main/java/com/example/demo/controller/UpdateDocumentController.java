package com.example.demo.controller;

import com.example.demo.model.Document;
import com.example.demo.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UpdateDocumentController {


    @Autowired
    private DocumentService documentService;

    @GetMapping("/update/{id}")
    public String updateDocument(Model model, @PathVariable("id") Long id) {
        model.addAttribute("document",documentService.getDocument(id));
        return "UpdateDocument";
    }


    @PostMapping("/update/{id}")
    public String updateDocument(
            @PathVariable("id") Long id,
            Document document,
            Model model,
            @RequestParam("files") MultipartFile[] files
    ) {
        model.addAttribute("document",documentService.updateDocument(document,id,files));
        return "UpdateDocument";
    }

}
