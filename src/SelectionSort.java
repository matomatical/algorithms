package com.matomatical.ads;

import java.util.Comparator;

public class SelectionSort extends Sort {

	// this class should not be instantiated
	private SelectionSort() {}
	
	/**
	 * Sorts the elements of A by their compareTo method, using selection sort
	 * @param A generic array of objects to sort
	 */
	public static <T extends Comparable<T>> void sort(T[] A) {
		sort(A, new ComparableComparator<T>());
	}

	/**
	 * Sorts the elements of A by a custom comparator using selection sort
	 * @param A generic array of objects to sort
	 * @param comparator a comparator that can compare the objects in the array
	 */
	public static <T> void sort(T[] A, Comparator<T> comparator) {
		// for every slot in the array
		for(int i = 0; i < A.length; i++){
			// find the smallest item remaining ...
			int min = i;
			for(int j = i + 1; j < A.length; j++){
				if (less(A[j], A[min], comparator)) {
					min = j;
				}
			}
			// ... its correct position is right here at index i
			swap(A, i, min);
		}
	}
}