package com.liubeznikov.sberbank.xmlExtractor;

import com.liubeznikov.sberbank.xmlExtractor.exeptions.XmlParseException;
import com.liubeznikov.sberbank.xmlExtractor.service.DocumentTypeService;
import com.liubeznikov.sberbank.xmlExtractor.extractor.XmlDataExtractor;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {XmlExtractorApplication.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class SimpleTest {

    @Autowired
    DocumentTypeService documentTypeService;

   private final String XmlDataPath = "D:\\java\\xmlExtractor\\src\\test\\resources\\data.xml";

    @Test
    public void listTest(){
        XmlDataExtractor e = new XmlDataExtractor(XmlDataPath)  ;
        List<String> resultList = null;
        try {
            resultList = e.getSortedDocList().get();
        } catch (XmlParseException e1) {
            e1.printStackTrace();
        }
        for(String str : resultList){
            System.out.println(str);
        }
    }
    @Test
    public void printAttr() {
        XmlDataExtractor e = new XmlDataExtractor(XmlDataPath)  ;
        Map<String, String > map = null;
        try {
            map = e.getAttributes("1","ГРАЖДАНСТВО").get();
        } catch (XmlParseException e1) {
            e1.printStackTrace();
        }
        System.out.println(map.toString());

    }
    @Test
    public void saveAndCheckDocumentTypeInDb(){


        XmlDataExtractor e = new XmlDataExtractor(XmlDataPath)  ;
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
