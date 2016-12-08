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
	private Node last = null;
	private int length = 0;

	/** Add an element to the start of the list
	 * @param data the element to add
	 */
	public void add(Data data) {
		Node node = new Node(data);
		node.next = first;

		if(last == null){
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
		first = node.next;
		length--;

		if(first == null){
			last = null;
		}

		return node.data;
	}


	/** Remove and return an element from the end of the list. O(n) where
	 * n is the length of the list.
	 * @return the element from the end of the list
	 * @throws EmptyException if there are no items in the list
	 */
	public Data removeEnd() throws EmptyException {
		if(length == 0){
			throw new EmptyException("no items to remove");
		}

		Node node = last;

		// the new last is the one pointing to this last node
		Node i = first;
		while(i != null){
			if(i.next == node){
				i.next = null;
				last = i;
			}
			i = i.next;
		}

		length--;
		if(last == null){
			first = null;
		}

		return node.data;
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