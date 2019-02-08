package Main;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

public class GraphTests {

	@Test
	public void getAllPathsTest() {
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
		
		String[] allPathsSolution = {"B -> A", "B -> C -> D"};
		String[] allPathsIndizesSolution = {"1 -> 0", "1 -> 2 -> 3"};
		
		String[] allPaths = testGraph.getAllPaths("default", 1, false);
		String[] allPathsIndizes = testGraph.getAllPaths("default", 1, true);
		
		System.out.println(Arrays.toString(allPaths));
		System.out.println(Arrays.toString(allPathsIndizes));
		
		assertArrayEquals(allPathsSolution, allPaths);
		assertArrayEquals(allPathsIndizesSolution, allPathsIndizes);
	}
}
