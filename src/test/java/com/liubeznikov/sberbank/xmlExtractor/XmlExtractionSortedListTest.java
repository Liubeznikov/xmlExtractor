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

public class XmlExtractionSortedListTest {



    private final String XmlDataPath = "src/test/resources/data.xml";
    private final String invalidXmlDataPath = "src/test/resources/invalidXml.xml";
    private final String XmlWithoutDataPath = "src/test/resources/withoutData.xml";
    private final String XmlBadPath = "src/test/resources/data.xl";
    private final List<String> expect = Arrays.asList("ВОДИТ УДОСТОВЕРЕНИЕ", "ИНН", "ОХОТНИЧИЙ БИЛЕТ", "ПАСПОРТ МОРЯКА",
            "ПАСПОРТ РФ", "РАЗРЕШ НА ОРУЖИЕ", "СВИД О РОЖДЕНИИ", "СВИД_РЕГ_ТС", "УДОСТОВЕР ВОЕНСЛУЖ");

    @Test
    public void listTest() {
        XmlDataExtractor xmlDataExtractor = new XmlDataExtractor(XmlDataPath);
        List<String> resultList = null;

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
        XmlDataExtractor xmlDataExtractor = new XmlDataExtractor(XmlWithoutDataPath);
        List<String> resultList = null;
        try {
            Optional<List<String>> sortedDocList = xmlDataExtractor.getSortedDocList();
            if (sortedDocList.isPresent()) {
                resultList = sortedDocList.get();
            }
        } catch (XmlParseException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(null, resultList);
    }

    //@Test(expected =  IOException.class)
    @Test
    public void badXmlPathListTest() {
        XmlDataExtractor xmlDataExtractor = new XmlDataExtractor(XmlBadPath);
        List<String> resultList = null;
        try {
            Optional<List<String>> sortedDocList = xmlDataExtractor.getSortedDocList();
            if (sortedDocList.isPresent()) {
                resultList = sortedDocList.get();
            }

        } catch (XmlParseException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(null, resultList);
    }


}
