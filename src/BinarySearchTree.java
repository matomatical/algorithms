package com.matomatical.ads;

public class BinarySearchTree<Key extends Comparable<Key>, Value> {

	private static class Node<Key, Value> {
		public Key key;
		public Value value;
		public Node<Key, Value> left = null, right = null;
		public Node(Key key, Value value){
			this.key = key;
			this.value = value;
		}
	}

	Node<Key, Value> root = null;

	public void insert(Key key, Value value){
	
		if(value == null) {
			// null keys are not allowed because a null search value means
			// a key is not in the tree, so it cannot also mean the key
			// is in the tree with value null!
			// throw an exception here
		}

		root = insert(root, key, value);
	}

	public Node<Key, Value> insert(Node<Key, Value> node, Key key, Value value){

		if (node == null) {
			return new Node<Key, Value>(key, value);
		}
	
		int comparison = node.key.compareTo(key);
		if(comparison < 0) {
			// node < key, key might be on the right side:
			node.right = insert(node.right, key, value);
		} else if (comparison > 0) {
			// node > key, key might be on the left side:
			node.left = insert(node.left, key, value);
		} else {
			// node == key, this is the key!
			node.value = value;
		}

		return node;
	}

	public Value search(Key key) {
		Node<Key, Value> node = root;

		while(node != null) {
			int comparison = node.key.compareTo(key);
			if(comparison < 0) {
				// node < key, key might be on the right side:
				node = node.right;
			} else if (comparison > 0) {
				// node > key, key might be on the left side:
				node = node.left;
			} else {
				// node == key, this is the key! we found it!
				return node.value;
			}
		}

		// key is nowhere to be found in the tree
		return null;
	}
}