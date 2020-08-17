package com.automation.utilities;

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

public class DataDrivenUtilities {

	public static String XMlData() throws SAXException, IOException, ParserConfigurationException {

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dbulider = dbFactory.newDocumentBuilder();
		Document doc= dbulider.parse(new File("C:\\Users\\M1047669\\eclipse-workspace\\Framework\\src\\main\\resources\\DataXml.xml"));

		NodeList nList = doc.getElementsByTagName("staff");
		Node nnode = nList.item(0);
		System.err.println(nnode.getNodeName());

		Element element = (Element) nnode;

		System.err.println(element.getElementsByTagName("firstname").item(0).getTextContent());

		return null;

	}

	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
		XMlData();
	}
}