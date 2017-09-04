package edu.ap.json;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class TXTParser {
private String INPUTFILE = "C:/MichelleVanNuffel_Webtech3_Sep17/Vraag2/products.txt";
	
	/** Get all patients from the txt file and return them 
	 * in array list
	 */
	public ArrayList<String> getProducts() {
		File inputFile = new File(INPUTFILE);
        ArrayList<String> lines = new ArrayList<String>();
		
		try {
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new FileReader(inputFile));
			String line = br.readLine();
			while (line != null)
			{
			    lines.add(line);
			    line = br.readLine();
			}
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return lines;
	}
	
	/** Get all patients from the txt file with a specific word in them
	 * and return them in array list
	 */
	public ArrayList<String> getProduct(String filter) {
		
		ArrayList<String> lines = new ArrayList<String>();
		
		for (String product : getProducts()){
			if (product.contains(filter)) {
				lines.add(product);
			}
		}
		
		return lines;
	}
}
