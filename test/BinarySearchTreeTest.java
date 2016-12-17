import org.junit.Assert;
import org.junit.Test;

import com.matomatical.ads.BinarySearchTree;
import com.matomatical.ads.NotFoundException;

public class BinarySearchTreeTest {

	@Test
	public void insertThenSearchShoudlReturnValue() {
		BinarySearchTree<String, String> bst =
			new BinarySearchTree<String, String>();
		
		bst.insert("hello,", "world!");
		String value = bst.search("hello,");

		Assert.assertEquals("world!", value);
	}

	@Test
	public void insertSeveralThenSearchShoudlReturnFirstValue() {
		BinarySearchTree<String, String> bst =
			new BinarySearchTree<String, String>();
		
		bst.insert("hello,", "world!");
		bst.insert("matt", "f");
		bst.insert("day", "sunday");
		String value = bst.search("hello,");

		Assert.assertEquals("world!", value);
	}

	@Test
	public void insertSeveralThenSearchShoudlReturnLaterValue() {
		BinarySearchTree<String, String> bst =
			new BinarySearchTree<String, String>();
		
		bst.insert("hello,", "world!");
		bst.insert("matt", "f");
		bst.insert("day", "sunday");
		String value = bst.search("day");

		Assert.assertEquals("sunday", value);
	}

	@Test
	public void insertExistingShouldUpdateSearchValue() {
		BinarySearchTree<String, String> bst =
			new BinarySearchTree<String, String>();
		bst.insert("hello,", "world!");

		bst.insert("hello,", "matt!");
		String value = bst.search("hello,");

		Assert.assertEquals("matt!", value);
	}

	@Test
	public void insertNullShouldSucceed() {
		BinarySearchTree<String, String> bst =
			new BinarySearchTree<String, String>();
		
		bst.insert("hello,", null);

		// pass if no exception was thrown
	}

	@Test
	public void insertExistingInLargeTreeShouldUpdateSearchValue() {
		BinarySearchTree<String, String> bst =
			new BinarySearchTree<String, String>();
		bst.insert("abc", "def"); bst.insert("1", "2"); bst.insert("2", "3"); 
		bst.insert("more", "nodes"); bst.insert("hi", "there");

		bst.insert("hello,", "world!");
		bst.insert("hello,", "matt!");

		String value = bst.search("hello,");

		Assert.assertEquals("matt!", value);
	}

	@Test
	public void searchForKeyWithNullValueShouldReturnNull() {
		BinarySearchTree<String, String> bst =
			new BinarySearchTree<String, String>();
		
		bst.insert("hello,", null);
		String value = bst.search("hello,");

		Assert.assertTrue(null == value);
	}

	@Test(expected = NotFoundException.class)
	public void searchOnEmptyTreeShouldThrowException() {
		BinarySearchTree<String, String> bst =
			new BinarySearchTree<String, String>();
		
		bst.search("hello!");

		Assert.fail("missing NotFoundException!");
	}

	@Test(expected = NotFoundException.class)
	public void searchOnMissingKeyShouldThrowException() {
		BinarySearchTree<String, String> bst =
			new BinarySearchTree<String, String>();
		bst.insert("hello,","world!");

		bst.search("another key");

		Assert.fail("missing NotFoundException!");
	}

	@Test(expected = NotFoundException.class)
	public void searchOnLargeTreeForMissingKeyShouldThrowException() {
		BinarySearchTree<String, String> bst =
			new BinarySearchTree<String, String>();
		bst.insert("abc", "def"); bst.insert("1", "2"); bst.insert("2", "3"); 
		bst.insert("more", "nodes"); bst.insert("hi", "there");

		bst.search("different key!");

		Assert.fail("missing NotFoundException!");
	}

	@Test
	public void nullsearchForKeyWithNullValueShouldReturnNull() {
		BinarySearchTree<String, String> bst =
			new BinarySearchTree<String, String>();
		
		bst.insert("hello,", null);
		String value = bst.nullsearch("hello,");

		Assert.assertTrue(null == value);
	}

	@Test
	public void nullsearchOnEmptyTreeShouldReturnNull() {
		BinarySearchTree<String, String> bst =
			new BinarySearchTree<String, String>();
		
		String value = bst.nullsearch("hello!");

		Assert.assertTrue(null == value);
	}

	@Test
	public void nullsearchOnMissingKeyShouldReturnNull() {
		BinarySearchTree<String, String> bst =
			new BinarySearchTree<String, String>();
		bst.insert("hello,","world!");

		String value = bst.nullsearch("another key");

		Assert.assertTrue(null == value);
	}

	@Test
	public void nullsearchOnLargeTreeForMissingKeyShouldReturnNull() {
		BinarySearchTree<String, String> bst =
			new BinarySearchTree<String, String>();
		bst.insert("abc", "def"); bst.insert("1", "2"); bst.insert("2", "3"); 
		bst.insert("more", "nodes"); bst.insert("hi", "there");

		String value = bst.nullsearch("different key!");

		Assert.assertTrue(null == value);
	}

	@Test(expected = NotFoundException.class)
	public void searchShouldFindByKeyNotValue() {
		BinarySearchTree<String, String> bst =
			new BinarySearchTree<String, String>();
		bst.insert("key", "value");
		
		bst.search("value");

		Assert.fail("missing NotFoundException!");
	}

	@Test
	public void containsShouldFindKeysAtTopOfTree() {
		BinarySearchTree<String, String> bst =
			new BinarySearchTree<String, String>();
		bst.insert("hello,", "world!");

		boolean result = bst.contains("hello,");

		Assert.assertEquals(true, result);
	}

	@Test
	public void containsShouldFindKeysInLargerTree() {
		BinarySearchTree<String, String> bst =
			new BinarySearchTree<String, String>();
		bst.insert("abc", "def"); bst.insert("1", "2"); bst.insert("2", "3"); 
		bst.insert("hello,", "world!");
		bst.insert("more", "nodes"); bst.insert("hi", "there");

		boolean result = bst.contains("hello,");

		Assert.assertEquals(true, result);
	}

	@Test
	public void containsShouldFindKeysNearBottomOfLargerTree() {
		BinarySearchTree<String, String> bst =
			new BinarySearchTree<String, String>();
		bst.insert("abc", "def"); bst.insert("1", "2"); bst.insert("2", "3"); 
		bst.insert("more", "nodes"); bst.insert("hi", "there");
		bst.insert("hello,", "world!");

		boolean result = bst.contains("hello,");

		Assert.assertEquals(true, result);
	}

	@Test
	public void containsShouldReturnFalseOnEmptyTree() {
		BinarySearchTree<String, String> bst =
			new BinarySearchTree<String, String>();
		
		boolean result = bst.contains("hello?");

		Assert.assertEquals(false, result);
	}

	@Test
	public void containsShouldReturnFalseOnMissingKey() {
		BinarySearchTree<String, String> bst =
			new BinarySearchTree<String, String>();
		bst.insert("some other key","..!");

		boolean result = bst.contains("hello!?");

		Assert.assertEquals(false, result);
	}

	@Test
	public void containsShouldReturnFalseOnMissingKeyInLargerTree() {
		BinarySearchTree<String, String> bst =
			new BinarySearchTree<String, String>();
		bst.insert("abc", "def"); bst.insert("1", "2"); bst.insert("2", "3"); 
		bst.insert("more", "nodes"); bst.insert("hi", "there");

		boolean result = bst.contains("different key!");

		Assert.assertEquals(false, result);
	}
}