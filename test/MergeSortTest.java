import org.junit.Assert;
import org.junit.Test;

import com.matomatical.ads.SimpleSorts;
import com.matomatical.ads.MergeSort;

public class MergeSortTest {

	@Test
	public void sortOnSortedArrayShouldSort() {
		Integer[] array = {1, 2, 3, 4, 5};

		MergeSort.sort(array);
		boolean sorted = SimpleSorts.isSorted(array);

		Assert.assertTrue(sorted);
	}
	
	@Test
	public void sortOnReverseSortedArrayShouldSort() {
		Integer[] array = {5, 4, 3, 2, 1};

		MergeSort.sort(array);
		boolean sorted = SimpleSorts.isSorted(array);

		Assert.assertTrue(sorted);
	}

	@Test
	public void sortOnUnsortedArrayShouldSort() {
		Integer[] array = {3, 2, 5, 4, 1};

		MergeSort.sort(array);
		boolean sorted = SimpleSorts.isSorted(array);

		Assert.assertTrue(sorted);
	}

	@Test
	public void sortOnArrayWithDuplicatesShouldSort() {
		Integer[] array = {3, 2, 4, 3, 5};

		MergeSort.sort(array);
		boolean sorted = SimpleSorts.isSorted(array);

		Assert.assertTrue(sorted);
	}
	
	@Test
	public void sortOnArrayWithManyDuplicatedShouldSort() {
		Integer[] array = {3, 2, 2, 2, 3, 3, 4, 6, 1, 2};

		MergeSort.sort(array);

		boolean sorted = SimpleSorts.isSorted(array);

		Assert.assertTrue(sorted);
	}
}