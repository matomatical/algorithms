import org.junit.Assert;
import org.junit.Test;

import com.matomatical.ads.BinarySearcher;

public class BinarySearcherTest {
	
	@Test
	public void searchShouldReturnMinusOneIfNotFound() {
		Integer[] array = {1, 2, 3, 4, 6, 7, 8, 9};

		int i = BinarySearcher.search(array, 5);

		Assert.assertEquals(-1, i);
	}

	@Test
	public void searchShouldFindItemInList() {
		Integer[] array = {1, 2, 3, 4, 6, 7, 8, 9};

		int i = BinarySearcher.search(array, 4);

		Assert.assertEquals(3, i);
	}

	@Test
	public void searchShouldFindFirstElementOddArray() {
		Integer[] array = {1, 2, 3};

		int i = BinarySearcher.search(array, 1);

		Assert.assertEquals(0, i);
	}

	@Test
	public void searchShouldFindFirstElementEvenArray() {
		Integer[] array = {1, 2, 3, 4};

		int i = BinarySearcher.search(array, 1);

		Assert.assertEquals(0, i);
	}

	@Test
	public void searchShouldFindLastElementOddArray() {
		Integer[] array = {1, 2, 3};

		int i = BinarySearcher.search(array, 3);

		Assert.assertEquals(2, i);
	}

	@Test
	public void searchShouldFindLastElementEvenArray() {
		Integer[] array = {1, 2, 3, 4};

		int i = BinarySearcher.search(array, 4);

		Assert.assertEquals(3, i);
	}
}