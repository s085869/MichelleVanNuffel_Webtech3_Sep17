package edu.ap.xml;

import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class XMLParser {
	private String INPUTFILE = "C:/MichelleVanNuffel_Webtech3_Sep17/Vraag1/bestellingen.xml";
	
	/** Get all orders from the xml file and return them 
	 * in html format
	 */
	public String getOrders() {
		File inputFile = new File(INPUTFILE);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
	        doc.getDocumentElement().normalize();
	        String result = "<h2>Orders</h2>";
	        
	        NodeList orders = doc.getElementsByTagName("order");

	        for (int i = 0; i < orders.getLength(); i++) {
	        	 Node nNode = orders.item(i);
	        	 Element eElement = (Element) nNode;
	        	 
	        	 result += "<br/><b>Client name : </b>" + eElement.getAttribute("client");
	        	 result += "<br/><b>Address : </b>" + eElement.getAttribute("address");
	        	 result += "<br/><b>Order date : </b>" + eElement.getAttribute("date");
	        	 result += "<br/><b>Product name : </b>" + eElement.getAttribute("product");
	        	 result += "<br/><b>Quantity : </b>" + eElement.getAttribute("quantity");
	        	 result += "<br/>";
	        }

	        return result;
		} 
		catch (Exception e) {
			return e.getMessage();
		}
	}
	
	/**
	 * Add an order and return all orders in
	 * html format
	 */
	public String addOrder(String xml) {
		File inputFile = new File(INPUTFILE);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        // set validating false to enable copying
        // node from one document to another
        dbFactory.setValidating(false);
        DocumentBuilder dBuilder;
        
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc1 = dBuilder.parse(inputFile);
	        Document doc2 = dBuilder.parse(new InputSource(new StringReader(xml)));
	        Element element = (Element) doc2.getDocumentElement();
	        // imports a node from doc2 document to doc1, without altering
	        // or removing the source node from the original document
	        Node copiedNode = doc1.importNode(element, true);
	        // adds the node to the end of the list of children of this node
	        doc1.getDocumentElement().appendChild(copiedNode);
	        
	        /*FileWriter fw = new FileWriter(INPUTFILE);
	        fw.write(doc1.toString());
	        fw.close();*/
	        // write the new document to file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc1);
			StreamResult result = new StreamResult(new File(INPUTFILE));
			transformer.transform(source, result);
	        
	        return this.getOrders();
		} 
		catch (Exception e) {
			return e.getMessage();
		}
	}

	/**
	 * Utility method to print xml document
	 */
	public String prettyPrint(Document xml) throws Exception {
		Transformer tf = TransformerFactory.newInstance().newTransformer();
		tf.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		tf.setOutputProperty(OutputKeys.INDENT, "yes");
		Writer out = new StringWriter();
		tf.transform(new DOMSource(xml), new StreamResult(out));
		return out.toString();
	}
}
