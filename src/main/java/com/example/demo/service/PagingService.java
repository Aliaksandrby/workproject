package com.example.demo.service;

import com.example.demo.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PagingService {
    @Autowired
    private DocumentRepository documentRepository;

    public Long getNumberOfDocuments() {
        return documentRepository.getNumberOfDocuments();
    }

    public int getNumberOfPages(int numberOfDocumentsOnAPage) {
        return (int)Math.ceil(getNumberOfDocuments()*1.0/numberOfDocumentsOnAPage);
    }

    public int getStartPage(int currentPage,int numberOfDocumentsOnAPage, int sizePaging) {
        int numberOfPages = getNumberOfPages(numberOfDocumentsOnAPage);
        int startPage;
        if(currentPage < sizePaging) {
            startPage = 1;
        } else {
            if(numberOfPages == currentPage) {
                startPage = currentPage - (sizePaging-1);
            } else {
                startPage = currentPage - (sizePaging-2);
            }
        }
        return startPage;
    }

    public int getEndPage(int currentPage, int numberOfDocumentsOnAPage, int sizePaging) {
        int numberOfPages = getNumberOfPages(numberOfDocumentsOnAPage);
        int endPage;
        if(currentPage < sizePaging) {
            endPage = Math.min(numberOfPages, sizePaging);
            if(numberOfPages == 0) {
                endPage = 1;
            }
        } else {
            if(numberOfPages == currentPage) {
                endPage = currentPage;
            } else {
                endPage = currentPage + 1;
            }
        }
        return endPage;
    }
}

