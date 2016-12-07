import org.junit.Assert;
import org.junit.Test;

import com.matomatical.ads.BinarySearcher;

public class BinarySearcherTest {
	
	// helper class for comparing integers during tests
	class IntegerComparer implements BinarySearcher.Comparer<Integer> {
		public int compare(Integer a, Integer b){
			return a.compareTo(b);
		}
	}
	private IntegerComparer comparer = new IntegerComparer();

	@Test
	public void searchShouldReturnMinusOneIfNotFound() {
		Integer[] array = {1, 2, 3, 4, 6, 7, 8, 9};

		int i = BinarySearcher.search(array, 5, comparer);

		Assert.assertEquals(-1, i);
	}

	@Test
	public void searchShouldFindItemInList() {
		Integer[] array = {1, 2, 3, 4, 6, 7, 8, 9};

		int i = BinarySearcher.search(array, 4, comparer);

		Assert.assertEquals(3, i);
	}

	@Test
	public void searchShouldFindFirstElementOddArray() {
		Integer[] array = {1, 2, 3};

		int i = BinarySearcher.search(array, 1, comparer);

		Assert.assertEquals(0, i);
	}

	@Test
	public void searchShouldFindFirstElementEvenArray() {
		Integer[] array = {1, 2, 3, 4};

		int i = BinarySearcher.search(array, 1, comparer);

		Assert.assertEquals(0, i);
	}

	@Test
	public void searchShouldFindLastElementOddArray() {
		Integer[] array = {1, 2, 3};

		int i = BinarySearcher.search(array, 3, comparer);

		Assert.assertEquals(2, i);
	}

	@Test
	public void searchShouldFindLastElementEvenArray() {
		Integer[] array = {1, 2, 3, 4};

		int i = BinarySearcher.search(array, 4, comparer);

		Assert.assertEquals(3, i);
	}

}