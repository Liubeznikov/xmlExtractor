package com.liubeznikov.sberbank.xmlExtractor.extractor;


import com.liubeznikov.sberbank.xmlExtractor.exeptions.XmlParseException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.IOException;
import java.util.*;


public class XmlDataExtractor {

    private Document doc;
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

    public Optional<List<String>> getSortedDocList() throws XmlParseException {
        List<String> list = new ArrayList<>();
        final String expBase = "/order/services/serv/pars/";
        final String expCondition = "par[@step ='1' and contains (@name,'ВИД_ДОК')]/";
        final String expValue = "par_list/@value";
        final String expression = expBase + expCondition + expValue;
        NodeList nodeList = getNodeList(expression);
        if (nodeList.getLength() > 0) {
            for (int i = 0; i < nodeList.getLength(); i++) {
                list.add(nodeList.item(i).getNodeValue());
            }
            Collections.sort(list);
            return Optional.of(list);
        } else {
            return Optional.empty();
        }
    }

    public Optional<Map<String, String>> getAttributes(String parStep, String parName) throws XmlParseException {
        Map<String, String> map = new HashMap<>();
        final String expBase = "/order/services/serv/pars/";
        final String expCondition = "par[@step ='" + parStep + "'  and contains (@name, '" + parName + "')]/";
        final String expValue = "@*";
        final String expression = expBase + expCondition + expValue;
        NodeList nodeList = getNodeList(expression);

        if (nodeList.getLength() > 0) {
            for (int i = 0; i < nodeList.getLength(); i++) {
                map.put(nodeList.item(i).getNodeName(), nodeList.item(i).getNodeValue());
            }
            return Optional.of(map);
        } else {
            return Optional.empty();
        }

    }

    private NodeList getNodeList(String expression) throws XmlParseException {
        try {
            XPathExpression xPathExpression = xpath.compile(expression);
            return (NodeList) xPathExpression.evaluate(doc, XPathConstants.NODESET);
        } catch (XPathExpressionException e) {
            //addLog
            throw new XmlParseException(e.getMessage());
        }
    }
}
