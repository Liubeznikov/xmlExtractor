package com.liubeznikov.sberbank.xmlExtractor.services;



import org.hibernate.boot.jaxb.SourceType;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



public class XmlDataExtractor {

    private Document doc ;
    private XPath xpath;


    public XmlDataExtractor(String xmlUrl) {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            doc = builder.parse(xmlUrl);
            XPathFactory xpathFactory = XPathFactory.newInstance();
            xpath = xpathFactory.newXPath();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

    }

    public List<String> getSortedDocList (){
        List<String> list = new ArrayList<>();
        try {
            // получаем список всех узлов, которые отвечают условию
            XPathExpression xPathExpression = xpath.compile(
                    "/order/services/serv/pars/par[3]/par_list/@value"
            );
            NodeList nodeList = (NodeList) xPathExpression.evaluate(doc, XPathConstants.NODESET);
            for (int i = 0; i < nodeList.getLength(); i++)
                list.add(nodeList.item(i).getNodeValue());
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        Collections.sort(list);
        return list;
    }

    public void printAttributes(String parStep, String parName){
        List<String> list = new ArrayList<>();
        try {

            XPathExpression xPathExpression = xpath.compile(
                    "/order/services/serv/pars/par[ @step =" + parStep +
                            "  and contains (@name, " + parName + ") ]/@value"
            );
            NodeList nodeList = (NodeList) xPathExpression.evaluate(doc, XPathConstants.NODESET);
            for (int i = 0; i < nodeList.getLength(); i++)
                list.add(nodeList.item(i).getNodeValue());
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }

        System.out.println(list.toString());
    }






}
