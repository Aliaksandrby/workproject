package com.example.demo.repository;

import com.example.demo.model.Document;
import com.example.demo.model.FileDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileDocumentRepository extends JpaRepository<FileDocument,Long> {
}
