package com.generate.stage;

import java.io.File;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ReadChangelist {
	
		Document readChangeXML() throws ParserConfigurationException, SAXException, IOException{
		File xml =new File("C:\\Users\\hraja\\Downloads\\CustomTest.change.xml");
		System.out.println("File Read successfully");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(xml);
        //validate changelist by this tag
        System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
        //Check if there is addForeignKey, if not, check for addBaseObjectColumn, if not check for addBaseObject,
        //if not, then terminate as there are no BOs
        NodeList nList = doc.getElementsByTagName("addForeignKey");
        //Read stage tables
        nList = doc.getElementsByTagName("addStagingTable");
        System.out.println("number of staging tables = "+nList.getLength());
        Node stageTable = nList.item(0);
        Element element = (Element) stageTable;
        System.out.println("Staging table ID = "+element.getAttribute("id"));
        //some random testing
        nList = doc.getElementsByTagName("changes");
        System.out.println(nList.getLength());
        System.out.println(stageTable.getNodeName());
        
        
        return doc;
	}
}
