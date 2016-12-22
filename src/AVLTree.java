package com.matomatical.ads;

public class AVLTree<Key extends Comparable<Key>, Value> {
	
	// internal node class, forms the tree
	private static class Node<K,V> {
		
		public int height = 1;
		public Node<K, V> left = null, right = null;

		public K key;
		public V value;
		public Node(K key, V value){
			this.key = key;
			this.value = value;
		}

		// NOTE: requires that subtree heights are correct, if they exist
		public void updateHeight() {
			
			// height is height of the taller subtree (starting at zero for an 
			// empty subtree)...
			int l = (left == null  ? 0 : left.height);
			int r = (right == null ? 0 : right.height);

			// plus one counting this node
			this.height = 1 + (l > r ? l : r);
		}

		public int getBalanceFactor() {
			int l = (left == null  ? 0 : left.height);
			int r = (right == null ? 0 : right.height);

			return r - l;
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

		// make sure this node's height is up to date before we continue up 
		// the tree
		node.updateHeight();

		// sort out any imbalances and return the correct node for the parent to
		// point to after any rotations have been carried out
		return balance(node);
	}

	private Node<Key,Value> balance(Node<Key, Value> node) {

		// okay let's check this node's balance factor
		int nodeBalanceFactor = node.getBalanceFactor();

		if (nodeBalanceFactor < -1) {
			// we have detected an imbalance to the left!
			// what is the balance factor below us?
			int childBalanceFactor = node.left.getBalanceFactor();

			// if the balance is coming from the opposite direction,
			// we should rotate the left child to turn it into an imbalance
			// from the same direction
			if (childBalanceFactor > 0) {
				node.left = rotateLeft(node.left);
			}

			// in either case, a right rotation will fix the imbalance now
			return rotateRight(node);

		}  else if (nodeBalanceFactor > 1) {
			// we have detected an imbalance to the right!
			
			// what is the balance factor below us?
			int childBalanceFactor = node.right.getBalanceFactor();

			// if the balance is coming from the opposite direction,
			// we should rotate the right child to turn it into an imbalance
			// from the same direction
			if (childBalanceFactor < 0) {
				node.right = rotateRight(node.right);
			}

			// in either case, a left rotation will fix the imbalance now
			return rotateLeft(node);

		} else {
			// no imbalances at this level, no changes to make!
			return node;
		}
	}

	private Node<Key, Value> rotateLeft(Node<Key, Value> node) {
		if (node == null || node.right == null) {
			// we can't rotate nodes that dont exist
			throw new TreeException("Left rotation impossible from this node");
		}

		Node<Key, Value> child = node.right;

		node.left = child.right;
		child.right = node;

		node.updateHeight();
		child.updateHeight();

		return child;
	}

	private Node<Key, Value> rotateRight(Node<Key, Value> node) {
		if (node == null || node.left == null) {
			// we can't rotate nodes that dont exist
			throw new TreeException("Right rotation impossible from this node");
		}

		Node<Key, Value> child = node.left;

		node.right = child.left;
		child.left = node;

		node.updateHeight();
		child.updateHeight();

		return child;
	}

	public int height() {
		return (root == null ? 0 : root.height);
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