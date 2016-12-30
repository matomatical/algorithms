package com.matomatical.ads;

public class TwoThreeTree<Key extends Comparable<Key>, Value> {

	// the node at the root of the tree
	private Node root = null;

	/**
	 * Search for the value associated with a given key, throwing an exception 
	 * if the key is not found in this tree
	 * @param key look for the value associated with this key
	 * @return Value associated with key, if it exists
	 * @throws NotFoundException if the key is not contained in the tree
	 */
	public Value search(Key key) {
		Node node = root;

		while (node != null) {

			Pair match = node.match(key);

			if(match != null) {
				return match.value;
			}

			node = node.next(key);
		}

		// if we reach null without finding a match, well then the key is not
		// there!
		throw new NotFoundException("could not find item with key " + key);
	}

	/**
	 * Is there an instance of some key inside this tree?
	 * @param key the search key to look for
	 * @return true iff the search tree contains key
	 */
	public boolean contains(Key key) {
		Node node = root;

		while (node != null) {

			Pair match = node.match(key);

			if(match != null) {
				return true;
			}

			node = node.next(key);
		}

		// if we reach null without finding a match, well then the key is not
		// there!
		return false;
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
	 * Insert a new item into the tree with a given key and value. if there is
	 * already an item with this key, update its value.
	 * @param key the search key for retreiving this value later
	 * @param value the value to store under this key
	 */
	public void insert(Key key, Value value) {
		
		// base base case: root is null
		if(root == null) {
			// create initial node
			root = new Node(key, value);
		}

		// otherwise, insert into the root, handling possible overflow
		// (resulting in the returning of a non-null value to become the new 
		// root)
		Node result = insert(root, key, value);
		if(result != null) {
			root = result;
		}
	}

	/**
	 * Helper method to recursively insert in the tree, returning any promoted
	 * nodes from transformations that occured in the subtree on the way back up
	 * if no node was promoted, the return value will be null
	 * @param node the node on our way through the tree
	 * @param key the search key for retreiving this value later
	 * @param value the value to store under this key
	 * @return a promoted node from the subtree inserted-into, or null if no
	 * change has to occur above the insertion
	 */
	private Node insert(Node node, Key key, Value value) {
		
		// equals case: key already exists in this node
		Pair match = node.match(key);
		if (match != null) {
			// update value and return null since no structural changes
			match.value = value;
			return null;
		}

		// base case: leaf node
		if(node.leaf()) {
			// insert directly into this node and return the result
			return node.insert(new Node(key, value));
		}

		// remaining case: inserting into a subtree, handling possible overflow
		Node result = insert(node.next(key), key, value);
		if(result != null) {
			return node.insert(result);	
		} else {
			return null;
		}
	}

	public String toString() {
		if(root != null) {
			return root.toString();	
		} else {
			return "null";
		}
		
	}

	// internal pair class, holds a key-value pair
	private class Pair implements Comparable<Pair>{
		public final Key key;
		public Value value;
		public Pair(Key key, Value value) {
			this.key = key;
			this.value = value;
		}
		public int compareTo(Pair that) { /** compare the pair */
			return this.key.compareTo(that.key);
		}
		public String toString() {
			return "(" + key.toString() + ": " + value.toString() +")";
		}
	}

	// internal node class, forms the tree
	private class Node {
		private static final int MAXPAIRS = 2;
		private int n; // actual number of pairs (always 0 <= n <= MAXPAIRS)

		// overcoming generic array creation errors
		// see stackoverflow.com/a/10690245/5395650
		@SuppressWarnings("unchecked")
		private Pair[] pairs = new TwoThreeTree.Pair[MAXPAIRS];
		@SuppressWarnings("unchecked")
		private Node[] links = new TwoThreeTree.Node[MAXPAIRS+1];
		
		public Node(Key key, Value value) {
			pairs[0] = new Pair(key, value);
			n = 1;
		}
		
		public Node(Pair pair) {
			pairs[0] = new Pair(pair.key, pair.value);
			n = 1;
		}

		/** Get the pair whose key matches this key (or null if none) */
		public Pair match(Key key) {
			for(int i = 0; i < n; i++) {
				if(key.compareTo(pairs[i].key) == 0) {
					return pairs[i];
				}
			}
			// no match found!
			return null;
		}

		/** Is this node a leaf node? (node with no children) */
		public boolean leaf() {
			for(int i = 0; i < n+1; i++) {
				if(links[i] != null) {
					// non-null child! not a leaf node
					return false;
				}
			}
			// all children are null! we are a leaf node
			return true;
		}

		/** Get the child node that this key belongs in */
		public Node next(Key key) {
			for(int i = 0; i < n; i++) {
				if(key.compareTo(pairs[i].key) < 0) {
					return links[i];
				}
			}
			return links[n];
		}

		/**
		 * Merge a two-node into this (two- or three-) node. If this is a two-
		 * node, then the given node will join it to make a three-node, causing
		 * no overflow and returning null. If this is a three-node, then the
		 * given node and the two existing pairs will be rearranged so that the
		 * median-key pair is promoted with the other two pairs as its children.
		 * the median-key pair will then be returned (to be handled further up
		 * the tree).
		 * <br>
		 * This method loses a reference to a child node, depending on where the
		 * given node fits. This is fine because those references should be
		 * carried within the given node, which will have come from the lost
		 * child node's direction.
		 */
		public Node insert(Node that) {
			if(that.n != 1) {
				throw new TreeException("can't insert a "+(that.n+1)+" node");
			}

			if(this.n == 1) {
				// easy case: there is room for the other node's pair
				int comparison = this.pairs[0].compareTo(that.pairs[0]);

				if(comparison < 0) {
					// this pair's first key is less, we should put the new
					// stuff on the right
					this.pairs[1] = that.pairs[0];
					this.links[1] = that.links[0];
					this.links[2] = that.links[1];

					// note that we lose the previous value of this.links[1]

				} else {
					// this pair's first key is larger, we should put the new
					// stuff on the left

					// move existing info to left
					this.pairs[1] = this.pairs[0];
					this.links[2] = this.links[1];

					// copy in new info
					this.pairs[0] = that.pairs[0];
					this.links[0] = that.links[0];
					this.links[1] = that.links[1];
					
					// note that we lose the previous value of this.links[0]
				}

				// either way, this node now contains two key/value pairs
				this.n = 2;

				// but no structural changes need to happen above this point,
				// so we should return null
				return null;

			} else { // (this.n == 2)
				// harder case: insertion is going to cause this node to break
				// apart!
				
				if (this.pairs[0].compareTo(that.pairs[0]) > 0) {
					// we're inserting on the left side of both pairs

					// keep the right pair as a new node
					Node right = new Node(this.pairs[1]);
					right.links[0] = this.links[1];
					right.links[1] = this.links[2];

					// the original left pair becomes a parent of both this new 
					// right node and that node inserted on the left
					Node middle = new Node(this.pairs[0]);
					middle.links[0] = that;
					middle.links[1] = right;

					// this middle node must be returned to be inserted into 
					// the node above 
					return middle;
					
					// note that we lose the previous value of this.links[0]

				} else if (this.pairs[1].compareTo(that.pairs[0]) < 0) {
					// we're inserting on the left side of both pairs, second
					// pair will be promoted

					// keep the left pair as a new node
					Node left = new Node(this.pairs[0]);
					left.links[0] = this.links[0];
					left.links[1] = this.links[1];

					// the original right pair becomes a parent of both this new
					// left node and that node inserted on the right
					Node middle = new Node(this.pairs[1]);
					middle.links[0] = left;
					middle.links[1] = that;

					// this new middle node must be returned to be inserted into
					// the node above
					return middle;

					// node that we lose the previous value of this.links[2]

				} else {
					// we must be inserting between the two pairs

					// keep the left pair as a new node, also giving it the
					// left child of the inserted node as a right child

					Node left = new Node(this.pairs[0]);
					left.links[0] = this.links[0];
					left.links[1] = that.links[0];

					// same with the right pair which inherets the inserted
					// node's right child as its left child
					Node right = new Node(this.pairs[1]);
					right.links[1] = this.links[2];
					right.links[0] = that.links[1];

					// the inserted node will become a new parent node for both
					// of these new nodes (now that its children are okay)
					that.links[0] = left;
					that.links[1] = right;

					// this inserted node must be returned to be inserted again
					// into the node above
					return that;

					// note that the previous value of this.links[1] is lost
				}
			}
		}

		public String toString() {

			String string = "[" + links[0] + pairs[0];
			for(int i = 1; i < n; i++) {
				string += "" + links[i] + pairs[i];
			}
			string += links[n] + "]";
			return string;
		}
	}
}