import org.junit.Assert;
import org.junit.Test;

import java.util.Comparator;

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

	@Test
	public void swapShouldSafelyExchangeTwoElements() {
		Integer first = 103;
		Integer second = 11;
		Integer[] array = {0, first, second, 3};
		
		Sort.swap(array, 1, 2);

		Assert.assertEquals(first, array[2]);
		Assert.assertEquals(second, array[1]);
	}

	@Test
	public void swapShouldSafelyExchangeSameElement() {
		Integer element = 100;
		Integer[] array = {element, 1, 2, 3};
		
		Sort.swap(array, 0, 0);

		Assert.assertEquals(element, array[0]);
	}

	@Test
	public void swapShouldSafelyExchangeTwoElementsBackwards() {
		Integer first = 103;
		Integer second = 11;
		Integer[] array = {0, first, second, 3};
		
		Sort.swap(array, 2, 1);

		Assert.assertEquals(first, array[2]);
		Assert.assertEquals(second, array[1]);
	}

	@Test
	public void swapShouldNotChangeOtherElements() {
		Integer first = 103;
		Integer second = 11;
		Integer[] array = {0, first, second, 3};
		
		Sort.swap(array, 0, 3);

		Assert.assertEquals(first, array[1]);
		Assert.assertEquals(second, array[2]);
	}

	@Test
	public void lessShouldAcceptOrderedItemsAccordingToCompareTo() {
		Integer big = 100, small = 1;

		boolean less = Sort.less(small, big);

		Assert.assertTrue(less);
	}

	@Test
	public void lessShouldRejectUnorderedItemsAccordingToCompareTo() {
		Integer big = 100, small = 1;

		boolean less = Sort.less(big, small);

		Assert.assertFalse(less);
	}

	@Test
	public void lessShouldRejectEqualItemsAccordingToCompareTo() {
		Integer one = 9, two = 9;

		boolean less = Sort.less(one, two);

		Assert.assertFalse(less);
	}


	// compare based on length of each string
	private static class MockStringComparator implements Comparator<String> {
		public int compare(String a, String b){
			return a.length() - b.length();
		}
	}

	@Test
	public void comparatorLessShouldAcceptOrderedItems() {
		String small = "..", large = "hello, world!";
		Comparator<String> comparator = new MockStringComparator();

		boolean less = Sort.less(small, large, comparator);

		Assert.assertTrue(less);
	}

	@Test
	public void comparatorLessShouldRejectUnorderedItems() {
		String small = "..", large = "hello, world!";
		Comparator<String> comparator = new MockStringComparator();

		boolean less = Sort.less(large, small, comparator);

		Assert.assertFalse(less);
	}
	
	@Test
	public void comparatorLessShouldRejectEqualItems() {
		String one = "same...", two = "length!";
		Comparator<String> comparator = new MockStringComparator();

		boolean less = Sort.less(one, two, comparator);

		Assert.assertFalse(less);
	}
}