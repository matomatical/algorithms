import org.junit.Assert;
import org.junit.Test;

import com.matomatical.ads.EmptyException;
import com.matomatical.ads.PriorityQueue;

public class PriorityQueueTest {

	@Test
	public void newQueueShouldBeEmpty(){
		PriorityQueue<String> pq = new PriorityQueue<String>();

		boolean empty = pq.isEmpty();

		Assert.assertTrue(empty);
	}

	@Test
	public void queueAddedToShouldNotBeEmpty(){
		PriorityQueue<String> pq = new PriorityQueue<String>();
		pq.add("");

		boolean empty = pq.isEmpty();

		Assert.assertFalse(empty);
	}

	@Test
	public void newQueueWithItemsShouldNotBeEmpty() {
		String[] items = {"hello,", " ", "world!"};
		PriorityQueue<String> pq = new PriorityQueue<String>(items);

		boolean empty = pq.isEmpty();

		Assert.assertFalse(empty);
	}

	@Test
	public void removingAnItemFromQueueShouldNotMakeItEmpty() {
		String[] items = {"hello,", " ", "world!"};
		PriorityQueue<String> pq = new PriorityQueue<String>(items);


		pq.removeMax();
		boolean empty = pq.isEmpty();

		Assert.assertFalse(empty);
	}

	@Test
	public void removingAllButOneItemsFromQueueShouldNotMakeItEmpty() {
		String[] items = {"hello,", " ", "world!"};
		PriorityQueue<String> pq = new PriorityQueue<String>(items);


		pq.removeMax(); pq.removeMax();
		boolean empty = pq.isEmpty();

		Assert.assertFalse(empty);
	}


	@Test
	public void peekingAtTheLastItemInQueueShouldNotMakeItEmpty() {
		String[] items = {"just one item"};
		PriorityQueue<String> pq = new PriorityQueue<String>(items);
		
		pq.max();
		boolean empty = pq.isEmpty();

		Assert.assertFalse(empty);
	}

	@Test
	public void removingAllItemsFromQueueShouldMakeItEmpty() {
		String[] items = {"hello,", " ", "world!"};
		PriorityQueue<String> pq = new PriorityQueue<String>(items);
		pq.removeMax();
		pq.removeMax();
		pq.removeMax();

		boolean empty = pq.isEmpty();

		Assert.assertTrue(empty);
	}

	@Test
	public void newQueueShouldHaveSizeZero(){
		PriorityQueue<String> pq = new PriorityQueue<String>();

		int size = pq.size();

		Assert.assertEquals(0, size);
	}

	@Test
	public void addingItemShouldIncreaseSize(){
		PriorityQueue<String> pq = new PriorityQueue<String>();
		pq.add("");

		int size = pq.size();

		Assert.assertEquals(1, size);
	}

	@Test
	public void addingItemsShouldIncreaseSize(){
		PriorityQueue<String> pq = new PriorityQueue<String>();
		pq.add(""); pq.add(""); pq.add("");

		int size = pq.size();

		Assert.assertEquals(3, size);
	}

	@Test
	public void newQueueWithItemsShouldHaveAppropriateSize() {
		String[] items = {"hello,", " ", "world!"};
		PriorityQueue<String> pq = new PriorityQueue<String>(items);

		int size = pq.size();

		Assert.assertEquals(items.length, size);
	}

	@Test
	public void removingAnItemFromQueueShouldReduceSize() {
		String[] items = {"hello,", " ", "world!"};
		PriorityQueue<String> pq = new PriorityQueue<String>(items);

		pq.removeMax();
		int size = pq.size();

		int expected = items.length - 1;
		Assert.assertEquals(expected, size);
	}

	@Test
	public void removingItemsFromQueueShouldReduceSize(){
		String[] items = {"hello,", " ", "world!"};
		PriorityQueue<String> pq = new PriorityQueue<String>(items);
		pq.removeMax();
		pq.removeMax();

		int size = pq.size();

		int expected = items.length - 2;
		Assert.assertEquals(expected, size);
	}

	@Test
	public void peekingAtItemsShouldNotChangeSize() {
		String[] items = {"one","two","three"};
		PriorityQueue<String> pq = new PriorityQueue<String>(items);
		
		int before = pq.size();
		pq.max();
		int after = pq.size();

		Assert.assertEquals(before, after);
	}

	@Test
	public void addingAndRemovingItemsShouldChangeSize() {
		PriorityQueue<String> pq = new PriorityQueue<String>();
		pq.add("hello,");	// size = 1
		pq.add("hello.");	// size = 2
		pq.add("hello ");	// size = 3
		pq.removeMax();		// size = 2
		pq.removeMax();		// size = 1
		pq.add("hello?");	// size = 2
		pq.add("hello!");	// size = 3
		pq.removeMax();		// size = 2?

		int size = pq.size();

		Assert.assertEquals(2, size);
	}

	@Test
	public void addThenRemoveShouldGiveSameItemBack() {
		PriorityQueue<String> pq = new PriorityQueue<String>();
		String test = "test string";
		pq.add(test);

		String result = pq.removeMax();

		Assert.assertTrue(test == result); // reference equality check
	}

	@Test
	public void addThenPeekShouldShowSameItem() {
		PriorityQueue<String> pq = new PriorityQueue<String>();
		String test = "test string";
		pq.add(test);

		String result = pq.max();

		Assert.assertTrue(test == result); // reference equality check
	}

	@Test
	public void peekThenRemoveShouldShowSameItem() {
		String[] items = {"string", "array", "with", "strings"};
		PriorityQueue<String> pq = new PriorityQueue<String>(items);

		String peek = pq.max();
		String remove = pq.removeMax();

		Assert.assertTrue(peek == remove); // reference equality check
	}

	@Test
	public void multiplePeeksShouldShowSameItem() {
		String[] items = {"string", "array", "with", "strings"};
		PriorityQueue<String> pq = new PriorityQueue<String>(items);

		String peek1 = pq.max();
		String peek2 = pq.max();

		Assert.assertTrue(peek1 == peek2); // reference equality check
	}

	@Test
	public void removeThenPeekShouldNotShowSameItem() {
		String[] items = {"string", "array", "with", "strings"};
		PriorityQueue<String> pq = new PriorityQueue<String>(items);

		String remove = pq.removeMax();
		String peek = pq.max();

		Assert.assertFalse(remove == peek); // reference equality check
	}

	@Test(expected = EmptyException.class)
	public void removeFromEmptyQueueShouldThrowEmptyException() {
		PriorityQueue<String> pq = new PriorityQueue<String>();

		pq.removeMax();

		// require exception to pass test
		Assert.fail("missing EmptyException");
	}

	@Test(expected = EmptyException.class)
	public void peekAtEmptyQueueShouldThrowEmptyException() {
		PriorityQueue<String> pq = new PriorityQueue<String>();

		pq.max();

		// require exception to pass test
		Assert.fail("missing EmptyException");
	}

	@Test
	public void addHighPriorityItemShouldSkipToFrontOfQueue() {
		Integer[] lows = {5, 6, 7, 8, 2, 5, 1, 8, 7, 4, 5};
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(lows);
		int high = 100;
		
		pq.add(high);
		int max = pq.max();

		Assert.assertEquals(high, max);
	}

	@Test
	public void repeatedRemovalsShouldBeInDecreasingPriorityOrder() {
		Integer[] items = {5, 6, 7, 8, 2, 5, 1, 8, 7, 4, 5};
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(items);

		// repeatedly remove and compare against previous removal
		// to ensure we are always decreasing

		int previous = pq.removeMax();
		while ( ! pq.isEmpty()) {
			int next = pq.removeMax();
			Assert.assertTrue(next <= previous);
			previous = next;
		}
	}

	@Test
	public void repeatedRemovalsAfterAddingShouldBeInDecreasingPriorityOrder() {
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		pq.add(5); pq.add(6); pq.add(7); pq.add(8); pq.add(2); pq.add(5);
		pq.add(1); pq.add(6); pq.add(8); pq.add(7); pq.add(4); pq.add(5);

		// repeatedly remove and compare against previous removal
		// to ensure we are always decreasing

		int previous = pq.removeMax();
		while ( ! pq.isEmpty()) {
			int next = pq.removeMax();
			Assert.assertTrue(next <= previous);
			previous = next;
		}
	}

	@Test
	public void addingInArbitraryOrderShouldPutMaximumItemAtTop() {
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		pq.add(5); pq.add(6); pq.add(7); pq.add(100); pq.add(2); pq.add(5);
		pq.add(1); pq.add(6); pq.add(8); pq.add(7); pq.add(4); pq.add(5);

		int max = pq.max();

		Assert.assertEquals(100, max);
	}

	@Test
	public void addingInReverseOrderShouldPutMaximumItemAtTop() {
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		pq.add(1); pq.add(2); pq.add(3); pq.add(4); pq.add(5); pq.add(6);
		pq.add(7); pq.add(8); pq.add(9); pq.add(10); pq.add(11); pq.add(100);

		int max = pq.max();
	
		Assert.assertEquals(100, max);	
	}

	@Test
	public void constructorShouldPutMaximumItemAtTopFromBottom() {
		Integer[] items = {1, 2, 3, 4, 5, 6, 7, 8, 100};
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(items);

		int max = pq.max();

		Assert.assertEquals(100, max);
	}

	@Test
	public void constructorShouldPutMaximumItemAtTopFromMiddle() {
		Integer[] items = {1, 2, 3, 100, 4, 5, 6, 7, 8};
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(items);

		int max = pq.max();

		Assert.assertEquals(100, max);
	}

	@Test
	public void constructorShouldLeaveMaximumItemAtTop() {
		Integer[] items = {100, 1, 2, 3, 4, 5, 6, 7, 8};
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(items);

		int max = pq.max();

		Assert.assertEquals(100, max);
	}
}