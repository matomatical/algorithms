package com.matomatical.ads;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<Item> implements Iterable<Item> {
	
	private static class Node<Item> {
		public Item data;
		public Node(Item data) {
			this.data = data;
		}
		public Node<Item> next;
	}

	private Node<Item> first = null;
	private Node<Item> last = null;
	private int length = 0;

	/** Add an element to the start of the list
	 * @param data the element to add
	 */
	public void add(Item data) {
		Node<Item> node = new Node<Item>(data);
		node.next = first;

		if (last == null) {
			last = node;
		}
		first = node;

		length++;
	}

	/** Add an element to the end of the list
	 * @param data the element to add
	 */
	public void addEnd(Item data) {
		Node<Item> node = new Node<Item>(data);

		if (last != null) {
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
	public Item remove() throws EmptyException {
		if (length == 0) {
			throw new EmptyException("no items to remove");
		}

		Node<Item> node = first;
		first = node.next;
		length--;

		if (first == null) {
			last = null;
		}

		return node.data;
	}

	/** Remove and return an element from the end of the list. O(n) where
	 * n is the length of the list.
	 * @return the element from the end of the list
	 * @throws EmptyException if there are no items in the list
	 */
	public Item removeEnd() throws EmptyException {
		if(length == 0){
			throw new EmptyException("no items to remove");
		}

		Node<Item> node = last;

		// the new last is the one pointing to this last node
		Node<Item> i = first;
		while (i != null) {
			if (i.next == node) {
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

	@Override
	public Iterator<Item> iterator(){
		return new LinkedListIterator(this.first);
	}

	public class LinkedListIterator implements Iterator<Item>{
		Node<Item> next, prev = null;

		LinkedListIterator(Node<Item> first){
			next = first;
		}

		@Override
		public boolean hasNext() {
			return (next != null);
		}

		@Override
		public Item next() throws NoSuchElementException {
			if (next == null) {
				throw new NoSuchElementException();
			}

			Item data = next.data;
			prev = next;
			next = next.next;
			return data;
		}

		@Override
		public void remove() throws NoSuchElementException {
			if (next == null) {
				throw new NoSuchElementException();
			}

			if (prev != null) {
				prev.next = next.next;
			} else {
				LinkedList.this.first = next.next;
				if (next.next == null) {
					LinkedList.this.last = null;
				}
			}
		}
	}
}