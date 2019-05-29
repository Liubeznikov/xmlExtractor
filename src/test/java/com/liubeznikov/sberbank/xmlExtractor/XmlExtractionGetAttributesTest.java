package com.liubeznikov.sberbank.xmlExtractor;

import com.liubeznikov.sberbank.xmlExtractor.exeptions.XmlParseException;
import com.liubeznikov.sberbank.xmlExtractor.extractor.XmlDataExtractor;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.context.TestPropertySource;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

public class XmlExtractionGetAttributesTest {

    private final String xmlFilePath = "src/test/resources/data.xml";
    private final String invalidXmlFilePath = "src/test/resources/invalidXml.xml";
    private final String noAttributesXmlFilePath = "src/test/resources/noAttributesData.xml";

    @Test
    public void testExtractingAttributeMap() {
        try {
            XmlDataExtractor xmlDataExtractor = new XmlDataExtractor(xmlFilePath);
            Optional<Map<String, String>> attributes = xmlDataExtractor.getAttributes("1", "ГРАЖДАНСТВО");
            Assert.assertTrue(attributes.isPresent());
            Map<String, String> extractedAttibutes = attributes.get();
            Assert.assertEquals(17, extractedAttibutes.size());
            Assert.assertEquals("0", extractedAttibutes.get("double_input"));
            Assert.assertEquals("1", extractedAttibutes.get("isRequired"));
            Assert.assertEquals("0", extractedAttibutes.get("min_length"));
            Assert.assertEquals("0", extractedAttibutes.get("isScanable"));
            Assert.assertEquals("", extractedAttibutes.get("reg_exp"));
            Assert.assertEquals("1", extractedAttibutes.get("isVisible"));
            Assert.assertEquals("M", extractedAttibutes.get("type"));
            Assert.assertEquals("1", extractedAttibutes.get("isPrinted"));
            Assert.assertEquals("0", extractedAttibutes.get("from_debt"));
            Assert.assertEquals("1", extractedAttibutes.get("isEditable"));
            Assert.assertEquals("ГРАЖДАНСТВО", extractedAttibutes.get("name"));
            Assert.assertEquals("Выберите гражданство, лица за кого оплачивается услуга, из списка", extractedAttibutes.get("comment"));
            Assert.assertEquals("1", extractedAttibutes.get("step"));
            Assert.assertEquals("Гражданство", extractedAttibutes.get("fullname"));
            Assert.assertEquals("1", extractedAttibutes.get("isValidateOnLine"));
            Assert.assertEquals("РОССИЯ", extractedAttibutes.get("value"));
            Assert.assertEquals("250", extractedAttibutes.get("max_length"));
        } catch (XmlParseException | ParserConfigurationException | IOException | SAXException ex) {
            Assert.fail();
        }
    }

    @Test(expected = SAXException.class)
    public void testExtractingAttributeMapFromInvalidXml() throws SAXException {
        try {
            new XmlDataExtractor(invalidXmlFilePath);
        } catch (ParserConfigurationException | IOException ex) {
            Assert.fail();
        } catch (SAXException e) {
            Assert.assertTrue(e.getMessage().contains("The element type \"pars\" must be terminated by the matching end-tag \"</pars>\"."));
            throw e;
        }
    }

    @Test
    public void testExtractingEmptyAttributeMap() {
        try {
            XmlDataExtractor xmlDataExtractor = new XmlDataExtractor(noAttributesXmlFilePath);
            Optional<Map<String, String>> attributes = xmlDataExtractor.getAttributes("1", "ГРАЖДАНСТВО");
            Assert.assertFalse(attributes.isPresent());
        } catch (XmlParseException | ParserConfigurationException | IOException | SAXException ex) {
            Assert.fail();
        }
    }

    @Test
    public void testPrintingMapValues() {
        try {
            XmlDataExtractor xmlDataExtractor = new XmlDataExtractor(xmlFilePath);
            Optional<Map<String, String>> attributes = xmlDataExtractor.getAttributes("1", "ГРАЖДАНСТВО");
            Assert.assertTrue(attributes.isPresent());
            Map<String, String> extractedAttibutes = attributes.get();
            StringBuilder sb = new StringBuilder();
            extractedAttibutes.forEach((key, value) -> {
                sb.append(key).append(" -> ").append(value).append(", ");
            });
            System.out.println(sb);
        } catch (XmlParseException | ParserConfigurationException | IOException | SAXException ex) {
            Assert.fail();
        }
    }
}
