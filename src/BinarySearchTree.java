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

	/**
	 * Delete the entry associated with a given key from the tree.
	 * @param key the key whose entry is to be removed from the tree
	 */
	public void delete(Key key){
		root = delete(root, key);
	}

	/**
	 * Helper method to recursively find the node to be deleted (based on key)
	 * and update the tree on the way back
	 * @param key the key whose entry is to be removed from the tree
	 * @return the node provided, or its replacement if the tree was modified
	 * during deletion
	 * <br>
	 * usage example:
	 * `node.left = delete(node.left, key)`
	 * <br>
	 * this will search for the key to delete, and update node's left subtree 
	 * if needs to change during the deletion (for example if it was pointing 
	 * at the node that was deleted, and now needs to point at the replacement 
	 * or null)
	 */
	private Node<Key, Value> delete(Node<Key, Value> node, Key key) {

		// base case
		if (node == null) {
			// if we run into a null node, then we have concluded that the 
			// node to be deleted is not in the tree.
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

		// if no change was made, just return the node itself
		return node;
	}

	/**
	 * carry out the actual delete operation on a node in the tree, and return
	 * the correct replacement node (successor, or null) resulting from removing
	 * the node from the tree and substituting its successor in its place
	 * @param node the node to replace
	 * @return the value that it should be replaced with on the parent
	 */
	private Node<Key, Value> delete(Node<Key, Value> node) {
		if(node.left == null && node.right == null) {
			// we can just remove this node from the tree, make the parent link
			// null
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

			// find the left-most child of the right child, that's our direct
			// successor
			Node<Key, Value> successor = node.right, presuccessor = null;
			while(successor.left != null) {
				presuccessor = successor;
				successor = successor.left;
			}

			// one thing's for sure, the successor has no left child. it can
			// take ours!
			successor.left = node.left;

			// assuming the successor wasn't just the node's right child, we
			// have to move it up into the node's place in the tree
			if(successor != node.right) {

				// we need to give the successor's right children to its parent,
				// in place of the successor itself (child may be null, but 
				// that's okay too)
				presuccessor.left = successor.right;
				
				// then, we need to give the successor our node's right child
				// as its own
				successor.right = node.right;
			}
			
			// if the successor is just our right child, then these 
			// transformations aren't necessary and all we need to do is 
			// promote the successor one level up the tree

			// in either case, the final step is to give the successor back to
			// the parent in place of the original node, which will now be
			// ready for garbage collection

			return successor;
		}
	}
}