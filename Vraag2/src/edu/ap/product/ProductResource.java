package edu.ap.product;

import java.util.ArrayList;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import edu.ap.json.TXTParser;

public class ProductResource extends ServerResource{

	@Get("html")
	public ArrayList<String> getQuote(String filter){
		TXTParser parser = new TXTParser();
		ArrayList<String> result = new ArrayList<String>();
		
		try{
			for(String product : parser.getProduct(filter)){
				result.add(product);
			}
			
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		
		return result;
	}
}
