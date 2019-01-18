package H1;

import java.util.List;
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
		}
		return null;
	}

	/**
	 * Searches the list and moves the found list item to the front
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
