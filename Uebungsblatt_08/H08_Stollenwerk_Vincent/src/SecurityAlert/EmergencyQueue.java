package SecurityAlert;

public class EmergencyQueue<T extends GovernmentEmployee> {

	private PriorityQueue<GovernmentEmployee> queue;
	
	public void rescue(int n) {
		for (int i = 0; i < n && queue.getSize() > 0; i++) {
			
			GovernmentEmployee rescuedEmployee = queue.dequeue();
			
			StringBuilder rescueAlert = new StringBuilder();
			
			rescueAlert.append(rescuedEmployee.getTitle());
			rescueAlert.append(" was rescued");
			
			System.out.println(rescueAlert.toString());
		}
	}
	
	public void chooseDesignatedSurvivor(Secretary ds) {
		queue.addToHeadOfPriorityClass(ds);
	}
	
	public void enqueue(GovernmentEmployee newItem) {
		queue.enqueue(newItem);
	}
	
	@Override
	public String toString() {
		return queue.toString();
	}
}
