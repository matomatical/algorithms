package com.matomatical.ads;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Stack<Item> implements Iterable<Item> {
	
	private LinkedList<Item> list = new LinkedList<Item>();

	/** Add an item to the top of the stack */
	public void push(Item item) {
		list.add(item);
	}
	
	/** Remove the topmost item from the stack
	 * @throws EmptyException if the stack is empty
	 */
	public Item pop() throws EmptyException {
		return list.remove();
	}
	
	/** The number of items in the stack */
	public int size() {
		return list.length();
	}
	
	/** rue iff the stack has no items in it */
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public Iterator<Item> iterator(){
		return list.iterator();
	}
}