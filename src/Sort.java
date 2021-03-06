package com.matomatical.ads;

import java.util.Random;
import java.util.Comparator;

public class Sort {

	// this class should not be instantiated
	private Sort() {}

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
	public static <T> void swap(T[] A, int i, int j) {
		T temp = A[i];
		A[i] = A[j];
		A[j] = temp;
	}

	/** true iff a is less than b according to comparator's compare() method */
	public static <T> boolean less(T a, T b, Comparator<T> comparator) {
		return (comparator.compare(a, b) < 0);
	}

	/** true iff a is less than b according to comparator's compare() method */
	public static <T extends Comparable<T>> boolean less(T a, T b) {
		return (a.compareTo(b) < 0);
	}

	/* Wrap a Comparable Type T's compareTo() method in a Comparator object. */
	public static class ComparableComparator<T extends Comparable<T>> 
	implements Comparator<T> {
		public int compare(T a, T b){
			return a.compareTo(b);
		}
	}
}