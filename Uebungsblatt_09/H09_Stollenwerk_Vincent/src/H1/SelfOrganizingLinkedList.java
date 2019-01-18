package H1;

import java.util.function.Predicate;

public class SelfOrganizingLinkedList<T> extends SimpleLinkedList<T> {

	private ReorganizingAlgorithm ra;

	public SelfOrganizingLinkedList(ReorganizingAlgorithm ra) {
		this.ra = ra;
	}

	@Override
	public T search(Predicate<T> predicate) {

		switch (ra) {
			case MOVETOFRONT:
				return moveToFrontSearch(predicate);
			case TRANSPOSE:
				return transposeSearch(predicate);
		}
		return null;
	}

	/**
	 * Searches the list and moves the found list item to head
	 * @param predicate
	 * @return First list item matching the predicate
	 */
	private T moveToFrontSearch(Predicate<T> predicate) {

		ListItem<T> previousItem = null;
		ListItem<T> pointer = head;

		while (pointer != null) {
			if (predicate.test(pointer.key)) {
				if (previousItem != null) {
					previousItem.next = pointer.next;
					pointer.next = head;
					head = pointer;
				}
				return pointer.key;
			}

			previousItem = pointer;
			pointer = pointer.next;
		}

		return null;
	}

	/**
	 * Searches the list and moves the found list item one position to the front
	 * @param predicate
	 * @return First list item matching the predicate
	 */
	private T transposeSearch(Predicate<T> predicate) {

		if (head == null) {
			return null;
		}

		ListItem<T> secondPreviousItem = null;
		ListItem<T> pointer = head.next;

		if (predicate.test(head.key)) {
			return head.key;
		}

		while (pointer != null) {
			if (predicate.test(pointer.key)) {
				if (secondPreviousItem != null) {

					ListItem<T> previousItem = secondPreviousItem.next;
					previousItem.next = pointer.next;

					pointer.next = previousItem;

					secondPreviousItem.next = pointer;
				} else {

					head.next = pointer.next;

					pointer.next = head;

					head = pointer;
				}

				return pointer.key;
			}

			secondPreviousItem = secondPreviousItem == null ? head : secondPreviousItem.next;
			pointer = pointer.next;
		}


		return null;

	}

	@Override
	public String toString() {

		ListItem<T> pointer = head;

		StringBuilder sb = new StringBuilder();

		while (pointer != null) {
			sb.append(pointer.key.toString()).append(" ");

			pointer = pointer.next;
		}

		return sb.toString();
	}
}
