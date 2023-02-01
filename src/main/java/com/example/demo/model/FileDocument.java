package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "FileDocument")
@Table(name = "t_file_documents")
public class FileDocument {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn (name="document_id")
    private Document document;

    @Column(name = "file_name")
    private String fileName;

}
