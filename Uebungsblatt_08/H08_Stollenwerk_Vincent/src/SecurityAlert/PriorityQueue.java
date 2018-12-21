package SecurityAlert;

import java.util.LinkedList;

public class PriorityQueue<T extends Comparable<? super T>> {
	
	private LinkedList<T> queue = new LinkedList<>();
	
	/**
	 * Inserts e into the queue, based of it's priority
	 * if multiple elements have the same priority
	 * e is inserted as last of it's priority class
	 * @param e Element to insert
	 */
	void enqueue(T e) {
		
		if (queue.contains(e)) {
			return;
		}
		
		for (int i = 0; i < queue.size(); i++) {
			
			if (e.compareTo(queue.get(i)) < 0) {
				queue.add(i, e);
				return;
			}
		}
		
		queue.add(e);
	}
	
	/**
	 * Returns and removes the element of highest priority
	 * @return Queue element of highest priority
	 */
	public T dequeue() {
		return queue.pollFirst();
	}
	
	/**
	 * @return number of queue elements
	 */
	int getSize() {
		return queue.size();
	}
	
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		
		for (int i = 0; i < queue.size(); i++) {
			stringBuilder.append('#').append(i).append(": ");
			
			stringBuilder.append(queue.get(i).toString());
			
			stringBuilder.append("\n");
		}
		
		return stringBuilder.toString();
	}
	
	/**
	 * Moves e to the head of it's priority class
	 * if the queue doesn't contains e, e is inserted at that position
	 * @param e Element to be moved or inserted
	 */
	void addToHeadOfPriorityClass(T e) {
		queue.remove(e);
		
		for (int i = 0; i < queue.size(); i++) {
			
			if (e.compareTo(queue.get(i)) <= 0) {
				queue.add(i, e);
				return;
			}
		}
		
		queue.add(e);
	}

}
