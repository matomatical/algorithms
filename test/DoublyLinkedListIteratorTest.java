import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import com.matomatical.ads.DoublyLinkedList;

public class DoublyLinkedListIteratorTest {

	@Test
	public void iteratorShouldIterateThroughElementsInOrder(){
		DoublyLinkedList<String> list = new DoublyLinkedList<String>();
		list.add("hello,");
		list.addEnd(" ");
		list.addEnd("world!");

		Iterator<String> iterator = list.iterator();
		String one = iterator.next();
		String two = iterator.next();
		String three = iterator.next();

		Assert.assertEquals("hello,", one);
		Assert.assertEquals(" ", two);
		Assert.assertEquals("world!", three);
	}

	@Test
	public void hasNextShouldBeTrueToBeingWithIfThereAreItems(){
		DoublyLinkedList<String> list = new DoublyLinkedList<String>();
		list.add("hello,");
		list.addEnd(" ");
		list.addEnd("world!");

		Iterator<String> iterator = list.iterator();
		boolean hasNext = iterator.hasNext();
		
		Assert.assertTrue(hasNext);
	}

	@Test
	public void hasNextShouldBeTrueWhenThereAreMoreItems(){
		DoublyLinkedList<String> list = new DoublyLinkedList<String>();
		list.add("hello,");
		list.addEnd(" ");
		list.addEnd("world!");

		Iterator<String> iterator = list.iterator();
		iterator.next();
		boolean hasNext = iterator.hasNext();
		
		Assert.assertTrue(hasNext);
	}

	@Test
	public void hasNextShouldBeTrueWhenThereIsOneMoreItem(){
		DoublyLinkedList<String> list = new DoublyLinkedList<String>();
		list.add("hello,");
		list.addEnd(" ");
		list.addEnd("world!");

		Iterator<String> iterator = list.iterator();
		iterator.next(); iterator.next();
		boolean hasNext = iterator.hasNext();
		
		Assert.assertTrue(hasNext);
	}

	@Test
	public void hasNextShouldBeFalseWhenThereAreNoMoreItems(){
		DoublyLinkedList<String> list = new DoublyLinkedList<String>();
		list.add("hello,");
		list.addEnd(" ");
		list.addEnd("world!");

		Iterator<String> iterator = list.iterator();
		iterator.next(); iterator.next(); iterator.next();
		boolean hasNext = iterator.hasNext();
		
		Assert.assertFalse(hasNext);
	}

	@Test
	public void hasNextShouldBeFalseWhenThereAreNoItemsToBeginWith(){
		DoublyLinkedList<String> list = new DoublyLinkedList<String>();

		Iterator<String> iterator = list.iterator();
		boolean hasNext = iterator.hasNext();
		
		Assert.assertFalse(hasNext);
	}

	@Test
	public void nextShouldNotThrowAnExceptionWhenThereAreMoreItems(){
		DoublyLinkedList<String> list = new DoublyLinkedList<String>();
		list.add("hello,");
		list.addEnd(" ");
		list.addEnd("world!");

		Iterator<String> iterator = list.iterator();
		
		try {
			iterator.next();
			// passed test! no exception

		} catch (NoSuchElementException e) {
			// there are more elements, this should not
			// throw an exception!
			Assert.fail();
		}
	}

	@Test
	public void nextShouldNotThrowAnExceptionWhenThereIsOneMoreItem(){
		DoublyLinkedList<String> list = new DoublyLinkedList<String>();
		list.add("hello,");
		list.addEnd(" ");
		list.addEnd("world!");

		Iterator<String> iterator = list.iterator();
		iterator.next(); iterator.next();

		try {
			iterator.next();
			// passed test! no exception

		} catch (NoSuchElementException e) {
			// there are more elements, this should not
			// throw an exception!
			Assert.fail();
		}
	}

	@Test
	public void nextShouldThrowAnExceptionWhenThereAreNoMoreItems(){
		DoublyLinkedList<String> list = new DoublyLinkedList<String>();
		list.add("hello,");
		list.addEnd(" ");
		list.addEnd("world!");

		Iterator<String> iterator = list.iterator();
		iterator.next(); iterator.next(); iterator.next();

		try {
			iterator.next();
			// missing exception
			Assert.fail();

		} catch (NoSuchElementException e) {
			// correctly thrown exception, pass test
		}
	}

	@Test
	public void nextShouldThrowAnExceptionWhenThereAreNoItemsToBeginWith(){
		DoublyLinkedList<String> list = new DoublyLinkedList<String>();

		Iterator<String> iterator = list.iterator();

		try {
			iterator.next();
			// missing exception
			Assert.fail();

		} catch (NoSuchElementException e) {
			// correctly thrown exception, pass test
		}
	}
}