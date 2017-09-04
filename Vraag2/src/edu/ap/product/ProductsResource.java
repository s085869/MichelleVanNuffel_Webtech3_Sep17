package edu.ap.product;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import edu.ap.json.TXTParser;

public class ProductsResource extends ServerResource{
		
	@Get("html")
	public ArrayList<String> getQuotes(){
		TXTParser parser = new TXTParser();
		ArrayList<String> result = new ArrayList<String>();
		
		try{
			for(String quote : parser.getProducts()){
				result.add(quote);
			}
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		
		return result;
	}
}