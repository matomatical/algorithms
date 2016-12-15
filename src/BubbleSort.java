package com.matomatical.ads;

import java.util.Comparator;

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
public class BubbleSort extends Sort {

	// this class should not be instantiated
	private BubbleSort() {}
	
	/**
	 * Sorts the elements of A by their compareTo method, using bubble sort
	 * @param A generic array of objects to sort
	 */
	public static <T extends Comparable<T>> void bubbleSort(T[] A) {
		bubbleSort(A, new ComparableComparator<T>());
	}

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
}