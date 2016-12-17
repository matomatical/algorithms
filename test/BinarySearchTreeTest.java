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

	@Test
	public void deleteNodeNotInTreeShouldNotComplain() {
		BinarySearchTree<String,String> bst =
			new BinarySearchTree<String,String>();
		// create the tree
						bst.insert("H", "");
		bst.insert("D","");				bst.insert("L","");
							bst.insert("J","");		bst.insert("N","");
									bst.insert("K","");

		// delete a key that is not in the tree
		bst.delete("Kalipso");

		// ensure other nodes were not lost
		for(String key : new String[] {"H", "D", "L", "N", "J", "K"}) {
			Assert.assertTrue(bst.contains(key));
		}
	}

	@Test
	public void deleteRootTest1() {
		BinarySearchTree<String,String> bst =
			new BinarySearchTree<String,String>();
		// create the tree
		bst.insert("H", ""); // just a root

		// delete the root
		bst.delete("H");

		// assert root was deleted
		Assert.assertFalse(bst.contains("H"));
	}

	@Test
	public void deleteRootTest2() {
		BinarySearchTree<String,String> bst =
			new BinarySearchTree<String,String>();
		// create the tree
				bst.insert("H", ""); // root
		bst.insert("D",""); // and a left child

		// delete the root
		bst.delete("H");

		// ensure root was deleted
		Assert.assertFalse(bst.contains("H"));

		// ensure other nodes were not lost
		Assert.assertTrue(bst.contains("D"));
	}

	@Test
	public void deleteRootTest3() {
		BinarySearchTree<String,String> bst =
			new BinarySearchTree<String,String>();
		// create the tree
		bst.insert("H", ""); // root and a right child
				bst.insert("L","");

		// delete the root
		bst.delete("H");

		// ensure root was deleted
		Assert.assertFalse(bst.contains("H"));

		// ensure other nodes were not lost
		Assert.assertTrue(bst.contains("L"));
	}

	@Test
	public void deleteRootTest4() {
		BinarySearchTree<String,String> bst =
			new BinarySearchTree<String,String>();
		// create the tree
					bst.insert("H", ""); // root and both children
		bst.insert("D","");		bst.insert("L","");
											bst.insert("N","");

		// delete the root
		bst.delete("H");

		// ensure root was deleted
		Assert.assertFalse(bst.contains("H"));

		// ensure other nodes were not lost
		for(String key : new String[] {"D", "L", "N"}) {
			Assert.assertTrue(bst.contains(key));
		}
	}
	
	@Test
	public void deleteRootTest5() {
		BinarySearchTree<String,String> bst =
			new BinarySearchTree<String,String>();
		// create the tree
						bst.insert("H", "");
		bst.insert("D","");				bst.insert("L","");
							bst.insert("J","");		bst.insert("N","");
									bst.insert("K","");

		// delete the root
		bst.delete("H");

		// ensure root was deleted
		Assert.assertFalse(bst.contains("H"));

		// ensure other nodes were not lost
		for(String key : new String[] {"D", "L", "N", "J", "K"}) {
			Assert.assertTrue(bst.contains(key));
		}
	}

	@Test
	public void deleteRightNodeTest1() {
		BinarySearchTree<String,String> bst =
			new BinarySearchTree<String,String>();
		// create the tree
						bst.insert("H", "");
		bst.insert("D","");				bst.insert("L","");

		// delete the right node
		bst.delete("L");

		// ensure right node was deleted
		Assert.assertFalse(bst.contains("L"));

		// ensure other nodes were not lost
		for(String key : new String[] {"H", "D"}) {
			Assert.assertTrue(bst.contains(key));
		}
	}

	@Test
	public void deleteRightNodeTest2() {
		BinarySearchTree<String,String> bst =
			new BinarySearchTree<String,String>();
		// create the tree
						bst.insert("H", "");
		bst.insert("D","");				bst.insert("L","");
								bst.insert("J","");

		// delete the right node
		bst.delete("L");

		// ensure right node was deleted
		Assert.assertFalse(bst.contains("L"));

		// ensure other nodes were not lost
		for(String key : new String[] {"H", "D", "J"}) {
			Assert.assertTrue(bst.contains(key));
		}
	}

	@Test
	public void deleteRightNodeTest3() {
		BinarySearchTree<String,String> bst =
			new BinarySearchTree<String,String>();
		// create the tree
						bst.insert("H", "");
		bst.insert("D","");				bst.insert("L","");
												bst.insert("N","");

		// delete the right node
		bst.delete("L");

		// ensure right node was deleted
		Assert.assertFalse(bst.contains("L"));

		// ensure other nodes were not lost
		for(String key : new String[] {"H", "D", "N"}) {
			Assert.assertTrue(bst.contains(key));
		}
	}
	
	@Test
	public void deleteRightNodeTest4() {
		BinarySearchTree<String,String> bst =
			new BinarySearchTree<String,String>();
		// create the tree
						bst.insert("H", "");
		bst.insert("D","");				bst.insert("L","");
							bst.insert("J","");		bst.insert("N","");

		// delete the right node
		bst.delete("L");

		// ensure right node was deleted
		Assert.assertFalse(bst.contains("L"));

		// ensure other nodes were not lost
		for(String key : new String[] {"H", "D", "J", "N"}) {
			Assert.assertTrue(bst.contains(key));
		}
	}

	@Test
	public void deleteRightNodeTest5() {
		BinarySearchTree<String,String> bst =
			new BinarySearchTree<String,String>();
		// create the tree
					bst.insert("H", "");
		bst.insert("D","");			bst.insert("L","");
						bst.insert("J","");		bst.insert("N","");
										bst.insert("M","");	bst.insert("O","");
											bst.insert("Mm","");
		// delete the right node
		bst.delete("L");

		// ensure right node was deleted
		Assert.assertFalse(bst.contains("L"));

		// ensure other nodes were not lost
		for(String key : new String[] {"H", "D", "J", "N", "M", "O", "Mm"}) {
			Assert.assertTrue(bst.contains(key));
		}
	}

	@Test
	public void deleteRightNodeTest6() {
		BinarySearchTree<String,String> bst =
			new BinarySearchTree<String,String>();
		// create the tree
					bst.insert("H", "");
		bst.insert("D","");			bst.insert("L","");
						bst.insert("J","");		bst.insert("N","");
														bst.insert("O","");
											
		// delete the right node
		bst.delete("L");

		// ensure right node was deleted
		Assert.assertFalse(bst.contains("L"));

		// ensure other nodes were not lost
		for(String key : new String[] {"H", "D", "J", "N", "O"}) {
			Assert.assertTrue(bst.contains(key));
		}
	}

	@Test
	public void deleteLeftNodeTest1() {
		BinarySearchTree<String,String> bst =
			new BinarySearchTree<String,String>();
		// create the tree
					bst.insert("H", "");
		bst.insert("D","");		bst.insert("L","");

		// delete the left node
		bst.delete("D");

		// ensure left node was deleted
		Assert.assertFalse(bst.contains("D"));

		// ensure other nodes were not lost
		for(String key : new String[] {"H", "L"}) {
			Assert.assertTrue(bst.contains(key));
		}
	}

	@Test
	public void deleteLeftNodeTest2() {
		BinarySearchTree<String,String> bst =
			new BinarySearchTree<String,String>();
		// create the tree
								bst.insert("H", "");
				bst.insert("D","");				bst.insert("L","");
		bst.insert("B","");

		// delete the left node
		bst.delete("D");

		// ensure left node was deleted
		Assert.assertFalse(bst.contains("D"));

		// ensure other nodes were not lost
		for(String key : new String[] {"H", "L", "B"}) {
			Assert.assertTrue(bst.contains(key));
		}
	}

	@Test
	public void deleteLeftNodeTest3() {
		BinarySearchTree<String,String> bst =
			new BinarySearchTree<String,String>();
		// create the tree
						bst.insert("H", "");
		bst.insert("D","");				bst.insert("L","");
				bst.insert("F","");

		// delete the left node
		bst.delete("D");

		// ensure left node was deleted
		Assert.assertFalse(bst.contains("D"));

		// ensure other nodes were not lost
		for(String key : new String[] {"H", "F", "L"}) {
			Assert.assertTrue(bst.contains(key));
		}
	}
	
	@Test
	public void deleteLeftNodeTest4() {
		BinarySearchTree<String,String> bst =
			new BinarySearchTree<String,String>();
		// create the tree
									bst.insert("H", "");
					bst.insert("D","");				bst.insert("L","");
		bst.insert("B","");		bst.insert("F","");

		// delete the left node
		bst.delete("D");

		// ensure left node was deleted
		Assert.assertFalse(bst.contains("D"));

		// ensure other nodes were not lost
		for(String key : new String[] {"H", "L", "B", "F"}) {
			Assert.assertTrue(bst.contains(key));
		}
	}

	@Test
	public void deleteLeftNodeTest5() {
		BinarySearchTree<String,String> bst =
			new BinarySearchTree<String,String>();
		// create the tree
									bst.insert("H", "");
					bst.insert("D","");				bst.insert("L","");
		bst.insert("B","");		bst.insert("F","");
						bst.insert("E","");	bst.insert("G","");
							bst.insert("Ee","");

		// delete the left node
		bst.delete("D");

		// ensure left node was deleted
		Assert.assertFalse(bst.contains("D"));

		// ensure other nodes were not lost
		for(String key : new String[] {"H", "L", "B", "F", "Ee", "E", "G"}) {
			Assert.assertTrue(bst.contains(key));
		}
	}

	@Test
	public void deleteLeftNodeTest6() {
		BinarySearchTree<String,String> bst =
			new BinarySearchTree<String,String>();
		// create the tree
									bst.insert("H", "");
					bst.insert("D","");				bst.insert("L","");
		bst.insert("B","");		bst.insert("F","");
										bst.insert("G","");
											
		// delete the left node
		bst.delete("D");

		// ensure left node was deleted
		Assert.assertFalse(bst.contains("D"));

		// ensure other nodes were not lost
		for(String key : new String[] {"H", "L", "B", "F", "G", "L"}) {
			Assert.assertTrue(bst.contains(key));
		}
	}
}