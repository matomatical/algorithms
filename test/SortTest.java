import org.junit.Assert;
import org.junit.Test;

import com.matomatical.ads.Sort;

public class SortTest {

	@Test
	public void isSortedOnSortedListShouldBeTrue() {
		Integer[] array = {1, 2, 3, 4, 6, 8, 10};
		
		boolean sorted = Sort.isSorted(array);

		Assert.assertTrue(sorted);
	}
	
	@Test
	public void isSortedOnSortedListWithDuplicatesShouldBeTrue() {
		Integer[] array = {1, 2, 3, 4, 4, 6, 8, 8, 8, 10};
		
		boolean sorted = Sort.isSorted(array);

		Assert.assertTrue(sorted);
	}
	
	@Test
	public void isSortedOnUnsortedListLastElementShouldBeFalse() {
		Integer[] array = {1, 2, 3, 4, 6, 8, 10, 0};
		
		boolean sorted = Sort.isSorted(array);

		Assert.assertFalse(sorted);
	}
	
	@Test
	public void isSortedOnUnsortedListFirstElementShouldBeFalse() {
		Integer[] array = {3, 2, 3, 4, 6, 8, 10};
		
		boolean sorted = Sort.isSorted(array);

		Assert.assertFalse(sorted);
	}

	@Test
	public void isSortedOnReverseSortedListShouldBeFalse() {
		Integer[] array = {3, 2, 1, -1};
		
		boolean sorted = Sort.isSorted(array);

		Assert.assertFalse(sorted);
	}
}