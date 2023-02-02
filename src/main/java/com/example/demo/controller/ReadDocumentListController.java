package com.example.demo.controller;

import com.example.demo.service.DocumentService;
import com.example.demo.service.PagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ReadDocumentListController {

    @Autowired
    private DocumentService documentService;

    @Autowired
    private PagingService pagingService;

    @GetMapping
    public String readDocuments() {
        return "redirect:/page/1";
    }

    @GetMapping("/page/{offset}")
    public String readPageDocuments(Model model, @PathVariable("offset") int offset) {
        int numberOfDocumentsOnAPage = 10;
        int sizePaging = 10;
        model.addAttribute("documentList",documentService.getPageDocuments(offset-1,numberOfDocumentsOnAPage));
        model.addAttribute("offset",offset);
        model.addAttribute("startPage",pagingService.getStartPage(offset,numberOfDocumentsOnAPage,sizePaging));
        model.addAttribute("endPage",pagingService.getEndPage(offset,numberOfDocumentsOnAPage,sizePaging));
        model.addAttribute("numberOfPages",pagingService.getNumberOfPages(numberOfDocumentsOnAPage));
        return "ReadDocuments";
    }


}
