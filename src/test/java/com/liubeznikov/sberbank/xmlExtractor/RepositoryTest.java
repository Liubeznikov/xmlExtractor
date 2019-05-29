package com.liubeznikov.sberbank.xmlExtractor;

import com.liubeznikov.sberbank.xmlExtractor.entities.DocumentType;
import com.liubeznikov.sberbank.xmlExtractor.repository.DocumentTypeRepository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {XmlExtractorApplication.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class RepositoryTest {

    @Autowired
    private DocumentTypeRepository documentTypeRepository;

    @Test
    public void saveTest() {
        DocumentType doc = new DocumentType();
        doc.setName("qwerty");
        DocumentType save = documentTypeRepository.save(doc);
        Assert.assertEquals(doc.getName(), save.getName());
    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void saveNullTest() {
        documentTypeRepository.save(null);
    }


    @Test
    public void saveTwoTimesTest() {
        DocumentType doc = new DocumentType();
        doc.setName("qwerty");
        documentTypeRepository.save(doc);
        documentTypeRepository.save(doc);
        List<DocumentType> list = documentTypeRepository.findAll();
        Assert.assertEquals(list.size(), 1);
    }


}
