package com.matomatical.ads;

public class UnionFinder {

	// collection of parent references
	private int[] nodes;

	public UnionFinder(int n){
		nodes = new int[n];

		// each node starts as its own parent
		for(int i = 0; i < nodes.length; i++){
			nodes[i] = i;
		}
	}

	public boolean find(int p, int q) {
		// do these two nodes both point to the same root?
		return root(p) == root(q);
	}

	public void union(int p, int q) {
		// p and q need to point to the same root,
		// lets point p's root at q's root
		nodes[root(p)] = root(q);
	}

	private int root(int p){
		// am *i* the root node?
		if(nodes[p] == p){
			return p;
			
		// if not, lets ask my parent who the root node is
		// and remember their answer for who to ask next time!
		} else {
			return (nodes[p] = root(nodes[p]));
		}
	}
}