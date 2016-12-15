import org.junit.Assert;
import org.junit.Test;

import com.matomatical.ads.Sort;
import com.matomatical.ads.SelectionSort;

public class SelectionSortTest {

	@Test
	public void selectionSortOnSortedArrayShouldSort() {
		Integer[] array = {1, 2, 3, 4, 5};

		SelectionSort.sort(array);
		boolean sorted = Sort.isSorted(array);

		Assert.assertTrue(sorted);
	}
	
	@Test
	public void selectionSortOnReverseSortedArrayShouldSort() {
		Integer[] array = {5, 4, 3, 2, 1};

		SelectionSort.sort(array);
		boolean sorted = Sort.isSorted(array);

		Assert.assertTrue(sorted);
	}

	@Test
	public void selectionSortOnUnsortedArrayShouldSort() {
		Integer[] array = {3, 2, 5, 4, 1};

		SelectionSort.sort(array);
		boolean sorted = Sort.isSorted(array);

		Assert.assertTrue(sorted);
	}

	@Test
	public void selectionSortOnArrayWithDuplicatesShouldSort() {
		Integer[] array = {3, 2, 4, 3, 5};

		SelectionSort.sort(array);
		boolean sorted = Sort.isSorted(array);

		Assert.assertTrue(sorted);
	}
	
	@Test
	public void selectionSortOnArrayWithManyDuplicatedShouldSort() {
		Integer[] array = {3, 2, 2, 2, 3, 3, 4, 6, 1, 2};

		SelectionSort.sort(array);
		boolean sorted = Sort.isSorted(array);

		Assert.assertTrue(sorted);
	}
}