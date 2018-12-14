package Tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;

import java.time.Duration;

import org.junit.jupiter.api.Test;

import Exceptions.InvalidTermException;
import Main.Term;

public class TermTests {

	@Test
	public void simpleNumberTest() {
		assertTimeout(Duration.ofSeconds(10), () ->  new Term("-120"));
	}
	
	@Test
	public void wrongBraceTest() {
		InvalidTermException exception = assertThrows(InvalidTermException.class, () -> new Term("3*(2 + 7))"));
		
		assertEquals(exception.getMessage(), "Wrong number of closing braces");
	}
	
	@Test
	public void multipleOperatorTest() {
		InvalidTermException exception = assertThrows(InvalidTermException.class, () -> new Term("3**2"));
		
		assertEquals(exception.getMessage(), "Incorrect operator usage");
	}
	
	@Test
	public void leadingOperatorTest() {
		InvalidTermException exception = assertThrows(InvalidTermException.class, () -> new Term("+42"));
		
		assertEquals(exception.getMessage(), "Incorrect operator usage");
	}
	
	@Test
	public void evaluateTermTest() {
		try {
			Term term = new Term("54 + (545 * 234 + (4545 - 54)) * 6 + 34");
			
			assertEquals("792214", term.getResult());
		} catch (InvalidTermException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
