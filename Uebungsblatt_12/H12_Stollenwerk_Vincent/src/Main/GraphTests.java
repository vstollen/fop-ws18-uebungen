package Main;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

public class GraphTests {
	
	@Test
	public void addNodeTest() {
		Graph<String, Integer> testGraph = new Graph<>(-1);
		
		assertEquals(0, testGraph.getNumberOfNodes());
		
		testGraph.addNode("A");
		
		assertEquals(1, testGraph.getNumberOfNodes());
	}
	
	@Test
	public void addNodeMatrixScaleTest() {
		Graph<String, Integer> testGraph = new Graph<>(-1);
		
		testGraph.addNode("A");
		testGraph.setWeight("default", 0, 0, 1);
		
		assertEquals(1, (int) testGraph.getWeight("default", 0, 0));
		
		assertThrows(ArrayIndexOutOfBoundsException.class, () -> testGraph.getWeight("default", 1, 1));
		
		testGraph.addNode("B");
		
		assertEquals(-1, (int) testGraph.getWeight("default", 1, 1));
	}
	
	@Test
	public void addNodeTest2() {
		Graph<Boolean, Double> testGraph = new Graph<>(0.5);
		
		testGraph.addNode(false);
		testGraph.addNode(false);
		testGraph.addNode(false);
		
		assertEquals(3, (int) testGraph.getNumberOfNodes());
	}
	
	@Test
	public void setWeightTest() {
		Graph<String, Integer> testGraph = new Graph<>(-1);
		
		assertThrows(Exception.class, () -> testGraph.setWeight("test", 4, 5, 3));
		
		testGraph.addNode("A");
		
		testGraph.setWeight("test", 0, 0, 5);
		
		assertEquals(5, (int) testGraph.getWeight("test", 0, 0));
	}
	
	@Test
	public void setWeightTest2() {
		Graph<String, Integer> testGraph = new Graph<>(-1);
		
		testGraph.addNode("A");
		testGraph.addNode("B");
		
		testGraph.setWeight("time", 1, 1, 5);
		testGraph.setWeight("distance", 1, 1, 2);
		
		assertEquals(5, (int) testGraph.getWeight("time", 1, 1));
		assertEquals(2, (int) testGraph.getWeight("distance", 1, 1));
	}
	
	@Test
	public void setWeightTest3() {
		Graph<String, Integer> testGraph = new Graph<>(-1);
		
		testGraph.addNode("X");
		testGraph.addNode("Y");
		
		testGraph.setWeight("default", 0, 0, 1);
		testGraph.setWeight("default", 0, 1, 2);
		testGraph.setWeight("default", 1, 0, 3);
		testGraph.setWeight("default", 1, 1, 4);
		
		assertEquals(1, (int) testGraph.getWeight("default", 0, 0));
		assertEquals(2, (int) testGraph.getWeight("default", 0, 1));
		assertEquals(3, (int) testGraph.getWeight("default", 1, 0));
		assertEquals(4, (int) testGraph.getWeight("default", 1, 1));
	}

	@Test
	public void getAllPathsTest() {
		Graph<String, Integer> testGraph = new Graph<>(-1);
		
		testGraph.addNode("A");
		testGraph.addNode("B");
		testGraph.addNode("C");
		testGraph.addNode("A");
		
		testGraph.setWeight("default", 0, 1, 3);
		testGraph.setWeight("default", 1, 0, 2);
		testGraph.setWeight("default", 1, 2, 4);
		testGraph.setWeight("default", 2, 3, 1);
		testGraph.setWeight("default", 3, 2, 2);
		
		String[] allPathsSolution = {"B -> A", "B -> C -> A"};
		String[] allPathsIndizesSolution = {"1 -> 0", "1 -> 2 -> 3"};
		
		String[] allPaths = testGraph.getAllPaths("default", 1, false);
		String[] allPathsIndizes = testGraph.getAllPaths("default", 1, true);
		
		System.out.println(Arrays.toString(allPaths));
		System.out.println(Arrays.toString(allPathsIndizes));
		
		assertArrayEquals(allPathsSolution, allPaths);
		assertArrayEquals(allPathsIndizesSolution, allPathsIndizes);
	}
	
	@Test
	public void getAllPathsTest2() {
		Graph<String, Integer> testGraph = new Graph<>(-1);
		
		testGraph.addNode("A");
		testGraph.addNode("B");
		testGraph.addNode("C");
		testGraph.addNode("D");
		
		testGraph.setWeight("default", 0, 1, 3);
		testGraph.setWeight("default", 1, 0, 2);
		testGraph.setWeight("default", 1, 2, 4);
		testGraph.setWeight("default", 2, 3, 1);
		testGraph.setWeight("default", 3, 2, 2);
		
		String[] allPathsSolution = {"C -> D"};
		String[] allPathsIndizesSolution = {"2 -> 3"};
		
		String[] allPaths = testGraph.getAllPaths("default", 2, false);
		String[] allPathsIndizes = testGraph.getAllPaths("default", 2, true);
		
		System.out.println(Arrays.toString(allPaths));
		System.out.println(Arrays.toString(allPathsIndizes));
		
		assertArrayEquals(allPathsSolution, allPaths);
		assertArrayEquals(allPathsIndizesSolution, allPathsIndizes);
	}
	
	@Test
	public void getAllPathsTest3() {
		Graph<Integer, Boolean> testGraph = new Graph<>(false);
		
		for (int i = 1; i < 7; i++) {
			testGraph.addNode(i);
		}
		
		testGraph.setWeight("default", 0, 1, true);
		testGraph.setWeight("default", 0, 2, true);
		testGraph.setWeight("default", 2, 0, true);
		testGraph.setWeight("default", 2, 3, true);
		testGraph.setWeight("default", 2, 5, true);
		testGraph.setWeight("default", 3, 0, true);
		testGraph.setWeight("default", 4, 2, true);
		testGraph.setWeight("default", 4, 4, true);
		testGraph.setWeight("default", 5, 1, true);
		testGraph.setWeight("default", 5, 3, true);
		testGraph.setWeight("default", 5, 4, true);
		
		String[] allPathsSolution = {"1 -> 2", "1 -> 3 -> 4", "1 -> 3 -> 6 -> 2", "1 -> 3 -> 6 -> 4", "1 -> 3 -> 6 -> 5"};
		String[] allPathsIndizesSolution = {"0 -> 1", "0 -> 2 -> 3", "0 -> 2 -> 5 -> 1", "0 -> 2 -> 5 -> 3", "0 -> 2 -> 5 -> 4"};
		
		String[] allPaths = testGraph.getAllPaths("default", 0, false);
		String[] allPathsIndizes = testGraph.getAllPaths("default", 0, true);
		
		System.out.println(Arrays.toString(allPaths));
		System.out.println(Arrays.toString(allPathsIndizes));
		
		assertArrayEquals(allPathsSolution, allPaths);
		assertArrayEquals(allPathsIndizesSolution, allPathsIndizes);
	}
}
