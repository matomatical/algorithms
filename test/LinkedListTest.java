import org.junit.Assert;
import org.junit.Test;

import com.matomatical.ads.LinkedList;
import com.matomatical.ads.EmptyException;

public class LinkedListTest {


	@Test
	public void addingThenRemovingShouldGiveSameElement(){
		LinkedList<String> list = new LinkedList<String>();
		String element = "element";
		list.add(element);

		String result = list.remove();

		Assert.assertEquals(element, result);
	}

	@Test
	public void removingShouldGiveFrontMostElement(){
		LinkedList<String> list = new LinkedList<String>();
		list.add("first string added");
		list.add("next string added");
		list.add("third string added");
		String element = "final string added";
		list.add(element);

		String result = list.remove();

		// the most recently added element should be at the front

		Assert.assertEquals(element, result);
	}

	@Test
	public void emptyListShouldFailToRemove(){
		LinkedList<String> list = new LinkedList<String>();

		try {
			// try to remove from an empty list
			// should throw an EmptyException
			list.remove();
			Assert.fail("missing EmptyException");

		} catch (EmptyException e) {
			// success! passed the test!
		}
	}

	@Test
	public void nonEmptyListShouldSucceedAtRemoving(){
		LinkedList<String> list = new LinkedList<String>();
		list.add("hi");

		try {
			// try to remove from a non-empty list
			// should NOT throw an EmptyException
			list.remove();
			
			// success! passed the test!

		} catch (EmptyException e) {
			Assert.fail("unecessary EmptyException");
		}
	}

	@Test
	public void removingSomeItemsShouldNotMakeRemoveFail(){
		LinkedList<String> list = new LinkedList<String>();
		list.add("hi");
		list.add("");
		list.remove(); // remove one of the two items, not both!

		try {
			// try to remove from a non-empty list
			// should NOT throw an EmptyException
			list.remove();
			
			// success! passed the test!

		} catch (EmptyException e) {
			Assert.fail("unecessary EmptyException");
		}
	}


	@Test
	public void newListShouldBeEmpty(){
		LinkedList<String> list = new LinkedList<String>();

		boolean empty = list.isEmpty();

		Assert.assertTrue(empty);
	}

	@Test
	public void listWithElementsShouldNotBeEmpty(){
		LinkedList<String> list = new LinkedList<String>();
		list.add("");

		boolean empty = list.isEmpty();

		Assert.assertFalse(empty);
	}

	@Test
	public void emptiedListShouldBeEmpty(){
		LinkedList<String> list = new LinkedList<String>();
		list.add("");
		list.remove();

		boolean empty = list.isEmpty();

		Assert.assertTrue(empty);
	}

	@Test
	public void removingSomeItemsShouldNotMakeListEmpty(){
		LinkedList<String> list = new LinkedList<String>();
		list.add("hi");
		list.add("");
		list.remove(); // remove one of the two items, not both!

		boolean empty = list.isEmpty();

		Assert.assertFalse(empty);
	}

	@Test
	public void newListShouldHaveZeroLength(){
		LinkedList<String> list = new LinkedList<String>();

		int length = list.length();

		Assert.assertEquals(0, length);
	}

	@Test
	public void addingElementShouldIncreaseLength(){
		LinkedList<String> list = new LinkedList<String>();
		list.add("hello, world!");

		int length = list.length();

		Assert.assertEquals(1, length);
	}

	@Test
	public void addingElementsShouldIncreaseLength(){
		LinkedList<String> list = new LinkedList<String>();
		list.add("hello,");
		list.add(" ");
		list.add("world!");

		int length = list.length();

		Assert.assertEquals(3, length);
	}

	@Test
	public void addingAndRemovingElementsShouldChangeLength(){
		LinkedList<String> list = new LinkedList<String>();	// length = 0
		list.add("hello,");	// length = 1
		list.add(" ");		// length = 2
		list.add("world!");	// length = 3
		list.remove();		// length = 2
		list.remove();		// length = 1
		list.add(" ");		// length = 2
		list.add("world!");	// length = 3
		list.remove();		// length = 2?

		int length = list.length();

		Assert.assertEquals(2, length);
	}

}