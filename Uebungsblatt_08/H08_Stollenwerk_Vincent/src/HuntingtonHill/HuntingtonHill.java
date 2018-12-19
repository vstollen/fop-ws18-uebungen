package HuntingtonHill;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.regex.Pattern;

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
				String[] lineInfo = line.split(Pattern.quote(";"));
				states.put(lineInfo[0], new State(Integer.parseInt(lineInfo[1])));
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
		
		int remainingSeats = H;
		
		if (remainingSeats < states.size()) {
			throw new MoreStatesThanSeatsException();
		}
		
		// One initial Seat
		for (State s : states.values()) {
			s.addSeat();
			remainingSeats--;
		}
		
		PriorityQueue<State> priorityQueue = new PriorityQueue<>(new Comparator<State>() {

			@Override
			public int compare(State state1, State state2) {
				
				if (state1.priority() > state2.priority()) {
					return -1;
				}
				
				if (state1.priority() < state2.priority()) {
					return 1;
				}
				
				return 0;
			}
		});
		
		priorityQueue.addAll(states.values());
		
		while (remainingSeats > 0) {
			State prioState = priorityQueue.poll();
			
			prioState.addSeat();
			remainingSeats--;
			
			priorityQueue.add(prioState);
		}
		
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
