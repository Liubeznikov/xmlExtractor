package com.liubeznikov.sberbank.xmlExtractor;

import com.liubeznikov.sberbank.xmlExtractor.exeptions.XmlParseException;
import com.liubeznikov.sberbank.xmlExtractor.extractor.XmlDataExtractor;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;
import java.util.Optional;

public class XmlExtractionAttributesTest {

    private final String xmlFilePath = "src/test/resources/data.xml";

    //positive test
    @Test
    public void printAttr() {
        XmlDataExtractor e = new XmlDataExtractor(xmlFilePath);
        Map<String, String> map = null;
        try {
            Optional<Map<String, String>> attributes = e.getAttributes("1", "ГРАЖДАНСТВО");
            Assert.assertTrue(attributes.isPresent());
        } catch (XmlParseException ex) {
            Assert.fail();
        }
    }
}
