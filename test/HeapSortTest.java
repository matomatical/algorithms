import org.junit.Assert;
import org.junit.Test;

import com.matomatical.ads.Sort;
import com.matomatical.ads.HeapSort;

public class HeapSortTest {

	@Test
	public void sortOnSortedArrayShouldSort() {
		Integer[] array = {1, 2, 3, 4, 5};

		HeapSort.sort(array);
		boolean sorted = Sort.isSorted(array);

		Assert.assertTrue(sorted);
	}
	
	@Test
	public void sortOnReverseSortedArrayShouldSort() {
		Integer[] array = {5, 4, 3, 2, 1};

		HeapSort.sort(array);
		boolean sorted = Sort.isSorted(array);

		Assert.assertTrue(sorted);
	}

	@Test
	public void sortOnUnsortedArrayShouldSort() {
		Integer[] array = {3, 2, 5, 4, 1};

		HeapSort.sort(array);
		boolean sorted = Sort.isSorted(array);

		Assert.assertTrue(sorted);
	}

	@Test
	public void sortOnArrayWithDuplicatesShouldSort() {
		Integer[] array = {3, 2, 4, 3, 5};

		HeapSort.sort(array);
		boolean sorted = Sort.isSorted(array);

		Assert.assertTrue(sorted);
	}
	
	@Test
	public void sortOnArrayWithManyDuplicatedShouldSort() {
		Integer[] array = {3, 2, 2, 2, 3, 3, 4, 6, 1, 2};

		HeapSort.sort(array);

		boolean sorted = Sort.isSorted(array);

		Assert.assertTrue(sorted);
	}
}