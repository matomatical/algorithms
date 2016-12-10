package com.matomatical.ads;

import java.util.Comparator;

public class MergeSort {

	// this class should not be instantiated
	private MergeSort() {}

	/**
	 * Sorts the elements of A by their compareTo method, using merge sort
	 * @param A generic array of objects to sort
	 */
	public static <T extends Comparable<T>> void sort(T[] A) {
		sort(A, new SimpleSorts.ComparableComparator<T>());
	}

	/**
	 * Sorts the elements of A by a custom comparator using merge sort
	 * @param A generic array of objects to sort
	 * @param comparator a comparator that can compare the objects in the array
	 */
	public static <T> void sort(T[] A, Comparator<T> comparator) {

		@SuppressWarnings("unchecked")
		T[] B = (T[]) new Comparable[A.length];

		sort(A, 0, A.length, comparator, B);
	}

	/**
	 * Recursively sort the elements of A between indices lo and hi using merge
	 * sort
	 * @param A generic array of objects to sort
	 * @param lo the first index in the slice of A to sort
	 * @param hi the last index (not included) in the slice of A to sort
	 * @param comparator a comparator that can compare the objects in the array
	 * @param B array of extra space for performing the merge
	 */
	private static <T> void sort(T[] A,
			int lo, int hi, Comparator<T> comparator, T[] B) {

		// base case: less than 2 items are trivially sorted, done!
		if (hi - lo < 2) {
			return;
		}

		// recursive case: we can sort two halves recursively, then merge to
		// sort this slice
		int mid = (lo + hi) / 2;
		sort(A, lo, mid, comparator, B);
		sort(A, mid, hi, comparator, B);
		merge(A, lo, mid, hi, comparator, B);
	}

	/** 
	 * Merges the (sorted) elements of A between lo and mid with those between 
	 * mid and hi, using B as working space
	 * @param A generic array of objects, a slice of which will be merged
	 * @param lo the first index in the first slice of A to merge
	 * @param mid the last index (not included) of the first slice to merge,
	 * 	also the first index of the second slice to merge
	 * @param hi the last index (not included) in the second slice of A to merge
	 * @param comparator a comparator that can compare the objects in the array
	 * @param B array of extra space for performing the merge (between lo and hi
	 * 	may be used)
	 */
	private static <T> void merge(T[] A,
			int lo, int mid, int hi, Comparator<T> comparator, T[] B) {
		
		// track positions in 1st slice, 2nd slice of A, and B (respectively)
		int i = lo, j = mid, k = lo;

		// take the smaller element and advance relevant pointer
		while(i < mid && j < hi){
			// break ties with lo side for stability:
			// only take hi side if strictly less
			if(less(A[j], A[i], comparator)) {
				B[k++] = A[j++];
			} else {
				B[k++] = A[i++];
			}
		}
		
		// There may be some left-over lo-side elements if we ran out of hi-side
		// elements first. We will need to copy them across:
		while(i < mid) {
			B[k++] = A[i++];
		}

		// however, if it's the other way around, we won't need to copy across 
		// any remaining hi-side elements, since we'd just copy them straight 
		// back into A in the next step anyway!
		// the code would be: while(j < hi) { B[k++] = A[j++]; }

		// in the end we need to take the items we copied out of B and into
		// their places in A
		for(int l = lo; l < k; l++){
			A[l] = B[l];
		}
	}

	/** true iff a is less than b according to comparator's compare() method */
	private static <T> boolean less(T a, T b, Comparator<T> comparator) {
		return (comparator.compare(a, b) < 0);
	}
}