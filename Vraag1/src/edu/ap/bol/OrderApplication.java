package edu.ap.bol;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

public class OrderApplication extends Application{
	public synchronized Restlet createInboundRoot(){
		Router router = new Router(getContext());
		router.attach("/registration", OrderResource.class);
		
		return router;
	}
}
