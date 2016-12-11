package com.matomatical.ads;

public class HeapSort {

	// this class should not be instantiated
	private HeapSort() {}

	/**
	 * Sorts the elements of A by their compareTo method, using heap sort
	 * this sort is not in place. instead it creates a copy of the array A
	 * to use inside a priority queue (for simplicity of implementation)
	 * @param A generic array of objects to sort
	 */
	public static <T extends Comparable<T>> void sort(T[] A) {
		PriorityQueue<T> pq = new PriorityQueue<T>(A);

		for (int i = A.length - 1; i >= 0; i--) {
			A[i] = pq.removeMax();
		}
	}
}