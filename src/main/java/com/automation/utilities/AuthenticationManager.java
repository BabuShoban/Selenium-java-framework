package com.automation.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class AuthenticationManager {

	public static String File_Load = "/Common.properties";
	private static Properties pageProperties = new PropertyLoader(File_Load).load();
	int row;

	static List<String> items = new ArrayList<String>();

	public static AuthenticationManager singleton = new AuthenticationManager();

	public AuthenticationManager instance() {
		return singleton;
	}

	public AuthenticationToken getUserCredentials(String Username) throws Throwable {

		File path = new File(pageProperties.getProperty("authenticationData"));
		FileInputStream excel = new FileInputStream(path);

		XSSFWorkbook xs = new XSSFWorkbook(excel);
		XSSFSheet sh = xs.getSheetAt(0);

		for (int i = 0; i <= sh.getLastRowNum(); i++) {

			XSSFCell cel = sh.getRow(i).getCell(0);
			items.add(cel.toString());
			System.err.println(items);
		}

		try {

			for (row = 0; row < items.size(); row++) {
				if (items.get(row).equalsIgnoreCase(Username)) {

					System.out.println(sh.getRow(row).getCell(1).toString() + "\t"
							+ sh.getRow(row).getCell(2).toString() + "\t" + sh.getRow(row).getCell(3).toString());
					break;

				}
			}
		} catch (Exception e) {
			throw new Exception("Data not found in excel");
		}
		return new AuthenticationToken(sh.getRow(row).getCell(1).toString(), sh.getRow(row).getCell(2).toString(),
				sh.getRow(row).getCell(3).toString());

	}

	// public Element XMlData() throws SAXException, IOException,
	// ParserConfigurationException {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dbulider = dbFactory.newDocumentBuilder();
		Document doc = dbulider.parse(new File(pageProperties.getProperty("xmlData")));

		NodeList nList = doc.getElementsByTagName("staff");
		System.err.println(nList.getLength());
		Node nnode = nList.item(0);
		nnode.getAttributes();
		System.err.println("hello"+nnode.getChildNodes().item(0).getNodeValue());

		Element element = (Element) nnode;

		//return element;

	}

}
