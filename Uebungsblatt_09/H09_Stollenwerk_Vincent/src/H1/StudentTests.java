package H1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SuppressWarnings("Duplicates")
class StudentTests {

    @Test
    void moveToFrontSearchTest() {
        SelfOrganizingLinkedList<Integer> linkedList = new SelfOrganizingLinkedList<>(ReorganizingAlgorithm.MOVETOFRONT);

        intListInitialization(linkedList);

        assertEquals(7, linkedList.search(integer -> integer == 7));

        assertEquals("7 0 1 2 3 4 5 6 8 9 ", linkedList.toString());
    }

    @Test
    void transposeSearchTest() {
        SelfOrganizingLinkedList<Integer> linkedList = new SelfOrganizingLinkedList<>(ReorganizingAlgorithm.TRANSPOSE);

        intListInitialization(linkedList);

        assertEquals(1, linkedList.search(integer -> integer == 1));

        assertEquals("1 0 2 3 4 5 6 7 8 9 ", linkedList.toString());

        assertEquals(7, linkedList.search(integer -> integer == 7));

        assertEquals("1 0 2 3 4 5 7 6 8 9 ", linkedList.toString());
    }

    @Test
    void countSearchTest() {
        SelfOrganizingLinkedList<Integer> linkedList = new SelfOrganizingLinkedList<>(ReorganizingAlgorithm.COUNT);

        intListInitialization(linkedList);

        assertEquals(1, linkedList.search(integer -> integer == 1));
        assertEquals(1, linkedList.head.counter);
        assertEquals("1 0 2 3 4 5 6 7 8 9 ", linkedList.toString());

        assertEquals(7, linkedList.search(integer -> integer == 7));
        assertEquals("1 7 0 2 3 4 5 6 8 9 ", linkedList.toString());

        assertEquals(7, linkedList.search(integer -> integer == 7));
        assertEquals("7 1 0 2 3 4 5 6 8 9 ", linkedList.toString());
    }

    private void intListInitialization(SelfOrganizingLinkedList<Integer> linkedList) {
        assertEquals("", linkedList.toString());

        assertNull(linkedList.search(integer -> integer == 5));

        for (int i = 0; i < 10; i++) {
            linkedList.addLast(i);
        }

        assertEquals("0 1 2 3 4 5 6 7 8 9 ", linkedList.toString());
    }
}
