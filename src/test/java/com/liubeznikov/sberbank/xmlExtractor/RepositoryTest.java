package com.liubeznikov.sberbank.xmlExtractor;

import com.liubeznikov.sberbank.xmlExtractor.entity.DocumentType;
import com.liubeznikov.sberbank.xmlExtractor.exeptions.XmlParseException;
import com.liubeznikov.sberbank.xmlExtractor.extractor.XmlDataExtractor;
import com.liubeznikov.sberbank.xmlExtractor.repository.DocumentTypeRepository;

import com.liubeznikov.sberbank.xmlExtractor.service.DocumentTypeService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {XmlExtractorApplication.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class RepositoryTest {

    @Autowired
    private DocumentTypeRepository documentTypeRepository;

    @Autowired
    DocumentTypeService documentTypeService;

    @Test
    public void saveTest() {
        DocumentType doc = new DocumentType();
        doc.setName("someDocumentTypeName");
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

    @Test
    public void saveAndCheckDocumentTypeInDb() {
        final String XmlDataPath = "src/test/resources/data.xml";
        XmlDataExtractor e = null;
        try {
            e = new XmlDataExtractor(XmlDataPath);
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Assert.fail();
        }
        List<String> resultList = null;
        try {
            resultList = e.getSortedDocList().get();
        } catch (XmlParseException e1) {
            e1.printStackTrace();
        }
        documentTypeService.save(resultList);
        System.out.println(documentTypeService.getAll().toString());
        Assert.assertEquals(resultList, documentTypeService.getAll());
    }
}
