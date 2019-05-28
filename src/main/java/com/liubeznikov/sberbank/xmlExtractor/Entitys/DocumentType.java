package com.liubeznikov.sberbank.xmlExtractor.Entitys;

import javax.persistence.*;

@Entity
@Table(name = "DocumentType")
public class DocumentType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String documentName;
}
