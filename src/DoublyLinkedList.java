package com.matomatical.ads;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoublyLinkedList<Item> implements Iterable<Item> {
	
	private class Node {
		public Item data;
		public Node(Item data) {
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
	public void add(Item data) {
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
	public void addEnd(Item data) {
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
	public Item remove() throws EmptyException {
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
	public Item removeEnd() throws EmptyException {
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

	@Override
	public Iterator<Item> iterator(){
		return new DoublyLinkedListIterator(this.first);
	}

	public class DoublyLinkedListIterator implements Iterator<Item>{
		Node next;

		DoublyLinkedListIterator(Node first){
			next = first;
		}

		@Override
		public boolean hasNext() {
			return (next != null);
		}

		@Override
		public Item next() throws NoSuchElementException {
			if(next == null){
				throw new NoSuchElementException();
			}

			Item data = next.data;
			next = next.next;
			return data;
		}

		@Override
		public void remove() throws NoSuchElementException {
			if(next == null){
				throw new NoSuchElementException();
			}

			if(next.prev != null){
				next.prev.next = next.next;
				next.next.prev = next.prev;
			} else {
				DoublyLinkedList.this.first = next.next;
				if(next.next == null){
					DoublyLinkedList.this.last = null;
				}
			}
		}
	}
}