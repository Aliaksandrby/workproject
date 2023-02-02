package com.example.demo.service;

import com.example.demo.model.Document;
import com.example.demo.model.FileDocument;
import com.example.demo.repository.DocumentRepository;
import com.example.demo.repository.FileDocumentRepository;
import com.example.demo.search.SearchObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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

    public Document createDocument(Document document, MultipartFile[] files) {
        documentRepository.save(document);
        createNewFilesDocument(document,files);
        return getDocument(document.getId());
    }

    public void deleteDocument(Long id) {
        Document document = documentRepository.findById(id).get();
        deleteFilesDocument(document.getFileDocumentList());
        documentRepository.delete(document);

    }

    public Document updateDocument(Document document, Long id, MultipartFile[] files) {
        Document document1 = documentRepository.findById(id).get();
        document1.setDescription(document.getDescription());

        if(!(files.length == 1 && Objects.equals(files[0].getOriginalFilename(), ""))) {
            deleteFilesDocument(document1.getFileDocumentList());
            createNewFilesDocument(document1,files);
        }
        return document1;
    }


    private void createNewFilesDocument(Document document, MultipartFile[] files) {
        for (MultipartFile file : files) {
            String fileName = file.getOriginalFilename();

            FileDocument fileDocument = new FileDocument();
            fileDocument.setDocument(document);
            fileDocument.setFileName(fileName);
            fileDocumentRepository.save(fileDocument);

            File folder = new File("storage");
            if(!folder.mkdir()) System.out.println("the folder didn't create");

            try {
                file.transferTo(
                        new File(
                                ""
                                        + folder.getAbsolutePath() + "/"
                                        + fileDocument.getId() + "."
                                        + StringService.getTypeOfFile(fileName)
                        )
                );
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void deleteFilesDocument(List<FileDocument> fileDocumentList) {
        for (FileDocument fd : fileDocumentList) {
            File file = new File("storage/"+fd.getId() + "." + StringService.getTypeOfFile(fd.getFileName()));
            if(!file.delete()) System.out.println("the file didn't delete");
            fileDocumentRepository.delete(fd);
        }
    }

    public List<Document> searchDocuments(SearchObject searchObject) { //todo it's slow to work for pagination
        List<Document> documentList = new ArrayList<>();
        if(!Objects.equals(searchObject.getDescription(), "")) {
            for (Document document : getDocuments()){
                if(document.getDescription().contains(searchObject.getDescription())) {
                    documentList.add(document);
                }
            }
        }
        return documentList;
    }

    public Page<Document> getPageDocuments(int page,int size) {
        return documentRepository.findAll(PageRequest.of(page,size));
    }
}
