import org.junit.Assert;
import org.junit.Test;

import com.matomatical.ads.AVLTree;
import com.matomatical.ads.NotFoundException;

public class AVLTreeTest {

	@Test
	public void insertThenSearchShoudlReturnValue() {
		AVLTree<String, String> tree = new AVLTree<String, String>();
		
		tree.insert("hello,", "world!");
		String value = tree.search("hello,");

		Assert.assertEquals("world!", value);
	}

	@Test
	public void insertSeveralThenSearchShoudlReturnFirstValue() {
		AVLTree<String, String> tree = new AVLTree<String, String>();
		
		tree.insert("hello,", "world!");
		tree.insert("matt", "f");
		tree.insert("day", "sunday");
		String value = tree.search("hello,");

		Assert.assertEquals("world!", value);
	}

	@Test
	public void insertSeveralThenSearchShoudlReturnLaterValue() {
		AVLTree<String, String> tree = new AVLTree<String, String>();
		
		tree.insert("hello,", "world!");
		tree.insert("matt", "f");
		tree.insert("day", "sunday");
		String value = tree.search("day");

		Assert.assertEquals("sunday", value);
	}

	@Test
	public void insertExistingShouldUpdateSearchValue() {
		AVLTree<String, String> tree = new AVLTree<String, String>();
		tree.insert("hello,", "world!");

		tree.insert("hello,", "matt!");
		String value = tree.search("hello,");

		Assert.assertEquals("matt!", value);
	}

	@Test
	public void insertNullShouldSucceed() {
		AVLTree<String, String> tree = new AVLTree<String, String>();
		
		tree.insert("hello,", null);

		// pass if no exception was thrown
	}

	@Test
	public void insertExistingInLargeTreeShouldUpdateSearchValue() {
		AVLTree<String, String> tree = new AVLTree<String, String>();
		
		tree.insert("abc", "def"); tree.insert("1", "2"); tree.insert("2", "3"); 
		tree.insert("more", "nodes"); tree.insert("hi", "there");

		tree.insert("hello,", "world!");
		tree.insert("hello,", "matt!");

		String value = tree.search("hello,");

		Assert.assertEquals("matt!", value);
	}

	@Test
	public void searchForKeyWithNullValueShouldReturnNull() {
		AVLTree<String, String> tree = new AVLTree<String, String>();
		
		tree.insert("hello,", null);
		String value = tree.search("hello,");

		Assert.assertTrue(null == value);
	}

	@Test(expected = NotFoundException.class)
	public void searchOnEmptyTreeShouldThrowException() {
		AVLTree<String, String> tree = new AVLTree<String, String>();
		
		tree.search("hello!");

		Assert.fail("missing NotFoundException!");
	}

	@Test(expected = NotFoundException.class)
	public void searchOnMissingKeyShouldThrowException() {
		AVLTree<String, String> tree = new AVLTree<String, String>();
		tree.insert("hello,","world!");

		tree.search("another key");

		Assert.fail("missing NotFoundException!");
	}

	@Test(expected = NotFoundException.class)
	public void searchOnLargeTreeForMissingKeyShouldThrowException() {
		AVLTree<String, String> tree = new AVLTree<String, String>();
		tree.insert("abc", "def"); tree.insert("1", "2"); tree.insert("2", "3"); 
		tree.insert("more", "nodes"); tree.insert("hi", "there");

		tree.search("different key!");

		Assert.fail("missing NotFoundException!");
	}

	@Test
	public void nullsearchForKeyWithNullValueShouldReturnNull() {
		AVLTree<String, String> tree = new AVLTree<String, String>();
		
		tree.insert("hello,", null);
		String value = tree.nullsearch("hello,");

		Assert.assertTrue(null == value);
	}

	@Test
	public void nullsearchOnEmptyTreeShouldReturnNull() {
		AVLTree<String, String> tree = new AVLTree<String, String>();
		
		String value = tree.nullsearch("hello!");

		Assert.assertTrue(null == value);
	}

	@Test
	public void nullsearchOnMissingKeyShouldReturnNull() {
		AVLTree<String, String> tree = new AVLTree<String, String>();
		tree.insert("hello,","world!");

		String value = tree.nullsearch("another key");

		Assert.assertTrue(null == value);
	}

	@Test
	public void nullsearchOnLargeTreeForMissingKeyShouldReturnNull() {
		AVLTree<String, String> tree = new AVLTree<String, String>();
		tree.insert("abc", "def"); tree.insert("1", "2"); tree.insert("2", "3"); 
		tree.insert("more", "nodes"); tree.insert("hi", "there");

		String value = tree.nullsearch("different key!");

		Assert.assertTrue(null == value);
	}

	@Test(expected = NotFoundException.class)
	public void searchShouldFindByKeyNotValue() {
		AVLTree<String, String> tree = new AVLTree<String, String>();
		tree.insert("key", "value");
		
		tree.search("value");

		Assert.fail("missing NotFoundException!");
	}

	@Test
	public void containsShouldFindKeysAtTopOfTree() {
		AVLTree<String, String> tree = new AVLTree<String, String>();
		tree.insert("hello,", "world!");

		boolean result = tree.contains("hello,");

		Assert.assertEquals(true, result);
	}

	@Test
	public void containsShouldFindKeysInLargerTree() {
		AVLTree<String, String> tree = new AVLTree<String, String>();
		tree.insert("abc", "def"); tree.insert("1", "2"); tree.insert("2", "3"); 
		tree.insert("hello,", "world!");
		tree.insert("more", "nodes"); tree.insert("hi", "there");

		boolean result = tree.contains("hello,");

		Assert.assertEquals(true, result);
	}

	@Test
	public void containsShouldFindKeysNearBottomOfLargerTree() {
		AVLTree<String, String> tree = new AVLTree<String, String>();
		tree.insert("abc", "def"); tree.insert("1", "2"); tree.insert("2", "3"); 
		tree.insert("more", "nodes"); tree.insert("hi", "there");
		tree.insert("hello,", "world!");

		boolean result = tree.contains("hello,");

		Assert.assertEquals(true, result);
	}

	@Test
	public void containsShouldReturnFalseOnEmptyTree() {
		AVLTree<String, String> tree = new AVLTree<String, String>();
		
		boolean result = tree.contains("hello?");

		Assert.assertEquals(false, result);
	}

	@Test
	public void containsShouldReturnFalseOnMissingKey() {
		AVLTree<String, String> tree = new AVLTree<String, String>();
		tree.insert("some other key","..!");

		boolean result = tree.contains("hello!?");

		Assert.assertEquals(false, result);
	}

	@Test
	public void containsShouldReturnFalseOnMissingKeyInLargerTree() {
		AVLTree<String, String> tree = new AVLTree<String, String>();
		tree.insert("abc", "def"); tree.insert("1", "2"); tree.insert("2", "3"); 
		tree.insert("more", "nodes"); tree.insert("hi", "there");

		boolean result = tree.contains("different key!");

		Assert.assertEquals(false, result);
	}

	@Test
	public void deleteNodeNotInTreeShouldNotComplain() {
		AVLTree<String, String> tree = new AVLTree<String, String>();
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

	@Test
	public void deleteRootTest1() {
		AVLTree<String, String> tree = new AVLTree<String, String>();
		// create the tree
		tree.insert("H", ""); // just a root

		// delete the root
		tree.delete("H");

		// assert root was deleted
		Assert.assertFalse(tree.contains("H"));
	}

	@Test
	public void deleteRootTest2() {
		AVLTree<String, String> tree = new AVLTree<String, String>();
		// create the tree
				tree.insert("H", ""); // root
		tree.insert("D",""); // and a left child

		// delete the root
		tree.delete("H");

		// ensure root was deleted
		Assert.assertFalse(tree.contains("H"));

		// ensure other nodes were not lost
		Assert.assertTrue(tree.contains("D"));
	}

	@Test
	public void deleteRootTest3() {
		AVLTree<String, String> tree = new AVLTree<String, String>();
		// create the tree
		tree.insert("H", ""); // root and a right child
				tree.insert("L","");

		// delete the root
		tree.delete("H");

		// ensure root was deleted
		Assert.assertFalse(tree.contains("H"));

		// ensure other nodes were not lost
		Assert.assertTrue(tree.contains("L"));
	}

	@Test
	public void deleteRootTest4() {
		AVLTree<String, String> tree = new AVLTree<String, String>();
		// create the tree
					tree.insert("H", ""); // root and both children
		tree.insert("D","");		tree.insert("L","");
											tree.insert("N","");

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
	public void deleteRootTest5() {
		AVLTree<String, String> tree = new AVLTree<String, String>();
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

	@Test
	public void deleteRightNodeTest1() {
		AVLTree<String, String> tree = new AVLTree<String, String>();
		// create the tree
						tree.insert("H", "");
		tree.insert("D","");				tree.insert("L","");

		// delete the right node
		tree.delete("L");

		// ensure right node was deleted
		Assert.assertFalse(tree.contains("L"));

		// ensure other nodes were not lost
		for(String key : new String[] {"H", "D"}) {
			Assert.assertTrue(tree.contains(key));
		}
	}

	@Test
	public void deleteRightNodeTest2() {
		AVLTree<String, String> tree = new AVLTree<String, String>();
		// create the tree
						tree.insert("H", "");
		tree.insert("D","");				tree.insert("L","");
								tree.insert("J","");

		// delete the right node
		tree.delete("L");

		// ensure right node was deleted
		Assert.assertFalse(tree.contains("L"));

		// ensure other nodes were not lost
		for(String key : new String[] {"H", "D", "J"}) {
			Assert.assertTrue(tree.contains(key));
		}
	}

	@Test
	public void deleteRightNodeTest3() {
		AVLTree<String, String> tree = new AVLTree<String, String>();
		// create the tree
						tree.insert("H", "");
		tree.insert("D","");				tree.insert("L","");
												tree.insert("N","");

		// delete the right node
		tree.delete("L");

		// ensure right node was deleted
		Assert.assertFalse(tree.contains("L"));

		// ensure other nodes were not lost
		for(String key : new String[] {"H", "D", "N"}) {
			Assert.assertTrue(tree.contains(key));
		}
	}
	
	@Test
	public void deleteRightNodeTest4() {
		AVLTree<String, String> tree = new AVLTree<String, String>();
		// create the tree
						tree.insert("H", "");
		tree.insert("D","");				tree.insert("L","");
							tree.insert("J","");		tree.insert("N","");

		// delete the right node
		tree.delete("L");

		// ensure right node was deleted
		Assert.assertFalse(tree.contains("L"));

		// ensure other nodes were not lost
		for(String key : new String[] {"H", "D", "J", "N"}) {
			Assert.assertTrue(tree.contains(key));
		}
	}

	@Test
	public void deleteRightNodeTest5() {
		AVLTree<String, String> tree = new AVLTree<String, String>();
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
		// delete the right node
		tree.delete("L");

		// ensure right node was deleted
		Assert.assertFalse(tree.contains("L"));

		// ensure other nodes were not lost
		for(String key : new String[] {"H", "D", "J", "N", "M", "O", "Mm"}) {
			Assert.assertTrue(tree.contains(key));
		}
	}

	@Test
	public void deleteRightNodeTest6() {
		AVLTree<String, String> tree = new AVLTree<String, String>();
		// create the tree
						tree.insert("H", "");
			tree.insert("D","");			tree.insert("L","");
		tree.insert("B",""); tree.insert("F","");
									tree.insert("J",""); tree.insert("N","");
															tree.insert("O","");
											
		// delete the right node
		tree.delete("L");

		// ensure right node was deleted
		Assert.assertFalse(tree.contains("L"));

		// ensure other nodes were not lost
		for(String key : new String[] {"H", "D", "J", "N", "O"}) {
			Assert.assertTrue(tree.contains(key));
		}
	}

	@Test
	public void deleteLeftNodeTest1() {
		AVLTree<String, String> tree = new AVLTree<String, String>();
		// create the tree
					tree.insert("H", "");
		tree.insert("D","");		tree.insert("L","");

		// delete the left node
		tree.delete("D");

		// ensure left node was deleted
		Assert.assertFalse(tree.contains("D"));

		// ensure other nodes were not lost
		for(String key : new String[] {"H", "L"}) {
			Assert.assertTrue(tree.contains(key));
		}
	}

	@Test
	public void deleteLeftNodeTest2() {
		AVLTree<String, String> tree = new AVLTree<String, String>();
		// create the tree
								tree.insert("H", "");
				tree.insert("D","");				tree.insert("L","");
		tree.insert("B","");

		// delete the left node
		tree.delete("D");

		// ensure left node was deleted
		Assert.assertFalse(tree.contains("D"));

		// ensure other nodes were not lost
		for(String key : new String[] {"H", "L", "B"}) {
			Assert.assertTrue(tree.contains(key));
		}
	}

	@Test
	public void deleteLeftNodeTest3() {
		AVLTree<String, String> tree = new AVLTree<String, String>();
		// create the tree
						tree.insert("H", "");
		tree.insert("D","");				tree.insert("L","");
				tree.insert("F","");

		// delete the left node
		tree.delete("D");

		// ensure left node was deleted
		Assert.assertFalse(tree.contains("D"));

		// ensure other nodes were not lost
		for(String key : new String[] {"H", "F", "L"}) {
			Assert.assertTrue(tree.contains(key));
		}
	}
	
	@Test
	public void deleteLeftNodeTest4() {
		AVLTree<String, String> tree = new AVLTree<String, String>();
		// create the tree
									tree.insert("H", "");
					tree.insert("D","");				tree.insert("L","");
		tree.insert("B","");		tree.insert("F","");

		// delete the left node
		tree.delete("D");

		// ensure left node was deleted
		Assert.assertFalse(tree.contains("D"));

		// ensure other nodes were not lost
		for(String key : new String[] {"H", "L", "B", "F"}) {
			Assert.assertTrue(tree.contains(key));
		}
	}

	@Test
	public void deleteLeftNodeTest5() {
		AVLTree<String, String> tree = new AVLTree<String, String>();
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
		
		// delete the left node
		tree.delete("D");

		// ensure left node was deleted
		Assert.assertFalse(tree.contains("D"));

		// ensure other nodes were not lost
		for(String key : new String[] {"H", "L", "B", "F", "Ee", "E", "G"}) {
			Assert.assertTrue(tree.contains(key));
		}
	}

	@Test
	public void deleteLeftNodeTest6() {
		AVLTree<String, String> tree = new AVLTree<String, String>();
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
		AVLTree<Character, Character> tree = new AVLTree<>();
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
		AVLTree<Character, Character> tree = new AVLTree<>();
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