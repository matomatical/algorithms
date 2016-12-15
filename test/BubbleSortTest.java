import org.junit.Assert;
import org.junit.Test;

import com.matomatical.ads.Sort;
import com.matomatical.ads.BubbleSort;

public class BubbleSortTest {
	
	@Test
	public void bubbleSortOnSortedArrayShouldSort() {
		Integer[] array = {1, 2, 3, 4, 5};

		BubbleSort.sort(array);
		boolean sorted = Sort.isSorted(array);

		Assert.assertTrue(sorted);
	}
	
	@Test
	public void bubbleSortOnReverseSortedArrayShouldSort() {
		Integer[] array = {5, 4, 3, 2, 1};

		BubbleSort.sort(array);
		boolean sorted = Sort.isSorted(array);

		Assert.assertTrue(sorted);
	}

	@Test
	public void bubbleSortOnUnsortedArrayShouldSort() {
		Integer[] array = {3, 2, 5, 4, 1};

		BubbleSort.sort(array);
		boolean sorted = Sort.isSorted(array);

		Assert.assertTrue(sorted);
	}

	@Test
	public void bubbleSortOnArrayWithDuplicatesShouldSort() {
		Integer[] array = {3, 2, 4, 3, 5};

		BubbleSort.sort(array);
		boolean sorted = Sort.isSorted(array);

		Assert.assertTrue(sorted);
	}
	
	@Test
	public void bubbleSortOnArrayWithManyDuplicatedShouldSort() {
		Integer[] array = {3, 2, 2, 2, 3, 3, 4, 6, 1, 2};

		BubbleSort.sort(array);
		boolean sorted = Sort.isSorted(array);

		Assert.assertTrue(sorted);
	}
}