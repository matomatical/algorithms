package com.matomatical.ads;

public class BinarySearcher {

	public static int search(Comparable[] array, Comparable key){
		int lo = 0;
		int hi = array.length - 1;

		while(hi >= lo) {
			
			int mid = (lo + hi) / 2;
			int compare = key.compareTo(array[mid]);

			if(compare < 0){
				// key is below array[mid] so we can throw out the high half
				hi = mid - 1;

			} else if (compare > 0) {
				// key is above array[mid] so we can throw out the low half
				lo = mid + 1;

			} else {
				// key is exactly array[mid]! we can return this index
				return mid;
			}
		}

		// if hi passes lo, key is not in the array so return -1
		return -1;
	}
}

/* example

array:
0 1 2 3 4 5 6 7 8 9
a b c d e f h i j k

key: g

--- preparation ---

lo = 0
hi = array.length - 1 = 9

--- iteration 1 ---

mid = (0+9)/2 = 4

v      mid        V
0 1 2 3 4 5 6 7 8 9
a b c d e f h i j k

'g'.compareTo('e') > 0
so lo = mid + 1 = 5

          v       V
0 1 2 3 4 5 6 7 8 9
a b c d e f h i j k

--- iteration 2 ---

mid = (5+9)/2 = 7

          v  mid  V
0 1 2 3 4 5 6 7 8 9
a b c d e f h i j k

'g'.compareTo('i') < 0
so hi = mid - 1 = 6

          v V
0 1 2 3 4 5 6 7 8 9
a b c d e f h i j k

--- iteration 3 ---

mid = (5+6)/2 = 5

          v V
         mid
0 1 2 3 4 5 6 7 8 9
a b c d e f h i j k

'g'.compareTo('f') > 0
so lo = mid + 1 = 6

            v
            V
0 1 2 3 4 5 6 7 8 9
a b c d e f h i j k

--- iteration 4 ---

mid = (6+6)/2 = 6

            v
            V
           mid
0 1 2 3 4 5 6 7 8 9
a b c d e f h i j k

'g'.compareTo('h') < 0
so hi = mid - 1 = 5

          V v
0 1 2 3 4 5 6 7 8 9
a b c d e f h i j k

--- iteration ends ---

return -1

*/