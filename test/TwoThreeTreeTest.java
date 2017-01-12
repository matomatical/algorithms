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

	@Test
	public void deleteNodeNotInTreeShouldNotComplain() {
		TwoThreeTree<String, String> tree = new TwoThreeTree<>();
		// create the tree
							tree.insert("H", "");
			tree.insert("D","");				tree.insert("L","");
		tree.insert("A","");	tree.insert("J","");		tree.insert("N","");
										tree.insert("K","");

		// delete a key that is not in the tree
		tree.delete("Kalipso");

		// ensure other nodes were not lost
		for(String key : new String[] {"H", "A", "D", "L", "N", "J", "K"}) {
			Assert.assertTrue(tree.contains(key));
		}
	}

	// old 'delete root' tests

	@Test
	public void deleteTest1() {
		TwoThreeTree<String, String> tree = new TwoThreeTree<>();
		// create the tree
		tree.insert("H", ""); // just a root

		// delete the root
		tree.delete("H");

		// assert root was deleted
		Assert.assertFalse(tree.contains("H"));
	}

	@Test
	public void deleteTest2() {
		TwoThreeTree<String, String> tree = new TwoThreeTree<>();
		// create the tree
		tree.insert("H", ""); tree.insert("D","");

		// delete the root
		tree.delete("H");

		// ensure root was deleted
		Assert.assertFalse(tree.contains("H"));

		// ensure other nodes were not lost
		Assert.assertTrue(tree.contains("D"));
	}

	@Test
	public void deleteTest3() {
		TwoThreeTree<String, String> tree = new TwoThreeTree<>();
		// create the tree
		tree.insert("H", "");
		tree.insert("L","");

		// delete the root
		tree.delete("H");

		// ensure root was deleted
		Assert.assertFalse(tree.contains("H"));

		// ensure other nodes were not lost
		Assert.assertTrue(tree.contains("L"));
	}

	@Test
	public void deleteTest4() {
		TwoThreeTree<String, String> tree = new TwoThreeTree<>();
		// create the tree
					tree.insert("H", "");
		tree.insert("D","");		tree.insert("L",""); tree.insert("N","");

		// delete the root
		tree.delete("H");

		// ensure root was deleted
		Assert.assertFalse(tree.contains("H"));

		// ensure other nodes were not lost
		for(String key : new String[] {"D", "L", "N"}) {
			Assert.assertTrue(tree.contains(key));
		}
	}
	
	@Test
	public void deleteTest5() {
		TwoThreeTree<String, String> tree = new TwoThreeTree<>();
		// create the tree
							tree.insert("H", "");
			tree.insert("D","");				tree.insert("L","");
		tree.insert("A","");	tree.insert("J","");		tree.insert("N","");
									tree.insert("K","");

		// delete the root
		tree.delete("H");

		// ensure root was deleted
		Assert.assertFalse(tree.contains("H"));

		// ensure other nodes were not lost
		for(String key : new String[] {"D", "A", "L", "N", "J", "K"}) {
			Assert.assertTrue(tree.contains(key));
		}
	}

	// old 'right node' tests

	@Test
	public void deleteTest6() {
		TwoThreeTree<String, String> tree = new TwoThreeTree<>();
		// create the tree
						tree.insert("H", "");
		tree.insert("D","");				tree.insert("L","");

		// delete a node
		tree.delete("L");

		// ensure node was deleted
		Assert.assertFalse(tree.contains("L"));

		// ensure other nodes were not lost
		for(String key : new String[] {"H", "D"}) {
			Assert.assertTrue(tree.contains(key));
		}
	}

	@Test
	public void deleteTest7() {
		TwoThreeTree<String, String> tree = new TwoThreeTree<>();
		// create the tree
						tree.insert("H", "");
		tree.insert("D","");				tree.insert("L","");
								tree.insert("J","");

		// delete a node
		tree.delete("L");

		// ensure node was deleted
		Assert.assertFalse(tree.contains("L"));

		// ensure other nodes were not lost
		for(String key : new String[] {"H", "D", "J"}) {
			Assert.assertTrue(tree.contains(key));
		}
	}

	@Test
	public void deleteTest8() {
		TwoThreeTree<String, String> tree = new TwoThreeTree<>();
		// create the tree
						tree.insert("H", "");
		tree.insert("D","");				tree.insert("L","");
												tree.insert("N","");

		// delete a node
		tree.delete("L");

		// ensure node was deleted
		Assert.assertFalse(tree.contains("L"));

		// ensure other nodes were not lost
		for(String key : new String[] {"H", "D", "N"}) {
			Assert.assertTrue(tree.contains(key));
		}
	}
	
	@Test
	public void deleteTest9() {
		TwoThreeTree<String, String> tree = new TwoThreeTree<>();
		// create the tree
						tree.insert("H", "");
		tree.insert("D","");				tree.insert("L","");
							tree.insert("J","");		tree.insert("N","");

		// delete a node
		tree.delete("L");

		// ensure node was deleted
		Assert.assertFalse(tree.contains("L"));

		// ensure other nodes were not lost
		for(String key : new String[] {"H", "D", "J", "N"}) {
			Assert.assertTrue(tree.contains(key));
		}
	}

	@Test
	public void deleteTest10() {
		TwoThreeTree<String, String> tree = new TwoThreeTree<>();
		// create the tree
						tree.insert("H", "");
		tree.insert("D","");			tree.insert("L","");
		tree.insert("B",""); tree.insert("F","");
									tree.insert("J",""); tree.insert("N","");
		tree.insert("A",""); tree.insert("C","");
			tree.insert("E",""); tree.insert("G","");
								tree.insert("I",""); tree.insert("K","");
									tree.insert("M",""); tree.insert("O","");
											tree.insert("Mm","");
		// delete a node
		tree.delete("L");

		// ensure node was deleted
		Assert.assertFalse(tree.contains("L"));

		// ensure other nodes were not lost
		for(String key : new String[] {"H", "D", "J", "N", "M", "O", "Mm"}) {
			Assert.assertTrue(tree.contains(key));
		}
	}

	@Test
	public void deleteTest11() {
		TwoThreeTree<String, String> tree = new TwoThreeTree<>();
		// create the tree
						tree.insert("H", "");
			tree.insert("D","");			tree.insert("L","");
		tree.insert("B",""); tree.insert("F","");
									tree.insert("J",""); tree.insert("N","");
															tree.insert("O","");
											
		// delete a node
		tree.delete("L");

		// ensure node was deleted
		Assert.assertFalse(tree.contains("L"));

		// ensure other nodes were not lost
		for(String key : new String[] {"H", "D", "J", "N", "O"}) {
			Assert.assertTrue(tree.contains(key));
		}
	}

	// old 'delete left node' tests from AVL trees

	@Test
	public void deleteTest12() {
		TwoThreeTree<String, String> tree = new TwoThreeTree<>();
		// create the tree
					tree.insert("H", "");
		tree.insert("D","");		tree.insert("L","");

		// delete a node
		tree.delete("D");

		// ensure node was deleted
		Assert.assertFalse(tree.contains("D"));

		// ensure other nodes were not lost
		for(String key : new String[] {"H", "L"}) {
			Assert.assertTrue(tree.contains(key));
		}
	}

	@Test
	public void deleteTest13() {
		TwoThreeTree<String, String> tree = new TwoThreeTree<>();
		// create the tree
								tree.insert("H", "");
				tree.insert("D","");				tree.insert("L","");
		tree.insert("B","");

		// delete a node
		tree.delete("D");

		// ensure node was deleted
		Assert.assertFalse(tree.contains("D"));

		// ensure other nodes were not lost
		for(String key : new String[] {"H", "L", "B"}) {
			Assert.assertTrue(tree.contains(key));
		}
	}

	@Test
	public void deleteTest14() {
		TwoThreeTree<String, String> tree = new TwoThreeTree<>();
		// create the tree
						tree.insert("H", "");
		tree.insert("D","");				tree.insert("L","");
				tree.insert("F","");

		// delete a node
		tree.delete("D");

		// ensure node was deleted
		Assert.assertFalse(tree.contains("D"));

		// ensure other nodes were not lost
		for(String key : new String[] {"H", "F", "L"}) {
			Assert.assertTrue(tree.contains(key));
		}
	}
	
	@Test
	public void deleteTest15() {
		TwoThreeTree<String, String> tree = new TwoThreeTree<>();
		// create the tree
									tree.insert("H", "");
					tree.insert("D","");				tree.insert("L","");
		tree.insert("B","");		tree.insert("F","");

		// delete a node
		tree.delete("D");

		// ensure node was deleted
		Assert.assertFalse(tree.contains("D"));

		// ensure other nodes were not lost
		for(String key : new String[] {"H", "L", "B", "F"}) {
			Assert.assertTrue(tree.contains(key));
		}
	}

	@Test
	public void deleteTest16() {
		TwoThreeTree<String, String> tree = new TwoThreeTree<>();
		// create the tree
		tree.insert("H","");
		tree.insert("D","");			tree.insert("L","");
		tree.insert("B",""); 	tree.insert("F","");
										tree.insert("J","");tree.insert("N","");
		tree.insert("A",""); tree.insert("C","");
			tree.insert("E",""); tree.insert("G","");
									tree.insert("I","");tree.insert("K","");
										tree.insert("M","");tree.insert("O","");
				tree.insert("Ee","");
		
		// delete a node
		tree.delete("D");

		// ensure node was deleted
		Assert.assertFalse(tree.contains("D"));

		// ensure other nodes were not lost
		for(String key : new String[] {"H", "L", "B", "F", "Ee", "E", "G"}) {
			Assert.assertTrue(tree.contains(key));
		}
	}

	@Test
	public void deleteLeftNodeTest6() {
		TwoThreeTree<String, String> tree = new TwoThreeTree<>();
		// create the tree
									tree.insert("H", "");
					tree.insert("D","");				tree.insert("L","");
		tree.insert("B","");	tree.insert("F","");
										tree.insert("J","");tree.insert("N","");
									tree.insert("G","");
		
		// delete the left node
		tree.delete("D");

		// ensure left node was deleted
		Assert.assertFalse(tree.contains("D"));

		// ensure other nodes were not lost
		for(String key : new String[] {"H", "L", "B", "F", "G", "L"}) {
			Assert.assertTrue(tree.contains(key));
		}
	}

	@Test
	public void deleteLotsOfNodesTest1() {
		TwoThreeTree<Character, Character> tree = new TwoThreeTree<>();
		char[] characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
		for(char c : characters) {
			tree.insert(c, c);
		}

		for(int i = 0; i < characters.length; i++) {
			tree.delete(characters[i]);
			Assert.assertFalse(tree.contains(characters[i]));
			for(int j = i+1; j < characters.length; j++) {
				Assert.assertTrue(tree.contains(characters[j]));
			}
		}
	}

	@Test
	public void deleteLotsOfNodesTest2() {
		TwoThreeTree<Character, Character> tree = new TwoThreeTree<>();
		char[] characters="qwertyuiopasdfghjklzxcvbnm".toCharArray();
		for(char c : characters) {
			tree.insert(c, c);
		}

		for(int i = 0; i < characters.length; i++) {
			tree.delete(characters[i]);
			Assert.assertFalse("deletion of " + characters[i] + " failed", 
				tree.contains(characters[i]));
			for(int j = i+1; j < characters.length; j++) {
				Assert.assertTrue("deletion of " + characters[i]
								+ " lost remaining character " + characters[j], 
								tree.contains(characters[j]));
			}
		}
	}
}