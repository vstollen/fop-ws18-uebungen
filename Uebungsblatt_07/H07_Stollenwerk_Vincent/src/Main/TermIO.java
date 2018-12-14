package Main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import Exceptions.InvalidTermException;

public class TermIO {
	
	public Term[] terms;
	
	/**
	 * Reads in file from filePath and stores data into terms
	 * @param filePath
	 * @return only true if reading in terms from filePath was successful
	 */
	public boolean readTermsFromFile(String filePath) {
		
		try {
			String[] input = Utils.readAllLinesFromFile(filePath);
			
			terms = new Term[input.length];
			
			for (int i = 0; i < terms.length; i++) {
				try {
					terms[i] = new Term(input[i]);
				} catch (InvalidTermException e) {
					System.out.println("Invalid term: " + input[i]);
					return false;
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + filePath);
			return false;
		} catch (UnsupportedEncodingException e) {
			System.out.println("Unsupported encoding: " + e.getMessage());
			return false;
		}
		
		return true;
	}
	
	/**
	 * @param filePath
	 * @return results of terms written into filePath
	 */
	public boolean writeTermResultsToFile(String filePath) {
		
		String[] output = new String[terms.length];
		
		for (int i = 0; i < terms.length; i++) {
			output[i] = terms[i].getResult();
		}
		try {
			Utils.writeLinesToFile(filePath, output);
		} catch (IOException e) {
			System.out.println("Error writing to file: " + e.getMessage());
			return false;
		}
		
		return true;
	}
}
