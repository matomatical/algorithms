package com.matomatical.ads;

import java.util.Comparator;

public class PriorityQueue<Item extends Comparable<Item>> {
	
	private DynamicArray<Item> heap;

	/** create a new priority queue on the items in an array, using O(n) 
	 * operations where n is the length of the items array.
	 * (this is faster than the O(nlogn) required for n individual add(item) 
	 * operations)
	 * the priority will be decided by comparing items using their compareTo()
	 * method
	 */
	public PriorityQueue(Item[] items){
		
		heap = new DynamicArray<Item>(items);

		// bottom-up heapify to establish heap property
		for (int i = items.length/2; i >= 0; i--) {
			siftdown(i);
		}
	}
	
	/** create a new empty priority queue
	 * priority will be decided by comparing items using their compareTo() 
	 * method
	 */
	public PriorityQueue(){
		heap = new DynamicArray<Item>();
	}

	/** Insert an item into its correct position in the priority queue */
	public void add(Item item) {
		heap.add(item);

		// sift up to restore heap property
		siftup(heap.length() - 1);
	}
	
	/** Remove the item with the highest priority from the queue
	 * @return the item removed
	 * @throws EmptyException if the queue is empty
	 */
	public Item removeMax() {

		// if there are no items, we need to throw an exception
		if (size() == 0) {
			throw new EmptyException("no items left in the priority queue");
		}

		// if this is the last item, we can just remove it from the array
		if (size() == 1) {
			return heap.remove();
		}

		// otherwise we need to take the first thing and find a replacement
		// to fill the hole at the top of the heap. we can use the item at the
		// end of the dynamic array, using remove to reduce the array by one
		// in size while we're at it
		Item max = heap.get(0);
		heap.set(0, heap.remove());
		
		// then we need to restore the heap property
		siftdown(0);

		return max;
	}

	/** Return (without removing) the item with the highest priority
	 * @throws EmptyException if the queue is empty
	 */
	public Item max() {
		// if there are no items, we need to throw an exception
		if (size() == 0) {
			throw new EmptyException("no items in the priority queue");
		}

		return heap.get(0);
	}
	
	/** The number of items in the queue */
	public int size() {
		return heap.length();
	}
	
	/** true iff the queue has no items in it */
	public boolean isEmpty() {
		return heap.length() == 0;
	}

	/**
	 * sift the item at index i of the heap down to its proper place in the heap
	 * @param i the index to start sifting down from
	 */
	private void siftdown(int i){
		
		int me = i, child = maxchild(me);

		while(child > 0 && less(me, child)) {
			swap(me, child);
			me = child;
			child = maxchild(me);
		}
	}

	/**
	 * what is the index of i's largest child? if i has no children in the 
	 * heap, -1 is returned!
	 */
	private int maxchild(int i){
		
		// left and right child indices are given by these formula:
		int left = 2 * i + 1, right = 2 * i + 2;

		// but those indices may not even be in the heap!
		if(left < heap.length()) {
			if(right < heap.length()) {
				// both children are valid, we should return the larger one
				return (less(left, right) ? right : left);
			} else {
				return left;
			}
		} else {
			// neihter child is valid, we need to return a flag
			return -1;
		}
	}

	/**
	 * sift the item at index i of the heap up to its proper place in the heap
	 * @param i the index to start sifting up from
	 */
	private void siftup(int i){

		int me = i, parent = (i - 1) / 2;
		
		while (me > 0 && less(parent, me)) {
			swap(me, parent);
			me = parent;
			parent = (me - 1) / 2;
		}
	}

	/** Exchanges the elements at position i and j of the heap
	 * @param i the item at this position will end up in position j
	 * @param j the item at this position will end up in position i
	 */
	private void swap(int i, int j) {
		Item temp = heap.get(i);
		heap.set(i, heap.get(j));
		heap.set(j, temp);
	}

	/** true iff heap[i] is less than heap[j]
	 * according to Item's compareTo() method
	 */
	private boolean less(int i, int j) {
		Item a = heap.get(i), b = heap.get(j);
		return (a.compareTo(b) < 0);
	}
}