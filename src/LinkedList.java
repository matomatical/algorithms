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

	public void add(Data data) {
		Node node = new Node(data);
		node.next = first;
		first = node;
		length++;
	}

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
}