package com.matomatical.ads;

public class Stack<Data> {
	
	private LinkedList<Data> list = new LinkedList<Data>();

	/** Add an item to the top of the stack */
	public void push(Data data) {
		list.add(data);
	}
	
	/** Remove the topmost item from the stack
	 * @throws EmptyException if the stack is empty
	 */
	public Data pop() throws EmptyException {
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
}