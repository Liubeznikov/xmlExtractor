package com.liubeznikov.sberbank.xmlExtractor.repository;

import com.liubeznikov.sberbank.xmlExtractor.entities.DocumentType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentTypeRepository extends JpaRepository<DocumentType, Long> {

}