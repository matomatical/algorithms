import org.junit.Assert;
import org.junit.Test;

import com.matomatical.ads.Sort;
import com.matomatical.ads.InsertionSort;

public class InsertionSortTest {
		
	@Test
	public void insertionSortOnSortedArrayShouldSort() {
		Integer[] array = {1, 2, 3, 4, 5};

		InsertionSort.sort(array);
		boolean sorted = Sort.isSorted(array);

		Assert.assertTrue(sorted);
	}
	
	@Test
	public void insertionSortOnReverseSortedArrayShouldSort() {
		Integer[] array = {5, 4, 3, 2, 1};

		InsertionSort.sort(array);
		boolean sorted = Sort.isSorted(array);

		Assert.assertTrue(sorted);
	}

	@Test
	public void insertionSortOnUnsortedArrayShouldSort() {
		Integer[] array = {3, 2, 5, 4, 1};

		InsertionSort.sort(array);
		boolean sorted = Sort.isSorted(array);

		Assert.assertTrue(sorted);
	}

	@Test
	public void insertionSortOnArrayWithDuplicatesShouldSort() {
		Integer[] array = {3, 2, 4, 3, 5};

		InsertionSort.sort(array);
		boolean sorted = Sort.isSorted(array);

		Assert.assertTrue(sorted);
	}
	
	@Test
	public void insertionSortOnArrayWithManyDuplicatedShouldSort() {
		Integer[] array = {3, 2, 2, 2, 3, 3, 4, 6, 1, 2};

		InsertionSort.sort(array);
		boolean sorted = Sort.isSorted(array);

		Assert.assertTrue(sorted);
	}
}