package H1;

import org.junit.jupiter.api.Test;

import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class StudentTests {

    @Test
    void moveToFrontSearchTest() {
        SelfOrganizingLinkedList<Integer> linkedList = new SelfOrganizingLinkedList<>(ReorganizingAlgorithm.MOVETOFRONT);

        intListInitialization(linkedList);

        assertEquals(7, linkedList.search(integer -> integer == 7));

        assertEquals("7 0 1 2 3 4 5 6 8 9 ", linkedList.toString(), "Test 3");
    }

    @Test
    void transposeSearchTest() {
        SelfOrganizingLinkedList<Integer> linkedList = new SelfOrganizingLinkedList<>(ReorganizingAlgorithm.TRANSPOSE);

        intListInitialization(linkedList);

        assertEquals(7, linkedList.search(integer -> integer == 7));

        assertEquals("0 1 2 3 4 5 7 6 8 9 ", linkedList.toString(), "Test 3");
    }

    private void intListInitialization(SelfOrganizingLinkedList<Integer> linkedList) {
        assertEquals("", linkedList.toString());

        for (int i = 0; i < 10; i++) {
            linkedList.addLast(i);
        }

        assertEquals("0 1 2 3 4 5 6 7 8 9 ", linkedList.toString());
    }
}
