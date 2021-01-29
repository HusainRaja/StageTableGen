package com.generate.stage;


import java.io.IOException;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class GenerateStage {
	public GenerateStage() throws ParserConfigurationException, SAXException, IOException, TransformerException {
		ReadChangelist readChangelist = new ReadChangelist();
		Document doc = readChangelist.readChangeXML();
		getInputs();
		doc = modifyChangelist(doc);
		WriteChangelist writeChangeList = new WriteChangelist();
		
		writeChangeList.applyChanges(doc);	
		
	}

	String displayName,physicalName,parentName,sourceSystem,dataSpace,indexSpace;
	boolean fillOnGap=false,cellUpdate=false;
	
	public static void main (String args[]) throws ParserConfigurationException, SAXException, IOException, TransformerException {
		new GenerateStage();		
	}

	private Document modifyChangelist(Document doc) {
		NodeList changeList = doc.getElementsByTagName("changes");
		Element change = (Element) changeList.item(0);
		Element stageTable = doc.createElement("addStagingTable");
		stageTable.setAttribute("id", "STAGING_TABLE." + physicalName);
		stageTable.setAttribute("parentId", "BASE_OBJECT." + parentName);
		Element stageElement = null;
		stageElement = doc.createElement("tableName");
		stageElement.appendChild(doc.createTextNode(physicalName));
        stageTable.appendChild(stageElement);
		change.appendChild(stageTable);
		
		return doc;
		
	}

	private void getInputs() {
		Scanner s = new Scanner(System.in);
		System.out.println("Enter the display name");
		displayName = s.nextLine();
		displayName = displayName.toUpperCase();
		System.out.println("Enter the physical name");
		physicalName = s.nextLine();
		physicalName = physicalName.toUpperCase();
		System.out.println("Enter the parent name");
		parentName = s.nextLine();
		parentName = parentName.toUpperCase();
		System.out.println("Enter the source system");
		sourceSystem = s.nextLine();
		sourceSystem = sourceSystem.toUpperCase();
		System.out.println("Enter the Data table space name");
		dataSpace = s.nextLine();
		dataSpace = dataSpace.toUpperCase();
		System.out.println("Enter the Index table space name");
		indexSpace = s.nextLine();
		indexSpace = indexSpace.toUpperCase();
		System.out.println("Enable fill on gap? [false]");
		fillOnGap = s.nextBoolean();
		System.out.println("Enable Cell update? [false]");
		cellUpdate = s.nextBoolean();
		s.close();
	}

}
