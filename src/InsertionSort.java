package com.matomatical.ads;

import java.util.Comparator;

public class InsertionSort {

	// this class should not be instantiated
	private InsertionSort() {}
	
	/**
	 * Sorts the elements of A by their compareTo method, using insertion sort
	 * @param A generic array of objects to sort
	 */
	public static <T extends Comparable<T>> void sort(T[] A) {
		sort(A, new Sort.ComparableComparator<T>());
	}

	/**
	 * Sorts the elements of A by a custom comparator using insertion sort
	 * @param A generic array of objects to sort
	 * @param comparator a comparator that can compare the objects in the array
	 */
	public static <T> void sort(T[] A, Comparator<T> comparator) {
		// for every item in the array
		for(int i = 1; i < A.length; i++){
			
			// pass through the first i-1 items looking for the right place
			for(int j = i - 1; j >= 0; j--){
				
				if (Sort.less(A[j+1], A[j], comparator)) {
					// as long as the item is above its correct place,
					// continue to swap the item along the array
					Sort.swap(A, j, j+1);
				} else {
					// when we find something the item isnt smaller than,
					// we can finish inserting early
					break;
				}
			}
		}
	}
}