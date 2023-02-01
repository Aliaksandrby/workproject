package com.example.demo.service;

import com.example.demo.model.FileDocument;
import com.example.demo.repository.FileDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FileDocumentService {

    @Autowired
    private FileDocumentRepository fileDocumentRepository;
    public FileDocument getFileById(Long id) {
        return fileDocumentRepository.findById(id).get();
    }
}
