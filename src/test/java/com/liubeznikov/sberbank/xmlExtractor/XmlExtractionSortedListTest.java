package com.liubeznikov.sberbank.xmlExtractor;

import com.liubeznikov.sberbank.xmlExtractor.exeptions.XmlParseException;
import com.liubeznikov.sberbank.xmlExtractor.extractor.XmlDataExtractor;

import org.junit.Assert;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
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
        XmlDataExtractor xmlDataExtractor = null;
        try {
            xmlDataExtractor = new XmlDataExtractor(XmlDataPath);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            Assert.fail();
        }
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

    @Test
    public void withoutDataListTest() {
        try {
            XmlDataExtractor xmlDataExtractor = new XmlDataExtractor(XmlWithoutDataPath);
            try {
                Optional<List<String>> sortedDocList = xmlDataExtractor.getSortedDocList();
                Assert.assertFalse(sortedDocList.isPresent());
            } catch (XmlParseException e) {
                Assert.fail();
            }
        } catch (ParserConfigurationException | IOException | SAXException e) {
            Assert.fail();
        }
    }

    @Test(expected = SAXException.class)
    public void invalidXmlListTest() throws SAXException {
        try {
            new XmlDataExtractor(invalidXmlDataPath);
        } catch (ParserConfigurationException | IOException e) {
            Assert.fail();
        }
    }

    @Test(expected = IOException.class)
    public void badXmlPathListTest() throws IOException{
        try {
            new XmlDataExtractor(XmlBadPath);
        } catch (ParserConfigurationException | SAXException e) {
            Assert.fail();
        }
    }
}
