package Main;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Test;

public class ScalingMatrixTest {

	@Test
    public void assureSizeTest() {
		
		ScalingMatrix<Integer> testMatrix = new ScalingMatrix<Integer>(42);
		
    	assertThrows(ArrayIndexOutOfBoundsException.class, () -> testMatrix.get(0, 0));
    	
    	assertEquals(0, testMatrix.size());
    	
    	testMatrix.assureSize(5);
    	
    	assertEquals(5, testMatrix.size());
    	
    	for (int row = 0; row < 5; row++) {
    		for (int column = 0; column < 5; column++) {
    			assertEquals(42, (int) testMatrix.get(row, column));
    		}
    	}
    	
    	assertThrows(ArrayIndexOutOfBoundsException.class, () -> testMatrix.get(6, 1));
    	assertThrows(ArrayIndexOutOfBoundsException.class, () -> testMatrix.get(1, 6));
    }
	
	@Test
	public void setTest() {
		
		ScalingMatrix<Integer> testMatrix = new ScalingMatrix<Integer>(42);
		
		testMatrix.assureSize(5);
		
		testMatrix.set(3, 2, 100);
		
		assertEquals(100, (int) testMatrix.get(3, 2));
		
		testMatrix.assureSize(10);
		
		assertEquals(100, (int) testMatrix.get(3, 2));
		
		assertEquals(42, (int) testMatrix.get(6, 7));
	}
	
	@Test
	public void initialSizeConstructorTest() {
		
		ScalingMatrix<Boolean> testMatrix = new ScalingMatrix<>(false, 5);
		
		assertEquals(5, testMatrix.size());
		
		assertEquals(false, testMatrix.get(2, 3));
	}
}
