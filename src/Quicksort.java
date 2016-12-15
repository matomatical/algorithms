package com.matomatical.ads;

import java.util.Random;
import java.util.Comparator;

public class Quicksort extends Sort {

	// this class should not be instantiated
	private Quicksort() {}

	/**
	 * Sorts the elements of A by their compareTo method, using quicksort
	 * @param A generic array of objects to sort
	 */
	public static <T extends Comparable<T>> void sort(T[] A) {
		sort(A, new ComparableComparator<T>());
	}

	/**
	 * Sorts the elements of A by a custom comparator using quicksort
	 * @param A generic array of objects to sort
	 * @param comparator a comparator that can compare the objects in the array
	 */
	public static <T> void sort(T[] A, Comparator<T> comparator) {
		sort(A, 0, A.length, comparator);
	}

	/**
	 * Recursively sort the elements of A between indices lo and hi using 
	 * quicksort
	 * @param A generic array of objects to sort
	 * @param lo the first index in the slice of A to sort
	 * @param hi the last index (not included) in the slice of A to sort
	 * @param comparator a comparator that can compare the objects in the array
	 */
	private static <T> void sort(T[] A,
									int lo, int hi, Comparator<T> comparator) {

		// base case: less than 2 items are trivially sorted, done!
		if (hi - lo < 2) {
			return;
		}

		// recursive case: partition the array, and then sort the two sections
		// recursively
		Partition p = partition(A, lo, hi, comparator);
		sort(A, lo, p.i, comparator);
		sort(A, p.j, hi, comparator);
	}

	private static Random rng = new Random();

	/** 
	 * Partition a slice of an array into three sections by comparing against a 
	 * randomly chosen pivot element
	 * @param A generic array of objects, a slice of which will be partitioned
	 * @param lo the first index in the first slice of A to partition
	 * @param hi the last index (not included) in the second slice of A to merge
	 * @param comparator a comparator that can compare the objects in the array
	 * @return Partition object p with p.i for the index of the first equal and 
	 * p.j the first greater element of the partition performed
	 */
	private static <T> Partition partition(T[] A, 
									int lo, int hi, Comparator<T> comparator) {
		
		// choose a random pivot for a decent split (on average)
		T pivot = A[lo + rng.nextInt(hi - lo)];

		// keep track of each section
		int i = lo, j = lo, k = hi - 1;

		while (j <= k) {
			if(less(A[j], pivot, comparator)) { // A[j] < pivot
				swap(A, i++, j++);

			} else if (less(pivot, A[j], comparator)) { // A[j] > pivot
				// find an item that is not larger and swap it into next 
				// position (growing k's group of larger elements by one)

				while (less(pivot, A[k], comparator)) {
				// no need to check for j > k because at least one occurence of
				// the pivot itself is an element of the array and is either in 
				// j-1 or somewhere ahead of j. in either case, loop will stop
				// on time
					k--;
				}
				
				if (j <= k) {
					swap(A, j, k--);
				}

			} else { // A[j] == pivot				
				j++;

			}
		}

		return new Partition(i, j);
	}

	private static class Partition {
		public int i, j;
		public Partition(int i, int j) { this.i = i; this.j = j; }
	}
} 