package SecurityAlert;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.LinkedList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EmergencyTest {
	
	private EmergencyQueue<GovernmentEmployee> emergency;
	private Field internalQueueField;
	private Field internalListField;
	
	@BeforeEach
	void init() throws NoSuchFieldException {
		emergency = new EmergencyQueue<GovernmentEmployee>();
		
		internalQueueField = EmergencyQueue.class.getDeclaredField("queue");
		internalQueueField.setAccessible(true);
		
		internalListField = PriorityQueue.class.getDeclaredField("queue");
		internalListField.setAccessible(true);
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
	
	@Test
	@SuppressWarnings("unchecked")
	public void priorityQueueDequeueTest() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
		PriorityQueue<GovernmentEmployee> testQueue = new PriorityQueue<>();
		
		testQueue.enqueue(nielsen);
		testQueue.enqueue(devos);
		testQueue.enqueue(trump);
		
		assertEquals(trump, testQueue.dequeue());
		
		LinkedList<GovernmentEmployee> internalList = (LinkedList<GovernmentEmployee>) internalListField.get(testQueue);
		
		LinkedList<GovernmentEmployee> resultingList = new LinkedList<>();
		
		resultingList.add(nielsen);
		resultingList.add(devos);
		
		assertEquals(resultingList, internalList);
		
		
		testQueue = new PriorityQueue<>();
		
		testQueue.enqueue(hatch);
		testQueue.enqueue(ryan);
		testQueue.enqueue(devos);
		
		assertEquals(devos, testQueue.dequeue());
		
		internalList = (LinkedList<GovernmentEmployee>) internalListField.get(testQueue);
		
		resultingList = new LinkedList<>();
		
		resultingList.add(hatch);
		resultingList.add(ryan);
		
		assertEquals(resultingList, internalList);
	}
	
	@Test
	public void priorityQueueGetSizeTest() {
		PriorityQueue<GovernmentEmployee> testQueue = new PriorityQueue<>();
		
		assertEquals(0, testQueue.getSize());
		
		testQueue.enqueue(devos);
		testQueue.enqueue(nielsen);
		testQueue.enqueue(pompeo);
		
		assertEquals(3, testQueue.getSize());
		
		testQueue.dequeue();
		
		assertEquals(2, testQueue.getSize());
	}
	
	@Test
	public void priorityQueueToStringTest() {
		PriorityQueue<GovernmentEmployee> testQueue = new PriorityQueue<>();
		
		assertEquals("", testQueue.toString());
		
		testQueue.enqueue(hatch);
		testQueue.enqueue(ryan);
		testQueue.enqueue(trump);
		testQueue.enqueue(nielsen);
		
		StringBuilder resultingStringBuilder = new StringBuilder();
		
		resultingStringBuilder.append("#0: ").append(trump.toString()).append("\n");
		resultingStringBuilder.append("#1: ").append(nielsen.toString()).append("\n");
		resultingStringBuilder.append("#2: ").append(hatch.toString()).append("\n");
		resultingStringBuilder.append("#3: ").append(ryan.toString()).append("\n");
		
		assertEquals(resultingStringBuilder.toString(), testQueue.toString());
		
		testQueue.dequeue();
		testQueue.addToHeadOfPriorityClass(ryan);
		
		resultingStringBuilder = new StringBuilder();
		
		resultingStringBuilder.append("#0: ").append(nielsen.toString()).append("\n");
		resultingStringBuilder.append("#1: ").append(ryan.toString()).append("\n");
		resultingStringBuilder.append("#2: ").append(hatch.toString()).append("\n");
		
		assertEquals(resultingStringBuilder.toString(), testQueue.toString());
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void priorityQueueAddToHeadOfPriorityClassTest() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
		PriorityQueue<GovernmentEmployee> testQueue = new PriorityQueue<>();
		
		testQueue.enqueue(nielsen);
		testQueue.enqueue(pompeo);
		testQueue.enqueue(trump);
		testQueue.enqueue(chao);
		testQueue.addToHeadOfPriorityClass(pompeo);
		
		LinkedList<GovernmentEmployee> internalList = (LinkedList<GovernmentEmployee>) internalListField.get(testQueue);
		
		LinkedList<GovernmentEmployee> resultingList = new LinkedList<>();
		
		resultingList.add(trump);
		resultingList.add(pompeo);
		resultingList.add(nielsen);
		resultingList.add(chao);
		
		assertEquals(resultingList, internalList);
		
		
		testQueue = new PriorityQueue<>();
		
		testQueue.addToHeadOfPriorityClass(nielsen);
		testQueue.addToHeadOfPriorityClass(devos);
		testQueue.addToHeadOfPriorityClass(sessions);
		testQueue.addToHeadOfPriorityClass(trump);
		testQueue.addToHeadOfPriorityClass(ryan);
		
		internalList = (LinkedList<GovernmentEmployee>) internalListField.get(testQueue);
		
		resultingList = new LinkedList<>();
		
		resultingList.add(trump);
		resultingList.add(devos);
		resultingList.add(nielsen);
		resultingList.add(ryan);
		resultingList.add(sessions);
		
		assertEquals(resultingList, internalList);
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void emergencyQueueRescueTest() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
		
		//First Test
		emergency.enqueue(chao);
		emergency.enqueue(devos);
		emergency.enqueue(sessions);
		emergency.enqueue(nielsen);
		emergency.enqueue(trump);
		
		ByteArrayOutputStream baOutputStream = new ByteArrayOutputStream();
		PrintStream stringStream = new PrintStream(baOutputStream);
		PrintStream sysOut = System.out;
		System.setOut(stringStream);
		        
		emergency.rescue(3);
		        
		System.out.flush();
		System.setOut(sysOut);
		
		StringBuilder resultingMessage = new StringBuilder();
		resultingMessage.append(trump.getTitle()).append(" was rescued\n");
		resultingMessage.append(chao.getTitle()).append(" was rescued\n");
		resultingMessage.append(devos.getTitle()).append(" was rescued\n");
		
		assertEquals(resultingMessage.toString(), baOutputStream.toString());
		
		PriorityQueue<GovernmentEmployee> internalQueue = (PriorityQueue<GovernmentEmployee>) internalQueueField.get(emergency);
		
		assertEquals(2, internalQueue.getSize());
		
		// Second Test
		baOutputStream = new ByteArrayOutputStream();
		stringStream = new PrintStream(baOutputStream);
		sysOut = System.out;
		System.setOut(stringStream);
		        
		emergency.rescue(100);
		        
		System.out.flush();
		System.setOut(sysOut);
		
		resultingMessage = new StringBuilder();
		resultingMessage.append(nielsen.getTitle()).append(" was rescued\n");
		resultingMessage.append(sessions.getTitle()).append(" was rescued\n");
		
		assertEquals(resultingMessage.toString(), baOutputStream.toString());
		
		internalQueue = (PriorityQueue<GovernmentEmployee>) internalQueueField.get(emergency);
		
		assertEquals(0, internalQueue.getSize());
	}
	
	@Test
	public void emergencyQueueChooseDesignatedSurvivorTest() {
		
		// First Test
		emergency.enqueue(devos);
		emergency.enqueue(chao);
		emergency.enqueue(nielsen);
		emergency.chooseDesignatedSurvivor(nielsen);
		
		ByteArrayOutputStream baOutputStream = new ByteArrayOutputStream();
		PrintStream stringStream = new PrintStream(baOutputStream);
		PrintStream sysOut = System.out;
		System.setOut(stringStream);
		        
		emergency.rescue(3);
		        
		System.out.flush();
		System.setOut(sysOut);
		
		StringBuilder resultingMessage = new StringBuilder();
		resultingMessage.append(nielsen.getTitle()).append(" was rescued\n");
		resultingMessage.append(devos.getTitle()).append(" was rescued\n");
		resultingMessage.append(chao.getTitle()).append(" was rescued\n");
		
		assertEquals(resultingMessage.toString(), baOutputStream.toString());
		
		// Second Test
		emergency.chooseDesignatedSurvivor(pompeo);
		emergency.chooseDesignatedSurvivor(devos);
		emergency.chooseDesignatedSurvivor(chao);
		
		baOutputStream = new ByteArrayOutputStream();
		stringStream = new PrintStream(baOutputStream);
		sysOut = System.out;
		System.setOut(stringStream);
		        
		emergency.rescue(3);
		        
		System.out.flush();
		System.setOut(sysOut);
		
		resultingMessage = new StringBuilder();
		resultingMessage.append(chao.getTitle()).append(" was rescued\n");
		resultingMessage.append(devos.getTitle()).append(" was rescued\n");
		resultingMessage.append(pompeo.getTitle()).append(" was rescued\n");
		
		assertEquals(resultingMessage.toString(), baOutputStream.toString());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void emergencyQueueEnqueueTest() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
		
		// First Test
		emergency.enqueue(chao);
		emergency.enqueue(devos);
		emergency.enqueue(hatch);
		emergency.enqueue(nielsen);
		
		PriorityQueue<GovernmentEmployee> internalQueue = (PriorityQueue<GovernmentEmployee>) internalQueueField.get(emergency);
		
		LinkedList<GovernmentEmployee> internalList = (LinkedList<GovernmentEmployee>) internalListField.get(internalQueue);
		
		PriorityQueue<GovernmentEmployee> resultingQueue = new PriorityQueue<>();
		
		resultingQueue.enqueue(chao);
		resultingQueue.enqueue(devos);
		resultingQueue.enqueue(hatch);
		resultingQueue.enqueue(nielsen);
		
		LinkedList<GovernmentEmployee> resultingList = (LinkedList<GovernmentEmployee>) internalListField.get(resultingQueue);
		
		assertEquals(resultingList, internalList);
		
		// Second Test
		emergency = new EmergencyQueue<>();
		
		emergency.enqueue(trump);
		emergency.enqueue(sessions);
		emergency.enqueue(ryan);
		emergency.enqueue(pompeo);
		
		internalQueue = (PriorityQueue<GovernmentEmployee>) internalQueueField.get(emergency);
		
		internalList = (LinkedList<GovernmentEmployee>) internalListField.get(internalQueue);
		
		resultingQueue = new PriorityQueue<>();
		
		resultingQueue.enqueue(trump);
		resultingQueue.enqueue(sessions);
		resultingQueue.enqueue(ryan);
		resultingQueue.enqueue(pompeo);
		
		resultingList = (LinkedList<GovernmentEmployee>) internalListField.get(resultingQueue);
		
		assertEquals(resultingList, internalList);
	}
	
	@Test
	public void EmergencyQueueToStringTest() {
		
		// First Test
		emergency.enqueue(chao);
		emergency.enqueue(nielsen);
		emergency.enqueue(pompeo);
		
		PriorityQueue<GovernmentEmployee> expectedQueue = new PriorityQueue<>();
		
		expectedQueue.enqueue(chao);
		expectedQueue.enqueue(nielsen);
		expectedQueue.enqueue(pompeo);
		
		assertEquals(expectedQueue.toString(), emergency.toString());
		
		
		// Second Test
		emergency.enqueue(trump);
		emergency.enqueue(hatch);
		emergency.rescue(3);
		emergency.enqueue(trump);
		
		expectedQueue.enqueue(trump);
		expectedQueue.enqueue(hatch);
		for (int i = 0 ; i < 3; i ++) {
			expectedQueue.dequeue();
		}
		expectedQueue.enqueue(trump);
		
		assertEquals(expectedQueue.toString(), emergency.toString());
	}

}
