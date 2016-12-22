import org.junit.Assert;
import org.junit.Test;

import com.matomatical.ads.AVLTree;
import com.matomatical.ads.NotFoundException;

public class AVLTreeTest {

	@Test
	public void insertThenSearchShoudlReturnValue() {
		AVLTree<String, String> bst = new AVLTree<String, String>();
		
		bst.insert("hello,", "world!");
		String value = bst.search("hello,");

		Assert.assertEquals("world!", value);
	}

	@Test
	public void insertSeveralThenSearchShoudlReturnFirstValue() {
		AVLTree<String, String> bst = new AVLTree<String, String>();
		
		bst.insert("hello,", "world!");
		bst.insert("matt", "f");
		bst.insert("day", "sunday");
		String value = bst.search("hello,");

		Assert.assertEquals("world!", value);
	}

	@Test
	public void insertSeveralThenSearchShoudlReturnLaterValue() {
		AVLTree<String, String> bst = new AVLTree<String, String>();
		
		bst.insert("hello,", "world!");
		bst.insert("matt", "f");
		bst.insert("day", "sunday");
		String value = bst.search("day");

		Assert.assertEquals("sunday", value);
	}

	@Test
	public void insertExistingShouldUpdateSearchValue() {
		AVLTree<String, String> bst = new AVLTree<String, String>();
		bst.insert("hello,", "world!");

		bst.insert("hello,", "matt!");
		String value = bst.search("hello,");

		Assert.assertEquals("matt!", value);
	}

	@Test
	public void insertNullShouldSucceed() {
		AVLTree<String, String> bst = new AVLTree<String, String>();
		
		bst.insert("hello,", null);

		// pass if no exception was thrown
	}

	@Test
	public void insertExistingInLargeTreeShouldUpdateSearchValue() {
		AVLTree<String, String> bst = new AVLTree<String, String>();
		
		bst.insert("abc", "def"); bst.insert("1", "2"); bst.insert("2", "3"); 
		bst.insert("more", "nodes"); bst.insert("hi", "there");

		bst.insert("hello,", "world!");
		bst.insert("hello,", "matt!");

		String value = bst.search("hello,");

		Assert.assertEquals("matt!", value);
	}

	@Test
	public void searchForKeyWithNullValueShouldReturnNull() {
		AVLTree<String, String> bst = new AVLTree<String, String>();
		
		bst.insert("hello,", null);
		String value = bst.search("hello,");

		Assert.assertTrue(null == value);
	}

	@Test(expected = NotFoundException.class)
	public void searchOnEmptyTreeShouldThrowException() {
		AVLTree<String, String> bst = new AVLTree<String, String>();
		
		bst.search("hello!");

		Assert.fail("missing NotFoundException!");
	}

	@Test(expected = NotFoundException.class)
	public void searchOnMissingKeyShouldThrowException() {
		AVLTree<String, String> bst = new AVLTree<String, String>();
		bst.insert("hello,","world!");

		bst.search("another key");

		Assert.fail("missing NotFoundException!");
	}

	@Test(expected = NotFoundException.class)
	public void searchOnLargeTreeForMissingKeyShouldThrowException() {
		AVLTree<String, String> bst = new AVLTree<String, String>();
		bst.insert("abc", "def"); bst.insert("1", "2"); bst.insert("2", "3"); 
		bst.insert("more", "nodes"); bst.insert("hi", "there");

		bst.search("different key!");

		Assert.fail("missing NotFoundException!");
	}

	@Test
	public void nullsearchForKeyWithNullValueShouldReturnNull() {
		AVLTree<String, String> bst = new AVLTree<String, String>();
		
		bst.insert("hello,", null);
		String value = bst.nullsearch("hello,");

		Assert.assertTrue(null == value);
	}

	@Test
	public void nullsearchOnEmptyTreeShouldReturnNull() {
		AVLTree<String, String> bst = new AVLTree<String, String>();
		
		String value = bst.nullsearch("hello!");

		Assert.assertTrue(null == value);
	}

	@Test
	public void nullsearchOnMissingKeyShouldReturnNull() {
		AVLTree<String, String> bst = new AVLTree<String, String>();
		bst.insert("hello,","world!");

		String value = bst.nullsearch("another key");

		Assert.assertTrue(null == value);
	}

	@Test
	public void nullsearchOnLargeTreeForMissingKeyShouldReturnNull() {
		AVLTree<String, String> bst = new AVLTree<String, String>();
		bst.insert("abc", "def"); bst.insert("1", "2"); bst.insert("2", "3"); 
		bst.insert("more", "nodes"); bst.insert("hi", "there");

		String value = bst.nullsearch("different key!");

		Assert.assertTrue(null == value);
	}

	@Test(expected = NotFoundException.class)
	public void searchShouldFindByKeyNotValue() {
		AVLTree<String, String> bst = new AVLTree<String, String>();
		bst.insert("key", "value");
		
		bst.search("value");

		Assert.fail("missing NotFoundException!");
	}

	@Test
	public void containsShouldFindKeysAtTopOfTree() {
		AVLTree<String, String> bst = new AVLTree<String, String>();
		bst.insert("hello,", "world!");

		boolean result = bst.contains("hello,");

		Assert.assertEquals(true, result);
	}

	@Test
	public void containsShouldFindKeysInLargerTree() {
		AVLTree<String, String> bst = new AVLTree<String, String>();
		bst.insert("abc", "def"); bst.insert("1", "2"); bst.insert("2", "3"); 
		bst.insert("hello,", "world!");
		bst.insert("more", "nodes"); bst.insert("hi", "there");

		boolean result = bst.contains("hello,");

		Assert.assertEquals(true, result);
	}

	@Test
	public void containsShouldFindKeysNearBottomOfLargerTree() {
		AVLTree<String, String> bst = new AVLTree<String, String>();
		bst.insert("abc", "def"); bst.insert("1", "2"); bst.insert("2", "3"); 
		bst.insert("more", "nodes"); bst.insert("hi", "there");
		bst.insert("hello,", "world!");

		boolean result = bst.contains("hello,");

		Assert.assertEquals(true, result);
	}

	@Test
	public void containsShouldReturnFalseOnEmptyTree() {
		AVLTree<String, String> bst = new AVLTree<String, String>();
		
		boolean result = bst.contains("hello?");

		Assert.assertEquals(false, result);
	}

	@Test
	public void containsShouldReturnFalseOnMissingKey() {
		AVLTree<String, String> bst = new AVLTree<String, String>();
		bst.insert("some other key","..!");

		boolean result = bst.contains("hello!?");

		Assert.assertEquals(false, result);
	}

	@Test
	public void containsShouldReturnFalseOnMissingKeyInLargerTree() {
		AVLTree<String, String> bst = new AVLTree<String, String>();
		bst.insert("abc", "def"); bst.insert("1", "2"); bst.insert("2", "3"); 
		bst.insert("more", "nodes"); bst.insert("hi", "there");

		boolean result = bst.contains("different key!");

		Assert.assertEquals(false, result);
	}
}