package com.liubeznikov.sberbank.xmlExtractor.entity;

import javax.persistence.*;

@Entity
@Table(name = "DocumentType")
public class DocumentType {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
