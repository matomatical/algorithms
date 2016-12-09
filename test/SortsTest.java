import org.junit.Assert;
import org.junit.Test;

import com.matomatical.ads.Sorts;

public class SortsTest {

	@Test
	public void isSortedOnSortedListShouldBeTrue() {
		Integer[] array = {1, 2, 3, 4, 6, 8, 10};
		
		boolean sorted = Sorts.isSorted(array);

		Assert.assertTrue(sorted);
	}
	
	@Test
	public void isSortedOnSortedListWithDuplicatesShouldBeTrue() {
		Integer[] array = {1, 2, 3, 4, 4, 6, 8, 8, 8, 10};
		
		boolean sorted = Sorts.isSorted(array);

		Assert.assertTrue(sorted);
	}
	
	@Test
	public void isSortedOnUnsortedListLastElementShouldBeFalse() {
		Integer[] array = {1, 2, 3, 4, 6, 8, 10, 0};
		
		boolean sorted = Sorts.isSorted(array);

		Assert.assertFalse(sorted);
	}
	
	@Test
	public void isSortedOnUnsortedListFirstElementShouldBeFalse() {
		Integer[] array = {3, 2, 3, 4, 6, 8, 10};
		
		boolean sorted = Sorts.isSorted(array);

		Assert.assertFalse(sorted);
	}

	@Test
	public void isSortedOnReverseSortedListShouldBeFalse() {
		Integer[] array = {3, 2, 1, -1};
		
		boolean sorted = Sorts.isSorted(array);

		Assert.assertFalse(sorted);
	}

	@Test
	public void selectionSortOnSortedArrayShouldSort() {
		Integer[] array = {1, 2, 3, 4, 5};

		Sorts.selectionSort(array);
		boolean sorted = Sorts.isSorted(array);

		Assert.assertTrue(sorted);
	}
	
	@Test
	public void selectionSortOnReverseSortedArrayShouldSort() {
		Integer[] array = {5, 4, 3, 2, 1};

		Sorts.selectionSort(array);
		boolean sorted = Sorts.isSorted(array);

		Assert.assertTrue(sorted);
	}

	@Test
	public void selectionSortOnUnsortedArrayShouldSort() {
		Integer[] array = {3, 2, 5, 4, 1};

		Sorts.selectionSort(array);
		boolean sorted = Sorts.isSorted(array);

		Assert.assertTrue(sorted);
	}

	@Test
	public void selectionSortOnArrayWithDuplicatesShouldSort() {
		Integer[] array = {3, 2, 4, 3, 5};

		Sorts.selectionSort(array);
		boolean sorted = Sorts.isSorted(array);

		Assert.assertTrue(sorted);
	}
	
	@Test
	public void selectionSortOnArrayWithManyDuplicatedShouldSort() {
		Integer[] array = {3, 2, 2, 2, 3, 3, 4, 6, 1, 2};

		Sorts.selectionSort(array);
		boolean sorted = Sorts.isSorted(array);

		Assert.assertTrue(sorted);
	}
	
	@Test
	public void insertionSortOnSortedArrayShouldSort() {
		Integer[] array = {1, 2, 3, 4, 5};

		Sorts.insertionSort(array);
		boolean sorted = Sorts.isSorted(array);

		Assert.assertTrue(sorted);
	}
	
	@Test
	public void insertionSortOnReverseSortedArrayShouldSort() {
		Integer[] array = {5, 4, 3, 2, 1};

		Sorts.insertionSort(array);
		boolean sorted = Sorts.isSorted(array);

		Assert.assertTrue(sorted);
	}

	@Test
	public void insertionSortOnUnsortedArrayShouldSort() {
		Integer[] array = {3, 2, 5, 4, 1};

		Sorts.insertionSort(array);
		boolean sorted = Sorts.isSorted(array);

		Assert.assertTrue(sorted);
	}

	@Test
	public void insertionSortOnArrayWithDuplicatesShouldSort() {
		Integer[] array = {3, 2, 4, 3, 5};

		Sorts.insertionSort(array);
		boolean sorted = Sorts.isSorted(array);

		Assert.assertTrue(sorted);
	}
	
	@Test
	public void insertionSortOnArrayWithManyDuplicatedShouldSort() {
		Integer[] array = {3, 2, 2, 2, 3, 3, 4, 6, 1, 2};

		Sorts.insertionSort(array);
		boolean sorted = Sorts.isSorted(array);

		Assert.assertTrue(sorted);
	}

	@Test
	public void bubbleSortOnSortedArrayShouldSort() {
		Integer[] array = {1, 2, 3, 4, 5};

		Sorts.bubbleSort(array);
		boolean sorted = Sorts.isSorted(array);

		Assert.assertTrue(sorted);
	}
	
	@Test
	public void bubbleSortOnReverseSortedArrayShouldSort() {
		Integer[] array = {5, 4, 3, 2, 1};

		Sorts.bubbleSort(array);
		boolean sorted = Sorts.isSorted(array);

		Assert.assertTrue(sorted);
	}

	@Test
	public void bubbleSortOnUnsortedArrayShouldSort() {
		Integer[] array = {3, 2, 5, 4, 1};

		Sorts.bubbleSort(array);
		boolean sorted = Sorts.isSorted(array);

		Assert.assertTrue(sorted);
	}

	@Test
	public void bubbleSortOnArrayWithDuplicatesShouldSort() {
		Integer[] array = {3, 2, 4, 3, 5};

		Sorts.bubbleSort(array);
		boolean sorted = Sorts.isSorted(array);

		Assert.assertTrue(sorted);
	}
	
	@Test
	public void bubbleSortOnArrayWithManyDuplicatedShouldSort() {
		Integer[] array = {3, 2, 2, 2, 3, 3, 4, 6, 1, 2};

		Sorts.bubbleSort(array);

		boolean sorted = Sorts.isSorted(array);

		Assert.assertTrue(sorted);
	}
}