package com.liubeznikov.sberbank.xmlExtractor.service;

import com.liubeznikov.sberbank.xmlExtractor.entities.DocumentType;
import com.liubeznikov.sberbank.xmlExtractor.repository.DocumentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        List<String> list = new ArrayList<>();
        for (DocumentType documentType : documentTypeRepository.findAll()) {
            list.add(documentType.getName());
        }
        return list;
    }
}
