package SecurityAlert;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EmergencyTest {
	
	private EmergencyQueue<GovernmentEmployee> emergency;
	
	@BeforeEach
	void init() {
		emergency = new EmergencyQueue<GovernmentEmployee>();
	}
	
	// TEST DATA
	
	// President
	President trump = new President("Donald Trump", Sex.MALE);
	
	// Secretaries
	Secretary nielsen = new Secretary("Kirstjen Nielsen", Sex.FEMALE); // Homeland Security
	Secretary pompeo = new Secretary("Mike Pompeo", Sex.MALE); // State
	Secretary devos = new Secretary("Betsy DeVos", Sex.FEMALE); // Education
	Secretary chao = new Secretary("Elaine Chao", Sex.FEMALE); // Transportation
	
	// Others
	Other ryan = new Other("Paul Ryan", Sex.MALE); // Speaker of the House of Representatives
	Other sessions = new Other("Hillary Clinton", Sex.FEMALE);
	Other hatch = new Other("Orin Hatch", Sex.MALE); // President pro tempore of the United States Senate
	

}
