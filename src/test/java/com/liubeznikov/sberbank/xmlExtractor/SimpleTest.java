package com.liubeznikov.sberbank.xmlExtractor;

import com.liubeznikov.sberbank.xmlExtractor.services.XmlDataExtractor;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class SimpleTest {

    @Test
    public void myTest(){
        Assert.assertEquals(1,1);
    }
    @Test
    public void listTest(){
        XmlDataExtractor e = new XmlDataExtractor("D:\\java\\xmlExtractor\\src\\test\\resources\\data.xml")  ;
        List<String> resultList = e.getSortedDocList();
        for(String str : resultList){
            System.out.println(str);
        }
    }
    @Test
    public void printAttr(){
        XmlDataExtractor e = new XmlDataExtractor("D:\\java\\xmlExtractor\\src\\test\\resources\\data.xml")  ;
        e.printAttributes("1","ГРАЖДАНСТВО");

    }


}
