package com.matomatical.ads;

public class TwoThreeTree<Key extends Comparable<Key>, Value> {

	// the node at the root of the tree
	private Node root = null;

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


	public void delete(Key key) {
		// kick off recursive deletion method starting at the root.
		delete(root, key);

		// In the end, if the root becomes a hole, we'll need to grab its 
		// only child to become the new root!
		if (root.empty()) {
			root = root.child();
		}
	}

	public void delete(Node node, Key key) {

		if (node == null) {
			// we have reached the end without finding the node to delete
			// just head back up without doing anything
			return;

		} else if (node.match(key) != null) {
			// is the key to delete here? yep! let's delete it from this node
			node.delete(key);

			// this may create a hole, but will fix all holes created
			// below this node. so, we can safely return
			return;

		} else {
			// the key to delete isnt here, but it may be below us!
			delete(node.next(key), key);

			// now we may have created a hole below us, so we should
			// fix that before we head back up the tree
			node.fixChildren();
			// which might turn our node into a hole, but they will
			// fix it as we unwind the recursion!
			return;
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

		/** Is this node a hole node? (node with no pairs and one child)? */
		public boolean empty() {
			return (n == 0);
		}

		/** Get the only child node of this one-node/hole/empty node) */
		public Node child() {
			if (n == 0) {
				return links[0];
			} else {
				throw new TreeException(
					"Node not empty; use next(Key) to get a child!");
			}
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
			if (that.n != 1) {
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

		/**
		 * Delete a key contained within this node. For a leaf node, this
		 * just deletes the pair containing the node (possibly creating a hole)
		 * For a non-leaf node, the pair containing the key to be deleted is
		 * replaced by its successor in the tree, the successor pair is deleted
		 * from a leaf, and any hole is propagated up to the level of this 
		 * node. So, in the end, this node may become a hole.
		 * @param key The key to be deleted from this node
		 * @throws TreeException if the key is not contained in this node
		 */
		public void delete(Key key) {
			
			if (this.leaf()) {
				// we were already at a leaf node, we just have to do a
				// removal
				this.remove(key);
				// this may have turned us into a leaf hole! but that's okay 
				// because we said we might do that, someone else will fix!

			} else { // non-leaf
				// first, find our successor node
				Node next = this.next(key);
				Node successor = next.leftmost();

				// then, replace our key pair with our successor pair
				// (successor node's leftmost pair)
				this.set(key, successor.pairs[0]);

				// and remove our successor pair from our successor node
				successor.remove(successor.pairs[0].key);

				// at this point, we may have just created a hole all the way 
				// down at our successor node. we need to fix that hole (at 
				// least up to this node's level) before we head back up
				
				// start by fixing hole to the left of next, which may create a 
				// hole at next.
				next.fixOnLeft();

				// then we need to fix any hole created in next
				this.fixChildren();
				// this may create a hole in THIS node, but once again that's
				// fine! someone else will fix it on the way back
			}
		}

		private Node leftmost() {
			// is there a node to the left of this node?
			if (this.links[0] != null) {
				// return *its* leftmost node!
				return this.links[0].leftmost();
			}
			// if not, this is the leftmost node! send it back up
			return this;
		}

		private void fixOnLeft() {
			
			// base case, a leaf node has no left children that could
			// have holes, so it cannot fill anything
			if (leaf()) {
				return;
			}

			// recursive case, tell our left child to fix holes on its left
			this.links[0].fixOnLeft();
			
			// this may have created a hole on our left! fix it!
			this.fixChildren();

			// (that may make THIS a hole, to be dealt with by parent)
			return;
		}

		private void set(Key key, Pair pair) {
			for(int i = 0; i < n; i++) {
				if(key.compareTo(pairs[i].key) == 0) {
					pairs[i] = pair;
				}
			}
		}

		/** Remove a pair from a leaf node (by key), possibly leaving a hole */
		private void remove(Key key) {
			
			if (n == 2) {
			// easy! just turn this from a three node into a two node!
				if (key.compareTo(pairs[0].key) == 0) {
					// we've been asked to remove the first pair of the two
					pairs[0] = pairs[1];
					pairs[1] = null;
					n = 1;

				} else if (key.compareTo(pairs[1].key) == 0) {
					// it's the second pair that needs removing, the
					// first pair can stay
					pairs[1] = null;
					n = 1;

				} else {
					// the key is not inside this node! something's
					// wrong...
					throw new TreeException("key " + key + " can't be " +
						"removed because it's not here");
				}

			} else { // n = 1
				// easy! we just have to turn this two node into a
				// (leaf) one-node! a.k.a a hole, or empty node
				// (note: someone else needs to fix afterwards)
				pairs[1] = null;
				n = 0;
			}
		}

		/**
		 * the mother of all methods right here! promotes any holes among
		 * this node's children to the level of their parent (this node)
		 * does nothing if there are no holes among its children
		 */
		private void fixChildren() {

			// a leaf node has no children to fix
			if(leaf()) {
				return;
			}

			// otherwise, we first need to know which of our children is a hole
			// if there is indeed one!
			int hole = findHole();
			if (hole == -1) {
				// no holes? fantastic! nothing to do, we can finish early
				return;
			}

			// next, we need to figure out which case we're looking at
			// so that we can determine how to fix it

			// first of all, how many children do we have?
			if(n == 1) {
				// this is a two-node, with two children
				// next, how big is the node that *isn't* the hole?
				int other = (1 - hole);
				if(links[other].n == 1) {
					// the other node is a two-node, too!
					// this is a case of a single sibling two-node:
					fixSingleSiblingTwoNode(hole);
				} else { // links[other].n == 2
					// the other node is a three node!
					// this is a case of a nearby sibling three-node:
					fixNearbySiblingThreeNode(hole);
				}

			} else { // (n == 2), three children
				// alright, this is a three-node, with three children

				// next we must establish what sort of situation the hole is in

				int other1 = (hole + 1) % 3; // index of one sibling of hole
				int other2 = (hole + 2) % 3; // index of other sibling of hole
				if (links[other1].n == 1 && links[other2].n == 1) {
					// both siblings are two-nodes!
					// this is a case of a double sibling two-node:
					fixDoubleSiblingTwoNode(hole);
				
				} else {
					// otherwise, one of the siblings (possibly both) must be a 
					// three-node

					// well, time for some case reduction:

					// if it's a nearby two-node and distant three-node, we can
					// move the hole to make it a case of a nearby three-node!
					if (hole != 1 && links[1].n == 1 && links[2 - hole].n == 2){
						// indeed! nearby hole [1] is a two-node, and distant 
						// [2 - hole] node is a three-node!
						
						// move the hole into the middle
						moveHoleToMiddleTwoNode(hole);
						hole = 1;
					}

					// now, we know that the hole is adjacent to a three-node!
					// this is a case of a nearby siling three-node!
					fixNearbySiblingThreeNode(hole);
				}

				// since one of those fixes must be applied, we are done :)
				return;
			}
		}

		/**
		 * Which of a node's children is a hole? Returns the links[] index
		 * of the first empty child (from left to right), or -1 if no children
		 * are empty.
		 */
		private int findHole() {
			for(int i = 0; i < n+1; i++) {
				if(links[i].empty()) {
					return i;
				}
			}
			return -1;
		}

		/**
		 * Fix the case of a hole with a single two-node sibling, by lowering a 
		 * pair from the parent to the sibling. Leaves the parent as a hole.
		 */
		private void fixSingleSiblingTwoNode(int h) {

			Node hole = this.links[h];
			Node sibling = this.links[1-h];

			// to fix this, demote parent pair into sibling and leave
			// a hole at the parent
			sibling.n = 2;

			if (h == 0) {
				// hole is on the left side!

				// move existing pair over
				sibling.links[2] = sibling.links[1];
				sibling.links[1] = sibling.links[0];
				sibling.pairs[1] = sibling.pairs[0];

				// insert parent pair and subtree link from hole on left
				sibling.pairs[0] = this.pairs[0];
				sibling.links[0] = hole.links[0];

			} else { // h == 1
				// hole is on right side!
				
				// no need to move existing pair over,
				// just insert parent pair and subtree link from hole on right
				sibling.pairs[1] = this.pairs[0];
				sibling.links[2] = hole.links[0];
			}

			// either way, remove pair from parent
			this.links[0] = sibling;
			this.links[1] = null;
			this.pairs[0] = null;
			this.n = 0;
		}

		private void fixDoubleSiblingTwoNode(int h) {

			Node hole = this.links[h];

			// method to fix depends if we are in the middle or on either side

			if (h == 0) {
				// hole is on the left, demote left pair from parent into middle
				
				Node middle = this.links[1];
				
				// move over pair in middle
				middle.n = 2;
				middle.pairs[1] = middle.pairs[0];
				middle.links[2] = middle.links[1];
				middle.links[1] = middle.links[0];

				// bring in left pair from parent and link from hole
				middle.pairs[0] = this.pairs[0];
				middle.links[0] = hole.links[0];

				// remove left pair and hole link from parent
				this.n = 1;
				this.links[0] = this.links[1];
				this.links[1] = this.links[2];
				this.links[2] = null;
				this.pairs[0] = this.pairs[1];
				this.pairs[1] = null;

			} else if (h == 2) {
				// hole is on the right, demote right pair from parent into mid
			
				Node middle = this.links[1];

				// no need to move over pair in middle, just bring in
				// right pair from parent, and link from hole
				middle.n = 2;
				middle.pairs[1] = this.pairs[1];
				middle.links[2] = hole.links[0];

				// and remove right pair and hole link from parent
				this.n = 1;
				this.links[2] = null;
				this.pairs[1] = null;
				
			} else { // h == 1
				// hole is in the middle, lets go with demoting left pair into
				// left child (arbitrarily - could have chosen right into right)

				Node left = this.links[0];

				// no need to make room, lets insert left pair from parent
				// into right of left child
				left.n = 2;
				left.pairs[1] = this.pairs[0];
				left.links[2] = hole.links[1];

				// now we remove left pair and middle link from parent
				this.n = 1;
				this.links[1] = this.links[2];
				this.links[2] = null;
				this.pairs[0] = this.pairs[1];
				this.pairs[1] = null;
			}
		}

		private void moveHoleToMiddleTwoNode(int h) {

			Node hole = this.links[h];
			Node middle = this.links[1];

			// to move the hole over we need to steal a pair from the parent,
			// and replace it with the pair from the middle node (making a hole)

			if (h == 0) {
				// the hole is on the left side
				
				// steal left pair from parent
				hole.n = 1;
				hole.links[1] = middle.links[0];
				hole.pairs[0] = this.pairs[0];

				// replace that pair in parent with pair from middle
				this.pairs[0] = middle.pairs[0];

				// and remove that pair from the middle (creating a new hole)
				middle.n = 0;
				middle.pairs[0] = null;
				middle.links[0] = middle.links[1];
				middle.links[1] = null;
				
			} else if (h == 2) {
				// the hole is on the right side

				// steal the right pair from parent and right link from middle
				hole.n = 1;
				hole.links[1] = hole.links[0];
				hole.links[0] = middle.links[1];
				hole.pairs[0] = this.pairs[1];

				// replace that pair in parent with pair from middle
				this.pairs[1] = middle.pairs[0];

				// remove that pair from the middle (creating a new hole)
				middle.n = 0;
				middle.links[1] = null;
				middle.pairs[0] = null;
			
			} else { // h == 1
				throw new TreeException("can't move hole in middle to middle!");
			}
		}

		private void fixNearbySiblingThreeNode(int h) {

			int s; // index of sibling three-node

			if (h > 0 && this.links[h - 1].n == 2) {
				// there's a three-node on the left!
				// (and possibly also right, but that doesn't matter)
				s = h - 1;
			} else if (h < 2 && this.links[h + 1].n == 2) {
				// there's a three-node on the right!
				s = h + 1;
			} else { // ~ this.links[h +/- 1].n != 2
				throw new TreeException("no adjacent sibling three-node");
			}

			Node hole = this.links[h];
			Node sibling = this.links[s];

			// now that we have node indices and references, it's time to fix 
			// the hole!

			// we'll do that by stealing a pair from the parent to fill the hole
			// and replacing the pair with one from [s], the adjacent three-node

			if (s < h) {
				// sibling is to the left of hole

				// steal pair from parent and last link from sibling
				hole.n = 1;
				hole.pairs[0] = this.pairs[s];
				hole.links[1] = hole.links[0];
				hole.links[0] = sibling.links[2];

				// replace pair in parent from rightmost pair in sibling
				this.pairs[s] = sibling.pairs[1];
				
				// remove that pair and last link from the sibling
				sibling.n = 1;
				sibling.pairs[1] = null;
				sibling.links[2] = null;

			} else { // s > h
				// sibling is to the right of hole

				// steal pair from parent and first link from sibling
				hole.n = 1;
				hole.pairs[0] = this.pairs[h];
				hole.links[1] = sibling.links[0];

				// replace that pair in parent with leftmost sibling pair
				this.pairs[h] = sibling.pairs[0];

				// and remove that leftmost pair and link in sibling
				sibling.n = 1;
				sibling.pairs[0] = sibling.pairs[1];
				sibling.pairs[1] = null;
				sibling.links[0] = sibling.links[1];
				sibling.links[1] = sibling.links[2];
				sibling.links[2] = null;
			}
		}


		/** Get a string representing this node (and its children) */
		public String toString() {
			String string = "[" + links[0] + pairs[0];
			for(int i = 1; i < n; i++) {
				string += "" + links[i] + pairs[i];
			}
			string += links[n] + "]";
			return string;
		}
	}




	// internal pair class, holds a key-value pair
	private class Pair implements Comparable<Pair> {
		public final Key key;
		public Value value;
		public Pair(Key key, Value value) {
			this.key = key;
			this.value = value;
		}
		/** Compare the pair */
		public int compareTo(Pair that) {
			return this.key.compareTo(that.key);
		}
		/** Get a string representing this pair */
		public String toString() {
			return "(" + key + ": " + value + ")";
		}
	}




	/** Get a string representing this tree */
	public String toString() {
		if(root != null) {
			return root.toString();	
		} else {
			return "null";
		}
	}
}