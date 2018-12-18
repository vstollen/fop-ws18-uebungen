package HuntingtonHill;

/**
 * class to model an us-american state
 * @author Florian Kadner
 *
 */
public class State {
	
	private int population;
	private int currentSeats;

	public State(int pop) {
		this.population = pop;
		currentSeats = 0;
	}

	public int getPopulation() {
		return this.population;
	}

	public int getCurrentSeats() {
		return this.currentSeats;
	}

	public void addSeat() {
		this.currentSeats++;
	}

	public double priority() {
		
		double priority = population / Math.sqrt(currentSeats*(currentSeats+1));
		
		return priority;
	}

}
