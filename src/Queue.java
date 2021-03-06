package com.matomatical.ads;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Queue<Item> implements Iterable<Item> {
	
	private LinkedList<Item> list = new LinkedList<Item>();
	
	/** Add an item to the end of the queue */
	public void enqueue(Item item) {
		list.addEnd(item);
	}
	
	/** Remove the frontmost item from the queue
	 * @throws EmptyException if the queue is empty
	 */
	public Item dequeue() {
		return list.remove();
	}
	
	/** The number of items in the queue */
	public int size() {
		return list.length();
	}
	
	/** true iff the queue has no items in it */
	public boolean isEmpty() {
		return list.isEmpty();
	}
	
	@Override
	public Iterator<Item> iterator(){
		return list.iterator();
	}
}