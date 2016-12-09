import org.junit.Assert;
import org.junit.Test;

import com.matomatical.ads.DoublyLinkedList;
import com.matomatical.ads.EmptyException;

public class DoublyLinkedListTest {


	@Test
	public void addingThenRemovingShouldGiveSameElement(){
		DoublyLinkedList<String> list = new DoublyLinkedList<String>();
		String element = "element";
		list.add(element);

		String result = list.remove();

		Assert.assertEquals(element, result);
	}

	@Test
	public void addingThenRemovingAtEndShouldGiveSameElement(){
		DoublyLinkedList<String> list = new DoublyLinkedList<String>();
		String element = "element";
		list.add(element);

		String result = list.removeEnd();

		Assert.assertEquals(element, result);
	}

	@Test
	public void addingAtEndThenRemovingShouldGiveSameElement(){
		DoublyLinkedList<String> list = new DoublyLinkedList<String>();
		String element = "element";
		list.addEnd(element);

		String result = list.remove();

		Assert.assertEquals(element, result);
	}

	@Test
	public void addingAtEndThenRemovingAtEndShouldGiveSameElement(){
		DoublyLinkedList<String> list = new DoublyLinkedList<String>();
		String element = "element";
		list.addEnd(element);

		String result = list.removeEnd();

		Assert.assertEquals(element, result);
	}

	@Test
	public void removingShouldGiveFrontMostElement(){
		DoublyLinkedList<String> list = new DoublyLinkedList<String>();
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
	public void removingAtEndShouldGiveLastElement(){
		DoublyLinkedList<String> list = new DoublyLinkedList<String>();
		String element = "first string added";
		list.add(element);
		list.add("next string added");
		list.add("third string added");
		list.add("final string added");

		String result = list.removeEnd();

		// the most first added element should be at the end

		Assert.assertEquals(element, result);
	}

	@Test
	public void removingShouldGiveFirstElementAddedAtEnd(){
		DoublyLinkedList<String> list = new DoublyLinkedList<String>();
		String element = "first string added";
		list.addEnd(element);
		list.addEnd("next string added");
		list.addEnd("third string added");
		list.addEnd("final string added");
		
		String result = list.remove();

		// the first added element to the end should be at the front

		Assert.assertEquals(element, result);
	}

	@Test
	public void removingAtEndShouldGiveLastElementAddedAtEnd(){
		DoublyLinkedList<String> list = new DoublyLinkedList<String>();
		list.addEnd("first string added");
		list.addEnd("next string added");
		list.addEnd("third string added");
		String element = "final string added";
		list.addEnd(element);

		String result = list.removeEnd();

		// the most recently added element to end should be at the end

		Assert.assertEquals(element, result);
	}

	@Test
	public void emptyListShouldFailToRemove(){
		DoublyLinkedList<String> list = new DoublyLinkedList<String>();

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
	public void emptyListShouldFailToRemoveAtEnd(){
		DoublyLinkedList<String> list = new DoublyLinkedList<String>();

		try {
			// try to remove from an empty list
			// should throw an EmptyException
			list.removeEnd();
			Assert.fail("missing EmptyException");

		} catch (EmptyException e) {
			// success! passed the test!
		}
	}

	@Test
	public void nonEmptyListShouldSucceedAtRemoving(){
		DoublyLinkedList<String> list = new DoublyLinkedList<String>();
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
		DoublyLinkedList<String> list = new DoublyLinkedList<String>();
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
	public void removingSomeItemsAtEndShouldNotMakeRemoveFail(){
		DoublyLinkedList<String> list = new DoublyLinkedList<String>();
		list.add("hi");
		list.add("");
		list.removeEnd(); // remove one of the two items, not both!

		try {
			// try to remove from a non-empty list
			// should NOT throw an EmptyException
			list.removeEnd();
			
			// success! passed the test!

		} catch (EmptyException e) {
			Assert.fail("unecessary EmptyException");
		}
	}

	@Test
	public void newListShouldBeEmpty(){
		DoublyLinkedList<String> list = new DoublyLinkedList<String>();

		boolean empty = list.isEmpty();

		Assert.assertTrue(empty);
	}

	@Test
	public void listWithElementsShouldNotBeEmpty(){
		DoublyLinkedList<String> list = new DoublyLinkedList<String>();
		list.add("");

		boolean empty = list.isEmpty();

		Assert.assertFalse(empty);
	}

	@Test
	public void emptiedListShouldBeEmpty(){
		DoublyLinkedList<String> list = new DoublyLinkedList<String>();
		list.add("");
		list.remove();

		boolean empty = list.isEmpty();

		Assert.assertTrue(empty);
	}

	@Test
	public void removingSomeItemsShouldNotMakeListEmpty(){
		DoublyLinkedList<String> list = new DoublyLinkedList<String>();
		list.add("hi");
		list.add("");
		list.remove(); // remove one of the two items, not both!

		boolean empty = list.isEmpty();

		Assert.assertFalse(empty);
	}

	@Test
	public void newListShouldHaveZeroLength(){
		DoublyLinkedList<String> list = new DoublyLinkedList<String>();

		int length = list.length();

		Assert.assertEquals(0, length);
	}

	@Test
	public void addingElementShouldIncreaseLength(){
		DoublyLinkedList<String> list = new DoublyLinkedList<String>();
		list.add("hello, world!");

		int length = list.length();

		Assert.assertEquals(1, length);
	}

	@Test
	public void addingElementsShouldIncreaseLength(){
		DoublyLinkedList<String> list = new DoublyLinkedList<String>();
		list.add("hello,");
		list.add(" ");
		list.add("world!");

		int length = list.length();

		Assert.assertEquals(3, length);
	}

	@Test
	public void addingElementAtEndShouldIncreaseLength(){
		DoublyLinkedList<String> list = new DoublyLinkedList<String>();
		list.addEnd("hello, world!");

		int length = list.length();

		Assert.assertEquals(1, length);
	}

	@Test
	public void addingElementsAtEndShouldIncreaseLength(){
		DoublyLinkedList<String> list = new DoublyLinkedList<String>();
		list.addEnd("hello,");
		list.addEnd(" ");
		list.addEnd("world!");

		int length = list.length();

		Assert.assertEquals(3, length);
	}

	@Test
	public void addingAndRemovingElementsShouldChangeLength(){
		DoublyLinkedList<String> list = new DoublyLinkedList<String>();	// length = 0
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

	@Test
	public void addingAndRemovingElementsAtEndShouldChangeLength(){
		DoublyLinkedList<String> list = new DoublyLinkedList<String>();	// length = 0
		list.addEnd("hello,");	// length = 1
		list.addEnd(" ");		// length = 2
		list.addEnd("world!");	// length = 3
		list.removeEnd();		// length = 2
		list.removeEnd();		// length = 1
		list.addEnd(" ");		// length = 2
		list.addEnd("world!");	// length = 3
		list.removeEnd();		// length = 2?

		int length = list.length();

		Assert.assertEquals(2, length);
	}
}