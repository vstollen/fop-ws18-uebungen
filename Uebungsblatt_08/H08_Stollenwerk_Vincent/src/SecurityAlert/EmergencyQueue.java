package SecurityAlert;

public class EmergencyQueue<T extends GovernmentEmployee> {

	private PriorityQueue<GovernmentEmployee> queue = new PriorityQueue<>();
	
	/**
	 * Rescues first n employees and prints their information into the console
	 * @param n
	 */
	public void rescue(int n) {
		for (int i = 0; i < n && queue.getSize() > 0; i++) {
			
			GovernmentEmployee rescuedEmployee = queue.dequeue();
			
			StringBuilder rescueAlert = new StringBuilder();
			
			rescueAlert.append(rescuedEmployee.getTitle());
			rescueAlert.append(" was rescued");
			
			System.out.println(rescueAlert.toString());
		}
	}
	
	/**
	 * Sets ds to be the minister with highest priority
	 * @param ds Prioritized minister
	 */
	public void chooseDesignatedSurvivor(Secretary ds) {
		queue.addToHeadOfPriorityClass(ds);
	}
	
	/**
	 * Inserts newItem into the queue based of it's priority
	 * @param newItem
	 */
	public void enqueue(GovernmentEmployee newItem) {
		queue.enqueue(newItem);
	}
	
	@Override
	public String toString() {
		return queue.toString();
	}
}
