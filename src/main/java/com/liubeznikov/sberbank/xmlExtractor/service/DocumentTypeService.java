package com.liubeznikov.sberbank.xmlExtractor.service;

import com.liubeznikov.sberbank.xmlExtractor.entities.DocumentType;
import com.liubeznikov.sberbank.xmlExtractor.repository.DocumentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DocumentTypeService {
    private final DocumentTypeRepository documentTypeRepository ;

    @Autowired
    public DocumentTypeService(DocumentTypeRepository documentTypeRepository) {
        this.documentTypeRepository = documentTypeRepository;
    }

    public void saveList(List<String> list){

        for(String str : list){
            DocumentType documentType = new DocumentType();
            documentType.setDocumentName(str);
            documentTypeRepository.save(documentType);
        }
    }

    public List<String> getAllDocumentTypeFromDb(){
        List<String> list = new ArrayList<>();
        for(DocumentType documentType : documentTypeRepository.findAll()){
            list.add(documentType.getDocumentName());
        }
        return list;
    }
}
