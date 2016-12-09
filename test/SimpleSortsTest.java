import org.junit.Assert;
import org.junit.Test;

import com.matomatical.ads.SimpleSorts;

public class SimpleSortsTest {

	@Test
	public void isSortedOnSortedListShouldBeTrue() {
		Integer[] array = {1, 2, 3, 4, 6, 8, 10};
		
		boolean sorted = SimpleSorts.isSorted(array);

		Assert.assertTrue(sorted);
	}
	
	@Test
	public void isSortedOnSortedListWithDuplicatesShouldBeTrue() {
		Integer[] array = {1, 2, 3, 4, 4, 6, 8, 8, 8, 10};
		
		boolean sorted = SimpleSorts.isSorted(array);

		Assert.assertTrue(sorted);
	}
	
	@Test
	public void isSortedOnUnsortedListLastElementShouldBeFalse() {
		Integer[] array = {1, 2, 3, 4, 6, 8, 10, 0};
		
		boolean sorted = SimpleSorts.isSorted(array);

		Assert.assertFalse(sorted);
	}
	
	@Test
	public void isSortedOnUnsortedListFirstElementShouldBeFalse() {
		Integer[] array = {3, 2, 3, 4, 6, 8, 10};
		
		boolean sorted = SimpleSorts.isSorted(array);

		Assert.assertFalse(sorted);
	}

	@Test
	public void isSortedOnReverseSortedListShouldBeFalse() {
		Integer[] array = {3, 2, 1, -1};
		
		boolean sorted = SimpleSorts.isSorted(array);

		Assert.assertFalse(sorted);
	}

	@Test
	public void selectionSortOnSortedArrayShouldSort() {
		Integer[] array = {1, 2, 3, 4, 5};

		SimpleSorts.selectionSort(array);
		boolean sorted = SimpleSorts.isSorted(array);

		Assert.assertTrue(sorted);
	}
	
	@Test
	public void selectionSortOnReverseSortedArrayShouldSort() {
		Integer[] array = {5, 4, 3, 2, 1};

		SimpleSorts.selectionSort(array);
		boolean sorted = SimpleSorts.isSorted(array);

		Assert.assertTrue(sorted);
	}

	@Test
	public void selectionSortOnUnsortedArrayShouldSort() {
		Integer[] array = {3, 2, 5, 4, 1};

		SimpleSorts.selectionSort(array);
		boolean sorted = SimpleSorts.isSorted(array);

		Assert.assertTrue(sorted);
	}

	@Test
	public void selectionSortOnArrayWithDuplicatesShouldSort() {
		Integer[] array = {3, 2, 4, 3, 5};

		SimpleSorts.selectionSort(array);
		boolean sorted = SimpleSorts.isSorted(array);

		Assert.assertTrue(sorted);
	}
	
	@Test
	public void selectionSortOnArrayWithManyDuplicatedShouldSort() {
		Integer[] array = {3, 2, 2, 2, 3, 3, 4, 6, 1, 2};

		SimpleSorts.selectionSort(array);
		boolean sorted = SimpleSorts.isSorted(array);

		Assert.assertTrue(sorted);
	}
	
	@Test
	public void insertionSortOnSortedArrayShouldSort() {
		Integer[] array = {1, 2, 3, 4, 5};

		SimpleSorts.insertionSort(array);
		boolean sorted = SimpleSorts.isSorted(array);

		Assert.assertTrue(sorted);
	}
	
	@Test
	public void insertionSortOnReverseSortedArrayShouldSort() {
		Integer[] array = {5, 4, 3, 2, 1};

		SimpleSorts.insertionSort(array);
		boolean sorted = SimpleSorts.isSorted(array);

		Assert.assertTrue(sorted);
	}

	@Test
	public void insertionSortOnUnsortedArrayShouldSort() {
		Integer[] array = {3, 2, 5, 4, 1};

		SimpleSorts.insertionSort(array);
		boolean sorted = SimpleSorts.isSorted(array);

		Assert.assertTrue(sorted);
	}

	@Test
	public void insertionSortOnArrayWithDuplicatesShouldSort() {
		Integer[] array = {3, 2, 4, 3, 5};

		SimpleSorts.insertionSort(array);
		boolean sorted = SimpleSorts.isSorted(array);

		Assert.assertTrue(sorted);
	}
	
	@Test
	public void insertionSortOnArrayWithManyDuplicatedShouldSort() {
		Integer[] array = {3, 2, 2, 2, 3, 3, 4, 6, 1, 2};

		SimpleSorts.insertionSort(array);
		boolean sorted = SimpleSorts.isSorted(array);

		Assert.assertTrue(sorted);
	}

	@Test
	public void bubbleSortOnSortedArrayShouldSort() {
		Integer[] array = {1, 2, 3, 4, 5};

		SimpleSorts.bubbleSort(array);
		boolean sorted = SimpleSorts.isSorted(array);

		Assert.assertTrue(sorted);
	}
	
	@Test
	public void bubbleSortOnReverseSortedArrayShouldSort() {
		Integer[] array = {5, 4, 3, 2, 1};

		SimpleSorts.bubbleSort(array);
		boolean sorted = SimpleSorts.isSorted(array);

		Assert.assertTrue(sorted);
	}

	@Test
	public void bubbleSortOnUnsortedArrayShouldSort() {
		Integer[] array = {3, 2, 5, 4, 1};

		SimpleSorts.bubbleSort(array);
		boolean sorted = SimpleSorts.isSorted(array);

		Assert.assertTrue(sorted);
	}

	@Test
	public void bubbleSortOnArrayWithDuplicatesShouldSort() {
		Integer[] array = {3, 2, 4, 3, 5};

		SimpleSorts.bubbleSort(array);
		boolean sorted = SimpleSorts.isSorted(array);

		Assert.assertTrue(sorted);
	}
	
	@Test
	public void bubbleSortOnArrayWithManyDuplicatedShouldSort() {
		Integer[] array = {3, 2, 2, 2, 3, 3, 4, 6, 1, 2};

		SimpleSorts.bubbleSort(array);

		boolean sorted = SimpleSorts.isSorted(array);

		Assert.assertTrue(sorted);
	}
}