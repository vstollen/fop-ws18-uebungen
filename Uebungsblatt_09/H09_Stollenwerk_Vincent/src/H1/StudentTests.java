package H1;

import org.junit.jupiter.api.Test;

import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StudentTests {

    @Test
    void moveToFrontSearchTest() {
        SelfOrganizingLinkedList<Integer> linkedList = new SelfOrganizingLinkedList<>(ReorganizingAlgorithm.MOVETOFRONT);

        for (int i = 0; i < 10; i++) {
            linkedList.addLast(i);
        }

        assertEquals("0 1 2 3 4 5 6 7 8 9 ", linkedList.toString());

        assertEquals(7, linkedList.search(integer -> integer == 7));

        assertEquals("7 0 1 2 3 4 5 6 8 9 ", linkedList.toString(), "Test 3");
    }
}
