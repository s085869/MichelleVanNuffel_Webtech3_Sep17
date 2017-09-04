package edu.ap.product;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

public class ProductApplication extends Application{
	public synchronized Restlet createInboundRoot(){
		//router.attach --> url zelfde als bij server (omdat we met restlet werken)
		Router router = new Router(getContext());
		router.attach("/products", ProductsResource.class);
		router.attach("/result", ProductResource.class);
		
		return router;
	}
}
