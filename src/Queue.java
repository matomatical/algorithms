package com.matomatical.ads;

public class Queue<Data> {
	
	private LinkedList<Data> list = new LinkedList<Data>();
	
	/** Add an item to the end of the queue */
	public void enqueue(Data data) {
		list.addEnd(data);
	}
	
	/** Remove the frontmost item from the queue
	 * @throws EmptyException if the queue is empty
	 */
	public Data dequeue() {
		return list.remove();
	}
	
	/** The number of items in the queue */
	public int size() {
		return list.length();
	}
	
	/** rue iff the queue has no items in it */
	public boolean isEmpty() {
		return list.isEmpty();
	}
}