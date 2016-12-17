package com.matomatical.ads;

public class BinarySearchTree<Key extends Comparable<Key>, Value> {

	// internal node class, forms the tree
	private static class Node<Key, Value> {
		public Key key;
		public Value value;
		public Node<Key, Value> left = null, right = null;
		public Node(Key key, Value value){
			this.key = key;
			this.value = value;
		}
	}

	// the node at the root of the tree
	Node<Key, Value> root = null;

	/**
	 * Insert a new item into the tree with a given key and value. if there is
	 * already an item with this key, update its value.
	 * @param key the search key for retreiving this value later
	 * @param value the value to store under this key
	 */
	public void insert(Key key, Value value){
		root = insert(root, key, value);
	}

	/**
	 * Helper method to easily perform insert operations.
	 * @param node the next node to follow looking for a place to insert
	 * @param key the search key for retreiving this value later
	 * @param value the value to store under this key
	 * @return the node provided, or a new node if the node provided was null
	 * and therefore ended up being the correct place to put the item being
	 * inserted
	 * <br>
	 * usage example:
	 * `node.left = insert(node.left, key, value)`
	 * <br>
	 * will search for a place to insert in the left subtree of Node node, and
	 * update node's left subtree if it changes (and a new reference was 
	 * returned)
	 */
	private Node<Key,Value> insert(Node<Key,Value> node, Key key, Value value) {

		if (node == null) {
			return new Node<Key,Value>(key, value);
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

	/**
	 * Search for the value associated with a given key, throwing an exception 
	 * if the key is not found in this tree
	 * @param key look for the value associated with this key
	 * @return Value associated with key, if it exists
	 * @throws NotFoundException if the key is not contained in the tree
	 */
	public Value search(Key key) throws NotFoundException {
		
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

		// this key is nowhere to be found in the tree
		throw new NotFoundException("could not find item with key " + key);
	}

	/**
	 * Search a tree for the value associated with a key, but silently return
	 * null if the key is not found.
	 * <br>
	 * NOTE: a return value of null may indicate that a key has not been found,
	 * but it may just indicate a key whose associated value is null
	 * @param key the key to search for
	 * @return the value associated with key in the tree, or null if the key is
	 * not contained in the tree
	 */
	public Value nullsearch(Key key) {
		try {
			return search(key);
		} catch (NotFoundException e) {
			return null;
		}
	}

	/**
	 * Is there an instance of some key inside this tree?
	 * @param key the search key to look for
	 * @return true iff the search tree contains key
	 */
	public boolean contains(Key key) {
		
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
				return true;
			}
		}

		// if we make it to the end of a tree branch but don't find the key,
		// then it's not in the tree!
		return false;
	}

}