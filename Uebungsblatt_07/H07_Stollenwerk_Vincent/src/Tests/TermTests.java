package Tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import Exceptions.InvalidTermException;
import Main.Term;

public class TermTests {

	@Test
	public void simpleNumberConstructorTest() {
		
		try {
			new Term("-1.20");
		} catch (InvalidTermException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void simpleExpressionConstructorTest() {
		
		try {
			new Term("43+89-32*23+5");
		} catch (InvalidTermException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void complexExpressionConstructorTest() {
		
		try {
			new Term("83 + 2384 - 1 * (3 + (2 - 5) * (5 * 2)) - 1");
		} catch (InvalidTermException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void emptyTermExceptionTest() {
		
		InvalidTermException e = assertThrows(InvalidTermException.class, () -> new Term(" ( ( ))"));
		
		assertEquals("Term is empty", e.getMessage());
		
		e = assertThrows(InvalidTermException.class, () -> new Term(""));
		
		assertEquals("Term is empty", e.getMessage());
	}

	@Test
	public void braceExceptionTest() {
		
		InvalidTermException e = assertThrows(InvalidTermException.class, () -> new Term("(5/1 + 4.2"));
		
		assertEquals("Invalid brace use", e.getMessage());
	}
	
	@Test
	public void invalidCharacterExceptionTest() {
		
		InvalidTermException e = assertThrows(InvalidTermException.class, () -> new Term("3*x + 1"));
		
		assertEquals("Contains invalid character", e.getMessage());
	}
	
	@Test
	public void wrongOperatorExcepionTest() {
		
		InvalidTermException e = assertThrows(InvalidTermException.class, () -> new Term("3(-2*8)"));
		
		assertEquals("Incorrect operator usage", e.getMessage());
		
		e = assertThrows(InvalidTermException.class, () -> new Term("--5+3"));
		
		assertEquals("Incorrect operator usage", e.getMessage());
	}
	
	@Test
	public void multiplayFirstResultTest() throws InvalidTermException {
		
		Term term = new Term("3 + 5 * 2");
		
		assertEquals("13", term.getResult());
	}
	
	@Test
	public void braceResultTest() throws InvalidTermException {
		
		Term term = new Term(" 3 * (3 - 5 * ( 2 / 6) + (2 - 5))");
		
		assertEquals(-5, Double.parseDouble(term.getResult()), 0.1);
	}
	
	@Test
	public void negativeNumberMinusOperatorResultTest() throws InvalidTermException {
		
		Term term = new Term("-3 + (3/-5)-(70.5 + -1-5)");
		
		assertEquals("-68.1", term.getResult());
	}
}