package com.liubeznikov.sberbank.xmlExtractor.service;

import com.liubeznikov.sberbank.xmlExtractor.entity.DocumentType;
import com.liubeznikov.sberbank.xmlExtractor.repository.DocumentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocumentTypeService {
    private final DocumentTypeRepository documentTypeRepository;

    @Autowired
    public DocumentTypeService(DocumentTypeRepository documentTypeRepository) {
        this.documentTypeRepository = documentTypeRepository;
    }

    public void save(List<String> list) {
        List<DocumentType> docList = list.stream()
                .map(documentName -> {
                    DocumentType documentType = new DocumentType();
                    documentType.setName(documentName);
                    return documentType;
                }).collect(Collectors.toList());

        documentTypeRepository.saveAll(docList);
    }

    public List<String> getAll() {
        return documentTypeRepository.findAll().stream()
                .map(DocumentType::getName)
                .collect(Collectors.toList());
    }
}
