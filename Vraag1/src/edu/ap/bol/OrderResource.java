package edu.ap.bol;

import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import edu.ap.xml.XMLParser;

public class OrderResource extends ServerResource{
	@Post("txt")
	public String addPatient(String order){
		XMLParser parser = new XMLParser();
		return parser.addOrder(order);
	}
	
	@Get("html")
	public String getPatients(){
		XMLParser parser = new XMLParser();
		return parser.getOrders();
	}
}
