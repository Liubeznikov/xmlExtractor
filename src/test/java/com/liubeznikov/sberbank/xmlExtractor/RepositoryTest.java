package com.liubeznikov.sberbank.xmlExtractor;

import com.liubeznikov.sberbank.xmlExtractor.entities.DocumentType;
import com.liubeznikov.sberbank.xmlExtractor.repository.DocumentTypeRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)

@SpringBootTest(classes = {XmlExtractorApplication.class})
public class RepositoryTest {
    @Autowired
    private DocumentTypeRepository documentTypeRepository ;
    @Test
    public void saveTest(){

        DocumentType doc = new DocumentType();
        System.out.println(doc.getId());
        doc.setDocumentName("qwerty");
        DocumentType save = documentTypeRepository.save(doc);
        System.out.println(save);


    }
}
