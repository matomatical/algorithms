package com.matomatical.ads;

public class Sorts {

	// this class should not be instantiated
	private Sorts() {}

	private static <T> void swap(T[] A, int i, int j) {
		T temp = A[i];
		A[i] = A[j];
		A[j] = temp;
	}

	private static <T extends Comparable<T>> boolean less(T a, T b) {
		return (a.compareTo(b) < 0);
	}

	public static <T extends Comparable<T>> boolean isSorted(T[] A) {
		// for every adjacent pair in the array
		for (int i = 1; i < A.length; i++) {
			// is this pair out of order?
			// (note: !less would fail on duplicates)
			if ( less(A[i], A[i-1])) {
				return false;
			}
		}
		// if no pairs were out of order, then we are sorted!
		return true;
	}

	public static <T extends Comparable<T>> void selectionSort(T[] A) {
		
		// for every slot in the array
		for(int i = 0; i < A.length; i++){
			// find the smallest item remaining ...
			int min = i;
			for(int j = i + 1; j < A.length; j++){
				if (less(A[j], A[min])) {
					min = j;
				}
			}
			// ... its correct position is right here at index i
			swap(A, i, min);
		}
	}

	public static <T extends Comparable<T>> void bubbleSort(T[] A) {
		// in this experimental implementation of bubble sort we're going to 
		// try to avoid using a 'changed' flag and also skip some unecessary 
		// comparisons

		// i will be the length of the section at the beginning of the array
		// which may contain out-of-order pairs
		int i = A.length;

		// we will iterate until this section vanishes (and the array is sorted)
		while (i > 0) {

			// we will track swaps with k to determine the right value of i for 
			// the next pass through the array. k begins at zero, and if no
			// swaps are made, i will become zero and we will finish early
			int k = 0;

			// work through all pairs with indices (j-1, j) in the
			// possibly-unordered section

			for (int j = 1; j < i; j++) {

				// if we encounter an unordered pair, swap it
				if (less(A[j], A[j-1])) {
					swap(A, j, j-1);

					// but also record the value of j-1 in k, because if no 
					// more swaps are made during this pass, we can be certain
					// that everything after index j-1 is already sorted
					k = j-1;
				}
			}

			// now, to prepare for the next iteration, take the new value of i
			// stored in k.
			i = k;
			// the largest this can be is the current value of i minus 1, 
			// corresponding to a swap between the last two elements (of the first i elements). This is the usual bubble sort decrement i--.

			// eventually, k will be 0 (no swaps made) and we will break out of
			// the while loop and finish the sorting algorithm.
		}
	}
}