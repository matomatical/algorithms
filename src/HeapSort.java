package com.matomatical.ads;

import java.util.Comparator;

public class HeapSort extends Sort {

	// this class should not be instantiated
	private HeapSort() {}

	/**
	 * Sorts the elements of A by their compareTo method, using heap sort
	 * @param A generic array of objects to sort
	 */
	public static <T extends Comparable<T>> void sort(T[] A) {
		sort(A, new ComparableComparator<T>());
	}

	/**
	 * Sorts the elements of A by their compareTo method, using heap sort
	 * @param A generic array of objects to sort
	 * @param comparator a comparator that can compare the objects in the array
	 */
	public static <T> void sort(T[] A, Comparator<T> comparator) {
		
		// construct heap on A
		for (int i = A.length / 2; i >= 0; i--) {
			siftdown(A, A.length, comparator, i);
		}

		// repeatedly extract max and reduce heap length (n)
		for (int n = A.length; n > 1; n--) {
			swap(A, 0, n-1);
			siftdown(A, n-1, comparator, 0);
		}
	}

	/**
	 * sift the item at index i of the heap down to its proper place in the heap
	 * @param A a generic array of objects to sort
	 * @param n the number of items in A that are part of a heap
	 * @param c a comparator that can compare the objects in the array
	 * @param i the index to start sifting down from
	 */
	private static <T> void siftdown(T[] A, int n, Comparator<T> c, int i) {
		
		int me = i, child = maxchild(A, n, c, me);

		while(child > 0 && less(A[me], A[child], c)) {
			swap(A, me, child);
			me = child;
			child = maxchild(A, n, c, me);
		}
	}

	/**
	 * what is the index of i's largest child? if i has no children in the 
	 * heap, -1 is returned!
	 * @param A a generic array of objects to sort
	 * @param n the number of items in A that are part of a heap
	 * @param c a comparator that can compare the objects in the array
	 * @param i the index to find the child of
	 * @return the index of element i's largest child, or -1 if the item has
	 * no heap children
	 */
	private static <T> int maxchild(T[] A, int n, Comparator<T> c, int i){
		
		// left and right child indices are given by these formula:
		int left = 2 * i + 1, right = 2 * i + 2;

		// but those indices may not even be in the heap!
		if(left < n) {
			if(right < n) {
				// both children are valid, we should return the larger one
				return (less(A[left], A[right], c) ? right : left);
			} else {
				return left;
			}
		} else {
			// neihter child is valid, we need to return a flag
			return -1;
		}
	}
}