package H1;

import java.util.function.Predicate;

public class SelfOrganizingLinkedList<T> extends SimpleLinkedList<T> {

	private ReorganizingAlgorithm ra;

	public SelfOrganizingLinkedList(ReorganizingAlgorithm ra) {
		this.ra = ra;
	}
	
	public T search(Predicate<T> predicate) {
		/**
		 * TODO H1.1, H1.2, H1.3
		 */
		return null;
	}

	
}
