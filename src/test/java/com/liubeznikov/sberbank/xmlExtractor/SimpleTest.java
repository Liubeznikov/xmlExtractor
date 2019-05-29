package com.liubeznikov.sberbank.xmlExtractor;

import com.liubeznikov.sberbank.xmlExtractor.service.DocumentTypeService;
import com.liubeznikov.sberbank.xmlExtractor.utils.XmlDataExtractor;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
@RunWith(SpringRunner.class)

@SpringBootTest(classes = {XmlExtractorApplication.class})
public class SimpleTest {

    @Autowired
    DocumentTypeService documentTypeService;

   private final String XmlDataPath = "D:\\java\\xmlExtractor\\src\\test\\resources\\data.xml";

    @Test
    public void listTest(){
        XmlDataExtractor e = new XmlDataExtractor(XmlDataPath)  ;
        List<String> resultList = e.getSortedDocList();
        for(String str : resultList){
            System.out.println(str);
        }
    }
    @Test
    public void printAttr(){
        XmlDataExtractor e = new XmlDataExtractor(XmlDataPath)  ;
        e.printAttributes("1","ГРАЖДАНСТВО");

    }
    @Test
    public void saveAndCheckDocumentTypeInDb(){

        //AddDocumentTypeInDb addDocumentTypeInDb = new AddDocumentTypeInDb();
        XmlDataExtractor e = new XmlDataExtractor(XmlDataPath)  ;
        List<String> resultList = e.getSortedDocList();
        documentTypeService.saveList(resultList);

        System.out.println(documentTypeService.getAllDocumentTypeFromDb().toString());
        Assert.assertEquals(resultList, documentTypeService.getAllDocumentTypeFromDb());

    }


}
