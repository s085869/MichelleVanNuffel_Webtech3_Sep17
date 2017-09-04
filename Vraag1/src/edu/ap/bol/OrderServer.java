package edu.ap.bol;

import org.restlet.Component;
import org.restlet.data.Protocol;

public class OrderServer {

	public static void main(String[] args) {
		try{
			Component component = new Component();
			component.getServers().add(Protocol.HTTP, 8181);
			
			component.getDefaultHost().attach("/ordersystem", new OrderApplication());
			component.start();
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}
}
