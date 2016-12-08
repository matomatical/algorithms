package com.matomatical.ads;

public class DoublyLinkedList<Data> {
	
	private class Node {
		public Data data;
		public Node(Data data) {
			this.data = data;
			this.next = null;
			this.prev = null;
		}
		public Node next, prev;
	}

	private Node first = null;
	private Node last = null;
	private int length = 0;

	/** Add an element to the start of the list
	 * @param data the element to add
	 */
	public void add(Data data) {
		Node node = new Node(data);
		
		if(first != null){
			node.next = first;
			first.prev = node;
		} else {
			last = node;
		}
		first = node;

		length++;
	}

	/** Add an element to the end of the list
	 * @param data the element to add
	 */
	public void addEnd(Data data) {
		Node node = new Node(data);
		
		if(last != null){
			last.next = node;
			node.prev = last;
		} else {
			first = node;
		}

		last = node;

		length++;
	}

	/** Remove and return an element from the start of the list
	 * @return the element from the beginning of the list
	 * @throws EmptyException if there are no items in the list
	 */
	public Data remove() throws EmptyException {
		if(length == 0){
			throw new EmptyException("no items to remove");
		}

		Node node = first;

		first = first.next;
		if (first == null){
			last = null;
		} else {
			first.prev = null;
		}

		length--;
		return node.data;
	}


	/** Remove and return an element from the end of the list. O(1)
	 * @return the element from the end of the list
	 * @throws EmptyException if there are no items in the list
	 */
	public Data removeEnd() throws EmptyException {
		if(length == 0){
			throw new EmptyException("no items to remove");
		}

		Node node = last;

		last = last.prev;
		if(last == null){
			first = null;
		} else {
			last.next = null;
		}
		
		length--;
		return node.data;
	}

	/** The number of elements in the list */
	public int length() {
		return length;
	}

	/** true iff there are no elements in the list */
	public boolean isEmpty(){
		return (first == null);
	}
}