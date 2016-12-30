import org.junit.Assert;
import org.junit.Test;

import com.matomatical.ads.TwoThreeTree;
import com.matomatical.ads.NotFoundException;

public class TwoThreeTreeTest {

	@Test
	public void insertThenSearchShoudlReturnValue() {
		TwoThreeTree<String, String> tree = new TwoThreeTree<>();
		
		tree.insert("hello,", "world!");
		String value = tree.search("hello,");

		Assert.assertEquals("world!", value);
	}

	@Test
	public void insertSeveralThenSearchShoudlReturnFirstValue() {
		TwoThreeTree<String, String> tree = new TwoThreeTree<>();
		
		tree.insert("hello,", "world!");
		tree.insert("matt", "f");
		tree.insert("day", "sunday");
		String value = tree.search("hello,");

		Assert.assertEquals("world!", value);
	}

	@Test
	public void insertSeveralThenSearchShoudlReturnLaterValue() {
		TwoThreeTree<String, String> tree = new TwoThreeTree<>();
		
		tree.insert("hello,", "world!");
		tree.insert("matt", "f");
		tree.insert("day", "sunday");
		String value = tree.search("day");

		Assert.assertEquals("sunday", value);
	}

	@Test
	public void insertExistingShouldUpdateSearchValue() {
		TwoThreeTree<String, String> tree = new TwoThreeTree<>();
		tree.insert("hello,", "world!");

		tree.insert("hello,", "matt!");
		String value = tree.search("hello,");

		Assert.assertEquals("matt!", value);
	}

	@Test
	public void insertNullShouldSucceed() {
		TwoThreeTree<String, String> tree = new TwoThreeTree<>();
		
		tree.insert("hello,", null);

		// pass if no exception was thrown
	}

	@Test
	public void insertExistingInLargeTreeShouldUpdateSearchValue() {
		TwoThreeTree<String, String> tree = new TwoThreeTree<>();
		
		tree.insert("abc", "def"); tree.insert("1", "2"); tree.insert("2", "3"); 
		tree.insert("more", "nodes"); tree.insert("hi", "there");

		tree.insert("hello,", "world!");
		tree.insert("hello,", "matt!");

		String value = tree.search("hello,");

		Assert.assertEquals("matt!", value);
	}

	@Test
	public void searchForKeyWithNullValueShouldReturnNull() {
		TwoThreeTree<String, String> tree = new TwoThreeTree<>();
		
		tree.insert("hello,", null);
		String value = tree.search("hello,");

		Assert.assertTrue(null == value);
	}

	@Test(expected = NotFoundException.class)
	public void searchOnEmptyTreeShouldThrowException() {
		TwoThreeTree<String, String> tree = new TwoThreeTree<>();
		
		tree.search("hello!");

		Assert.fail("missing NotFoundException!");
	}

	@Test(expected = NotFoundException.class)
	public void searchOnMissingKeyShouldThrowException() {
		TwoThreeTree<String, String> tree = new TwoThreeTree<>();
		tree.insert("hello,","world!");

		tree.search("another key");

		Assert.fail("missing NotFoundException!");
	}

	@Test(expected = NotFoundException.class)
	public void searchOnLargeTreeForMissingKeyShouldThrowException() {
		TwoThreeTree<String, String> tree = new TwoThreeTree<>();
		tree.insert("abc", "def"); tree.insert("1", "2"); tree.insert("2", "3"); 
		tree.insert("more", "nodes"); tree.insert("hi", "there");

		tree.search("different key!");

		Assert.fail("missing NotFoundException!");
	}

	@Test
	public void nullsearchForKeyWithNullValueShouldReturnNull() {
		TwoThreeTree<String, String> tree = new TwoThreeTree<>();
		
		tree.insert("hello,", null);
		String value = tree.nullsearch("hello,");

		Assert.assertTrue(null == value);
	}

	@Test
	public void nullsearchOnEmptyTreeShouldReturnNull() {
		TwoThreeTree<String, String> tree = new TwoThreeTree<>();
		
		String value = tree.nullsearch("hello!");

		Assert.assertTrue(null == value);
	}

	@Test
	public void nullsearchOnMissingKeyShouldReturnNull() {
		TwoThreeTree<String, String> tree = new TwoThreeTree<>();
		tree.insert("hello,","world!");

		String value = tree.nullsearch("another key");

		Assert.assertTrue(null == value);
	}

	@Test
	public void nullsearchOnLargeTreeForMissingKeyShouldReturnNull() {
		TwoThreeTree<String, String> tree = new TwoThreeTree<>();
		tree.insert("abc", "def"); tree.insert("1", "2"); tree.insert("2", "3"); 
		tree.insert("more", "nodes"); tree.insert("hi", "there");

		String value = tree.nullsearch("different key!");

		Assert.assertTrue(null == value);
	}

	@Test(expected = NotFoundException.class)
	public void searchShouldFindByKeyNotValue() {
		TwoThreeTree<String, String> tree = new TwoThreeTree<>();
		tree.insert("key", "value");
		
		tree.search("value");

		Assert.fail("missing NotFoundException!");
	}

	@Test
	public void containsShouldFindKeysAtTopOfTree() {
		TwoThreeTree<String, String> tree = new TwoThreeTree<>();
		tree.insert("hello,", "world!");

		boolean result = tree.contains("hello,");

		Assert.assertEquals(true, result);
	}

	@Test
	public void containsShouldFindKeysInLargerTree() {
		TwoThreeTree<String, String> tree = new TwoThreeTree<>();
		tree.insert("abc", "def"); tree.insert("1", "2"); tree.insert("2", "3"); 
		tree.insert("hello,", "world!");
		tree.insert("more", "nodes"); tree.insert("hi", "there");

		boolean result = tree.contains("hello,");

		Assert.assertEquals(true, result);
	}

	@Test
	public void containsShouldFindKeysNearBottomOfLargerTree() {
		TwoThreeTree<String, String> tree = new TwoThreeTree<>();
		tree.insert("abc", "def"); tree.insert("1", "2"); tree.insert("2", "3"); 
		tree.insert("more", "nodes"); tree.insert("hi", "there");
		tree.insert("hello,", "world!");

		boolean result = tree.contains("hello,");

		Assert.assertEquals(true, result);
	}

	@Test
	public void containsShouldReturnFalseOnEmptyTree() {
		TwoThreeTree<String, String> tree = new TwoThreeTree<>();
		
		boolean result = tree.contains("hello?");

		Assert.assertEquals(false, result);
	}

	@Test
	public void containsShouldReturnFalseOnMissingKey() {
		TwoThreeTree<String, String> tree = new TwoThreeTree<>();
		tree.insert("some other key","..!");

		boolean result = tree.contains("hello!?");

		Assert.assertEquals(false, result);
	}

	@Test
	public void containsShouldReturnFalseOnMissingKeyInLargerTree() {
		TwoThreeTree<String, String> tree = new TwoThreeTree<>();
		tree.insert("abc", "def"); tree.insert("1", "2"); tree.insert("2", "3"); 
		tree.insert("more", "nodes"); tree.insert("hi", "there");

		boolean result = tree.contains("different key!");

		Assert.assertEquals(false, result);
	}
}