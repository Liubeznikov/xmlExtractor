package com.liubeznikov.sberbank.xmlExtractor.entities;

import javax.persistence.*;

@Entity
@Table(name = "DocumentType")
public class DocumentType {

    @Id
    @GeneratedValue
    private Long id;
    private String documentName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }
}
