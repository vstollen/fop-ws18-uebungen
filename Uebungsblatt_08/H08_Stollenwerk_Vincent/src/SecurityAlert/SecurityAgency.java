package SecurityAlert;

import java.util.ArrayList;

public class SecurityAgency {

	private ArrayList<GovernmentEmployee> emps = new ArrayList<>();
	private EmergencyQueue<GovernmentEmployee> emergency = new EmergencyQueue<>();
	private AlertLevel al = AlertLevel.LOW;

	/**
	 * Adds e to internal employee list
	 * @param e Employee
	 */
	void add(GovernmentEmployee e) {
		emps.add(e);
	}
	
	/**
	 * Submit a change to the AlertLevel
	 * @param newAL new Alert Level
	 */
	public void changeAlertLevel(AlertLevel newAL) {
		al = newAL;
		
		if (al.ordinal() <= AlertLevel.ELEVATED.ordinal()) {
			
			for (GovernmentEmployee employee : emps) {
				if (employee instanceof President) {
					emergency.enqueue(employee);
				}
			}
			
		}
		
		if (al.ordinal() <= AlertLevel.HIGH.ordinal()) {
			
			for (GovernmentEmployee employee : emps) {
				if (employee instanceof Secretary) {
					emergency.enqueue(employee);
				}
			}
		}
		
		if (al.ordinal() <= AlertLevel.SEVERE.ordinal()) {
			
			for (GovernmentEmployee employee : emps) {
				emergency.enqueue(employee);
			}
		}
	}

}
