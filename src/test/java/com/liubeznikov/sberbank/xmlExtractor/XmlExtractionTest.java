package com.liubeznikov.sberbank.xmlExtractor;

import com.liubeznikov.sberbank.xmlExtractor.exeptions.XmlParseException;
import com.liubeznikov.sberbank.xmlExtractor.service.DocumentTypeService;
import com.liubeznikov.sberbank.xmlExtractor.extractor.XmlDataExtractor;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {XmlExtractorApplication.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class XmlExtractionTest {

    @Autowired
    DocumentTypeService documentTypeService;

    private final String XmlDataPath = "D:\\java\\xmlExtractor\\src\\test\\resources\\data.xml";
    private final String invalidXmlDataPath = "D:\\java\\xmlExtractor\\src\\test\\resources\\invalidXml.xml";
    private final String XmlWithoutDataPath = "D:\\java\\xmlExtractor\\src\\test\\resources\\withoutData.xml";
    private final String XmlBadPath = "D:\\java\\xmlExtractor\\src\\test\\resources\\data.xl";

    @Test
    public void listTest() {
        XmlDataExtractor xmlDataExtractor = new XmlDataExtractor(XmlDataPath);
        List<String> resultList = null;
        List<String> expect = Arrays.asList("ВОДИТ УДОСТОВЕРЕНИЕ", "ИНН", "ОХОТНИЧИЙ БИЛЕТ", "ПАСПОРТ МОРЯКА",
                "ПАСПОРТ РФ", "РАЗРЕШ НА ОРУЖИЕ", "СВИД О РОЖДЕНИИ", "СВИД_РЕГ_ТС", "УДОСТОВЕР ВОЕНСЛУЖ");
        try {
            Optional<List<String>> sortedDocList = xmlDataExtractor.getSortedDocList();
            if (sortedDocList.isPresent()) {
                resultList = sortedDocList.get();
            }
        } catch (XmlParseException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(expect, resultList);
    }


    @Test(expected =  SAXException.class)
    public void invalidXmlListTest() {
        XmlDataExtractor xmlDataExtractor = new XmlDataExtractor(invalidXmlDataPath);
        List<String> resultList = null;

        try {
            Optional<List<String>> sortedDocList = xmlDataExtractor.getSortedDocList();
            if (sortedDocList.isPresent()) {
                resultList = sortedDocList.get();
            }
        } catch (XmlParseException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void withoutDataListTest() {
        XmlDataExtractor e = new XmlDataExtractor(XmlWithoutDataPath);
        List<String> resultList = null;
        try {
            resultList = e.getSortedDocList().get();
        } catch (XmlParseException e1) {
            e1.printStackTrace();
        }
        for (String str : resultList) {
            System.out.println(str);
        }
    }

    //@Test(expected =  IOException.class)
    @Test
    public void badUrlListTest() {
        XmlDataExtractor e = new XmlDataExtractor("123");

    }


    @Test
    public void printAttr() {
        XmlDataExtractor e = new XmlDataExtractor(XmlDataPath);
        Map<String, String> map = null;
        try {
            map = e.getAttributes("1", "ГРАЖДАНСТВО").get();
        } catch (XmlParseException e1) {
            e1.printStackTrace();
        }
        System.out.println(map.toString());

    }

    @Test
    public void saveAndCheckDocumentTypeInDb() {


        XmlDataExtractor e = new XmlDataExtractor(XmlDataPath);
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
