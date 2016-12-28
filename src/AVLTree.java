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

		/**
		 * Updates the height of this node based on the most up-to-date
		 * heights of its substrees
		 */
		public void updateHeight() {
			
			// height is height of the taller subtree (starting at zero for an 
			// empty subtree), plus one (counting this node)

			int l = (left == null  ? 0 : left.height);
			int r = (right == null ? 0 : right.height);
			this.height = 1 + (l > r ? l : r);
		}

		/**
		 * returns the balance factor for this node according to the calculation
		 * (right height - left height). That means a positive number indicates
		 * an imbalance to the right, and a negative means an imbalance to the
		 * left.
		 */
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

	/**
	 * Helper method to recursively insert in the tree, returning the 
	 * appropriate child pointer (after insertion and possible load balancing)
	 * on the way back up. Also keeps heights up to date.
	 * @param node the node on our way through the tree
	 * @param key the search key for retreiving this value later
	 * @param value the value to store under this key
	 * @return the correct node that the above node should point to after
	 * insertion (a new node, or the result of a rotation)
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

		// make sure this node's height is up to date before we continue up 
		// the tree
		node.updateHeight();

		// sort out any imbalances and return the correct node for the parent to
		// point to after any rotations have been carried out
		return balance(node);
	}

	/** 
	 * Check the balance factor of a node and preform rotations if things get 
	 * out of balance. Also keeps the heights up to date through any transfor-
	 * mations
	 * @param node the node to check
	 * @return the correct replacement node after any necessary rotations
	 */
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

	/**
	 * rotate the node to become the left child of its right child. Also keeps
	 * the height of all nodes involved up to date.
	 * @param node the node to rotate left
	 * @return the replacement node
	 * @throws TreeException if a rotation is not possible because the node or
	 * its right child are null
	 */
	private Node<Key, Value> rotateLeft(Node<Key, Value> node) {
		if (node == null || node.right == null) {
			// we can't rotate nodes that dont exist
			throw new TreeException("Left rotation impossible from this node");
		}

		Node<Key, Value> child = node.right;

		node.right = child.left;
		child.left = node;

		node.updateHeight();
		child.updateHeight();

		return child;
	}

	/**
	 * rotate the node to become the right child of its left child. Also keeps
	 * the height of all nodes involved up to date.
	 * @param node the node to rotate right
	 * @return the replacement node
	 * @throws TreeException if a rotation is not possible because the node or
	 * its left child are null
	 */
	private Node<Key, Value> rotateRight(Node<Key, Value> node) {
		if (node == null || node.left == null) {
			// we can't rotate nodes that dont exist
			throw new TreeException("Right rotation impossible from this node");
		}

		Node<Key, Value> child = node.left;

		node.left = child.right;
		child.right = node;

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


	/**
	 * Delete the entry associated with a given key from the tree.
	 * @param key the key whose entry is to be removed from the tree
	 */
	public void delete(Key key){
		root = delete(root, key);
	}

	/**
	 * Helper method to recursively find the node to be deleted (based on key)
	 * and update the tree on the way back (to make sure it remains balanced)
	 * @param key the key whose entry is to be removed from the tree
	 * @return the node provided, or its replacement if the tree was modified
	 * during deletion
	 */
	private Node<Key, Value> delete(Node<Key, Value> node, Key key) {

		// base case
		if (node == null) {
			// if we run into a null node, then we can conclude that the 
			// node to be deleted is already not in the tree.
			return null;
		}

		// recursive case
		int comparison = node.key.compareTo(key);
		if(comparison < 0) {
			// node < key, key might be on the right side:
			node.right = delete(node.right, key);

		} else if (comparison > 0) {
			// node > key, key might be on the left side:
			node.left = delete(node.left, key);

		} else {
			// node == key, this is the key! we found it!
			// time to actually delete it...
			return delete(node);
		}

		// make sure this node's height is up to date before we head back up
		// then sort out any resulting imbalances, and return the appropriate 
		// node
		node.updateHeight();
		return balance(node);
	}

	/**
	 * carry out the actual delete operation on a node in the tree, and return
	 * the correct replacement node (successor, or null) resulting from removing
	 * the node from the tree and substituting its successor in its place
	 * (AND balancing the resulting tree from the successor down)
	 * @param node the node to replace
	 * @return the value that it should be replaced with on the parent
	 */
	private Node<Key, Value> delete(Node<Key, Value> node) {
		if(node.left == null && node.right == null) {
			// we can just remove this node from the tree, make the parent link
			// null. there won't be any rebalancing to do
			return null;

		} else if (node.left == null){
			// left child is null, but right is not. we can just promote the
			// right child to become the parents new child
			return node.right;

		} else if (node.right == null) {
			// right child is null, but left is not. we can just promote the 
			// left child to become the parents new child
			return node.left;

		} else {
			// we have two children, great! lets find and promote our successor

			if(node.right.left == null) {
				node.right.left = node.left;
				node.right.updateHeight();
				return balance(node.right);
			} else {
				
				Node<Key, Value> presuccessor = node.right;
				while (presuccessor.left.left != null) {
					presuccessor = presuccessor.left;
				}
				Node<Key, Value> successor = presuccessor.left;

				successor.left = node.left;
				presuccessor.left = successor.right;
				successor.right = balanceLeft(node.right);
				
				successor.updateHeight();
				return balance(successor);
			}
		}
	}

	/** balance all left children of this node recursively, return result */
	private Node<Key, Value> balanceLeft(Node<Key, Value> node) {
		if(node == null) {
			return null;
		} else {
			node.left = balanceLeft(node.left);
			node.updateHeight();
			return balance(node);
		}
	}

	/** private helper method for debugging: print the path to a node */
	private String path(Key key) {
		
		Node<Key, Value> node = root;
		String path = "";
		while(node != null) {
			path += node.value;
			int comparison = node.key.compareTo(key);
			if(comparison < 0) {
				// node < key, key might be on the right side:
				path += "/";
				node = node.right;
			} else if (comparison > 0) {
				// node > key, key might be on the left side:
				path += "\\";
				node = node.left;
			} else {
				// node == key, this is the key! we found it!
				return path;
			}
		}

		// this key is nowhere to be found in the tree
		return path + "!?";
	}

}
