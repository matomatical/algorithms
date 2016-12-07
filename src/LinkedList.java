package com.matomatical.ads;

public class LinkedList<Data> {
	
	private class Node {
		public Data data;
		public Node(Data data) {
			this.data = data;
		}
		public Node next;
	}

	private Node first = null;
	private int length = 0;

	/** Add an element to the start of the list
	 * @param data the element to add
	 */
	public void add(Data data) {
		Node node = new Node(data);
		node.next = first;
		first = node;
		length++;
	}

	/** Remove and return an element from the start of the list
	 * @return the element from the beginning of the list
	 * @throws EmptyException if there are no items in the list
	 */
	public Data remove() {
		if(length == 0){
			throw new EmptyException("no items to remove");
		} else {
			Node node = first;
			first = node.next;
			length--;
			return node.data;
		}
	}

	/** The number of elements in the list */
	public int length() {
		return length;
	}

	/** true iff there are no elements in the list */
	public boolean isEmpty(){
		return (length == 0);
	}
}