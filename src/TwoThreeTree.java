package com.matomatical.ads;

public class TwoThreeTree<Key extends Comparable<Key>, Value> {

	private Node root = null;

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

	public Value nullsearch(Key key) {
		try {
			return search(key);
		} catch (NotFoundException e) {
			return null;
		}
	}

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

		public Pair match(Key key) {
			for(int i = 0; i < n; i++) {
				if(key.compareTo(pairs[i].key) == 0) {
					return pairs[i];
				}
			}
			// no match found!
			return null;
		}

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

		public Node next(Key key) {
			for(int i = 0; i < n; i++) {
				if(key.compareTo(pairs[i].key) < 0) {
					return links[i];
				}
			}
			return links[n];
		}

		/**
		 * this method needs to take a two-node and insert it and its links into
		 * the current node, POSSIBLY OVERFLOWING AND CREATING A SPLIT
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

	public static void main(String[] args) {
		TwoThreeTree<String, Integer> t = new TwoThreeTree<>();

		t.insert("A", 1);
		t.insert("L", 1);
		t.insert("G", 1);
		t.insert("O", 1);
		t.insert("R", 1);
		t.insert("I", 1);
		t.insert("T", 1);
		t.insert("H", 1);
		t.insert("M", 1);
		t.insert("S", 1);
		t.insert("F", 1);
		t.insert("U", 1);
		t.insert("N", 1);

		System.out.println(t);
	}
}