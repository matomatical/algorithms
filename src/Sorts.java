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
		
		// make one pass for each element in the array
		for(int i = A.length; i > 0; i--){
			// track whether we make any changes
			boolean changed = false;

			// for each pair in the possibly unsorted section,
			// swap it (and record a change) if out of order 
			for(int j = 1; j < i; j++){

				if (less(A[j], A[j-1])) {
					swap(A, j, j-1);
					changed = true;
				}
			}

			// if there were no changes, we can finish early
			if (changed == false){
				break;
			}
		}
	}
}