package com.matomatical.ads;

import java.util.Random;
import java.util.Comparator;

public class SimpleSorts {

	// this class should not be instantiated
	private SimpleSorts() {}

	/**
	 * Checks whether the elements of A are sorted by a custom comparator
	 * @param A a generic array of objects to check
	 * @param comparator a comparator that can compare the objects in the array
	 * @return true iff the objects are in ascending order according to 
	 * their compareTo() methods
	 */
	public static <T> boolean isSorted(T[] A, Comparator<T> comparator) {
		// for every adjacent pair in the array
		for (int i = 1; i < A.length; i++) {
			// is this pair out of order?
			// (note: !less would fail on duplicates)
			if ( less(A[i], A[i-1], comparator)) {
				return false;
			}
		}
		// if no pairs were out of order, then we are sorted!
		return true;
	}
	
	/**
	 * Checks whether the elements of A are sorted by their compareTo method
	 * @param A generic array of objects to check
	 * @return true iff the objects are in ascending order according to 
	 * their compareTo() methods
	 */
	public static <T extends Comparable<T>> boolean isSorted(T[] A) {
		return isSorted(A, new ComparableComparator<T>());
	}



	/**
	 * Sorts the elements of A by a custom comparator using selection sort
	 * @param A generic array of objects to sort
	 * @param comparator a comparator that can compare the objects in the array
	 */
	public static <T> void selectionSort(T[] A, Comparator<T> comparator) {
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

	/**
	 * Sorts the elements of A by their compareTo method, using selection sort
	 * @param A generic array of objects to sort
	 */
	public static <T extends Comparable<T>> void selectionSort(T[] A) {
		selectionSort(A, new ComparableComparator<T>());
	}



	/**
	 * Sorts the elements of A by a custom comparator using insertion sort
	 * @param A generic array of objects to sort
	 * @param comparator a comparator that can compare the objects in the array
	 */
	public static <T> void insertionSort(T[] A, Comparator<T> comparator) {
		// for every item in the array
		for(int i = 1; i < A.length; i++){
			
			// pass through the first i-1 items looking for the right place
			for(int j = i - 1; j >= 0; j--){
				
				if (less(A[j+1], A[j], comparator)) {
					// as long as the item is above its correct place,
					// continue to swap the item along the array
					swap(A, j, j+1);
				} else {
					// when we find something the item isnt smaller than,
					// we can finish inserting early
					break;
				}
			}
		}
	}

	/**
	 * Sorts the elements of A by their compareTo method, using insertion sort
	 * @param A generic array of objects to sort
	 */
	public static <T extends Comparable<T>> void insertionSort(T[] A) {
		insertionSort(A, new ComparableComparator<T>());
	}



	// i thought of this really cool improvement over the basic bubble sort
	// implementation.
	// It's a way to avoid using a 'changed' flag and also skip some unecessary
	// comparisons while still finishing early.
	//
	// While implementing, I wrote a lot of comments to crystalise my thought 
	// process, and I've left them in so now the method has more comments than
	// code.
	// After implementing and testing the method, I looked it up to see if it
	// has been done before (and of course it has). Wikipedia reports that the 
	// improvement results in "about a worst case 50% improvement in comparison
	// count (though no improvement in swap counts)", which makes sense!
	/**
	 * Sorts the elements of A by a custom comparator using bubble sort
	 * @param A generic array of objects to sort
	 * @param comparator a comparator that can compare the objects in the array
	 */
	public static <T> void bubbleSort(T[] A, Comparator<T> comparator) {
		// `i` will be the length of the section at the beginning of the array
		// which may contain out-of-order pairs
		int i = A.length;

		// we will iterate until this section vanishes (and the array is sorted)
		// actually, we can finish as soon as it is 1 because the first element 
		// is trivially sorted
		while (i > 1) {

			// we will track swaps with `k` to determine the right value of `i`
			// for the next pass through the array. `k` begins at zero, and if 
			// no swaps are made `i` will become zero and we will finish early!
			int k = 0;

			// work through all pairs with indices (`j-1`, `j`) in the
			// possibly-unordered section, up to and including (i-2, i-1);
			for (int j = 1; j < i; j++) {

				// if we encounter an unordered pair, swap it
				if (less(A[j], A[j-1], comparator)) {
					swap(A, j, j-1);

					// but also record the value of `j` in `k`, because if no 
					// more swaps are made during this pass, we can be certain
					// that everything after index `j-1` is already sorted!
					k = j;
				}
			}

			// now, to prepare for the next iteration, set the new value of `i`
			i = k;

			// the largest this can be is the current value of `i`, minus 1, 
			// corresponding to a swap between the last two elements (of the 
			// first `i` elements). This is the usual bubble sort decrement 
			// `i--`.

			// therefore, eventually `k` will be 1 or 0 and we will break out 
			// of the while loop and finish the sorting algorithm.
		}
	}

	/**
	 * Sorts the elements of A by their compareTo method, using bubble sort
	 * @param A generic array of objects to sort
	 */
	public static <T extends Comparable<T>> void bubbleSort(T[] A) {
		bubbleSort(A, new ComparableComparator<T>());
	}



	private static Random rng = new Random();

	/** Reorders the contents of A with approximate randomness
	 * (just using java.util.Random with default seed)
	 * @param A generic array of objects to shuffle
	 */
	public static <T> void shuffle(T[] A){
		for (int i = 1; i < A.length; i++){
			// pick a position between 0 and i inclusive
			int r = rng.nextInt(i+1);
			
			// swap into that position
			swap(A, i, r);
		}
	}



	/** Exchanges the elements at position i and j of array A
	 * @param A a generic array of objects
	 * @param i the object at this position will end up in position j
	 * @param j the object at this position will end up in position i
	 */
	private static <T> void swap(T[] A, int i, int j) {
		T temp = A[i];
		A[i] = A[j];
		A[j] = temp;
	}


	/**
	 * Nested class that wraps a Comparable Type T's compareTo() method in a
	 * Comparator object.
	 */
	private static class ComparableComparator<T extends Comparable<T>> 
	implements Comparator<T> {
		// Using this class to treat Comparable sorting methods as Comparator 
		// sorts enables lower duplication of code throughout this class. in
		// particular, each algorithm is only implemented once, though it is
		// accessible through more than one method
		public int compare(T a, T b){
			return a.compareTo(b);
		}
	}
	
	/** true iff a is less than b according to comparator's compare() method */
	private static <T> boolean less(T a, T b, Comparator<T> comparator) {
		return (comparator.compare(a, b) < 0);
	}
}