package com.example.demo.service;

import com.example.demo.model.Document;
import com.example.demo.model.FileDocument;
import com.example.demo.repository.DocumentRepository;
import com.example.demo.repository.FileDocumentRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class DocumentService {
    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private FileDocumentRepository fileDocumentRepository;

    public List<Document> getDocuments() {
        return documentRepository.findAll();
    }

    public Document getDocument(Long id) {
        return documentRepository.findById(id).get();
    }

    public void deleteDocument(Long id) {
        documentRepository.delete(getDocument(id));
    }

    public void createDocument(Document document, MultipartFile[] files){
        documentRepository.save(document);
        if (!(files.length==1 && Objects.equals(files[0].getOriginalFilename(), ""))) {
            List<FileDocument> fileDocumentList = addFilesToDocument(document,files);
            document.setFileDocumentList(fileDocumentList);
        } else {
            FileDocument fileDocument = new FileDocument();
            fileDocument.setDocument(document);
            fileDocument.setFile("");
            document.setFileDocumentList(List.of(fileDocument));
            fileDocumentRepository.save(fileDocument);
        }

    }

    public void updateDocument(Document document,Long id) {
        Document updateDocument = getDocument(id);

        if(document.getType() != null) {
            updateDocument.setType(document.getType());
        }

        if(document.getName() != null) {
            updateDocument.setName(document.getName());
        }

        if(document.getDescription() != null) {
            updateDocument.setDescription(document.getDescription());
        }
    }

    @SneakyThrows
    private List<FileDocument> addFilesToDocument(Document document,MultipartFile[] files){
        List<FileDocument> fileDocumentList = new ArrayList<>();
        for (MultipartFile file : files) {
            FileDocument fileDocument = new FileDocument();
            fileDocument.setDocument(document);
            fileDocument.setFile(Base64.getEncoder().encodeToString(file.getBytes()));
            fileDocumentList.add(fileDocument);
            fileDocumentRepository.save(fileDocument);
        }
        return fileDocumentList;
    }
}
