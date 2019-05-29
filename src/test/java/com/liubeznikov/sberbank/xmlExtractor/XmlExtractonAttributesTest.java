package com.liubeznikov.sberbank.xmlExtractor;

import com.liubeznikov.sberbank.xmlExtractor.exeptions.XmlParseException;
import com.liubeznikov.sberbank.xmlExtractor.extractor.XmlDataExtractor;
import org.junit.Test;

import java.util.Map;

public class XmlExtractonAttributesTest {

    private final String XmlDataPath = "D:\\java\\xmlExtractor\\src\\test\\resources\\data.xml";

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
}
