package HuntingtonHill;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

/**
 * class to model the Huntington-Hill-Method
 * @author Florian Kadner
 *
 */
public class HuntingtonHill {

	private HashMap<String, State> states;
	private int H;
	
	public HuntingtonHill(String filename, int numberOfSeats) {

		this.H = numberOfSeats;
		states = new HashMap<String, State>();

		try {
			FileReader fr = new FileReader(filename);
			BufferedReader in = new BufferedReader(fr);

			String line;
			while ((line = in.readLine()) != null) {
				// TODO: line represents one row of the .txt file
				
			}

			in.close();
			fr.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * distribute the seats with the huntington-hill method
	 * @throws MoreStatesThanSeatsException
	 */
	public void distributeSeats() throws MoreStatesThanSeatsException {
		
	}
	
	/**
	 * creates a string containing the existing states and the number of assigned seats
	 * @return string containing the existing states and the number of assigned seats
	 */
	public String printDistribution() {
		
		return "";

	}
	
	public HashMap<String, State> getStates() {
		return this.states;
	}

}
