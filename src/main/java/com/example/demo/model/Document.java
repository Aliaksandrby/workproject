package com.example.demo.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Document")
@Table(name = "t_documents")
public class Document {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String description;

    @OneToMany(fetch = FetchType.EAGER,
            mappedBy = "document",
            cascade = CascadeType.REMOVE)
    private List<FileDocument> fileDocumentList;
}
