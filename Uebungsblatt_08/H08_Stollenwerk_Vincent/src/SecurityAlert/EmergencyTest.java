package SecurityAlert;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;
import java.util.LinkedList;

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
	
	@Test
	@SuppressWarnings("unchecked")
	public void priorityQueueEnqueueTest() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
		PriorityQueue<GovernmentEmployee> testQueue = new PriorityQueue<>();
		
		testQueue.enqueue(ryan);
		testQueue.enqueue(trump);
		testQueue.enqueue(nielsen);
		testQueue.enqueue(hatch);
		testQueue.enqueue(chao);
		
		Field internalListField = PriorityQueue.class.getDeclaredField("queue");
		internalListField.setAccessible(true);
		LinkedList<GovernmentEmployee> internalList = (LinkedList<GovernmentEmployee>) internalListField.get(testQueue);
		
		LinkedList<GovernmentEmployee> correctList = new LinkedList<>();
		correctList.add(trump);
		correctList.add(nielsen);
		correctList.add(chao);
		correctList.add(ryan);
		correctList.add(hatch);
		
		assertEquals(correctList, internalList);
		
		
		testQueue = new PriorityQueue<>();
		
		testQueue.enqueue(hatch);
		testQueue.enqueue(nielsen);
		testQueue.enqueue(ryan);
		testQueue.enqueue(hatch);
		testQueue.enqueue(nielsen);
		testQueue.enqueue(trump);
		
		internalList = (LinkedList<GovernmentEmployee>) internalListField.get(testQueue);
		
		correctList = new LinkedList<>();
		correctList.add(trump);
		correctList.add(nielsen);
		correctList.add(hatch);
		correctList.add(ryan);
		
		assertEquals(correctList, internalList);
	}
	

}
