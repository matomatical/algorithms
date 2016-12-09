import org.junit.Assert;
import org.junit.Test;

import com.matomatical.ads.Queue;
import com.matomatical.ads.EmptyException;

public class QueueTest {

	@Test
	public void enqueueingThenDequeueingShouldGiveSameElement(){
		Queue<String> queue = new Queue<String>();
		String element = "element";
		queue.enqueue(element);

		String result = queue.dequeue();

		Assert.assertEquals(element, result);
	}

	@Test
	public void dequeueingShouldGiveFirstEnqueuedElement(){
		Queue<String> queue = new Queue<String>();
		String element = "first string added";
		queue.enqueue(element);
		queue.enqueue("next string added");
		queue.enqueue("third string added");
		queue.enqueue("final string added");
		
		String result = queue.dequeue();

		// the least recently added element should be at the front

		Assert.assertEquals(element, result);
	}

	@Test
	public void emptyQueueDequeueShouldFail(){
		Queue<String> queue = new Queue<String>();

		try {
			// try to remove from an empty queue
			// should throw an EmptyException
			queue.dequeue();
			Assert.fail("missing EmptyException");

		} catch (EmptyException e) {
			// success! passed the test!
		}
	}

	@Test
	public void nonEmptyQueueDequeueShouldNotThrowException(){
		Queue<String> queue = new Queue<String>();
		queue.enqueue("hi");

		try {
			// try to remove from a non-empty queue
			// should NOT throw an EmptyException
			queue.dequeue();
			
			// success! passed the test!

		} catch (EmptyException e) {
			Assert.fail("unecessary EmptyException");
		}
	}

	@Test
	public void nonEmptyQueueDequeuesShouldNotThrowException(){
		Queue<String> queue = new Queue<String>();
		queue.enqueue("hi");
		queue.enqueue("");
		queue.dequeue(); // remove one of the two items, not both!

		try {
			// try to remove from a non-empty queue
			// should NOT throw an EmptyException
			queue.dequeue();
			
			// success! passed the test!

		} catch (EmptyException e) {
			Assert.fail("unecessary EmptyException");
		}
	}

	@Test
	public void newQueueShouldBeEmpty(){
		Queue<String> queue = new Queue<String>();

		boolean empty = queue.isEmpty();

		Assert.assertTrue(empty);
	}

	@Test
	public void enqueuedQueueShouldNotBeEmpty(){
		Queue<String> queue = new Queue<String>();
		queue.enqueue("");

		boolean empty = queue.isEmpty();

		Assert.assertFalse(empty);
	}

	@Test
	public void dequeuedQueueShouldBeEmpty(){
		Queue<String> queue = new Queue<String>();
		queue.enqueue("");
		queue.dequeue();

		boolean empty = queue.isEmpty();

		Assert.assertTrue(empty);
	}

	@Test
	public void dequeueingSomeItemsShouldNotMakeQueueEmpty(){
		Queue<String> queue = new Queue<String>();
		queue.enqueue("hi");
		queue.enqueue("");
		queue.dequeue(); // remove one of the two items, not both!

		boolean empty = queue.isEmpty();

		Assert.assertFalse(empty);
	}

	@Test
	public void newQueueShouldHaveZeroSize(){
		Queue<String> queue = new Queue<String>();

		int size = queue.size();

		Assert.assertEquals(0, size);
	}

	@Test
	public void enqueueingElementShouldIncreaseSize(){
		Queue<String> queue = new Queue<String>();
		queue.enqueue("hello, world!");

		int size = queue.size();

		Assert.assertEquals(1, size);
	}

	@Test
	public void enqueueingElementsShouldIncreaseSize(){
		Queue<String> queue = new Queue<String>();
		queue.enqueue("hello,");
		queue.enqueue(" ");
		queue.enqueue("world!");

		int size = queue.size();

		Assert.assertEquals(3, size);
	}

	@Test
	public void enqueuingAndDequeuingElementsShouldChangeSize(){
		Queue<String> queue = new Queue<String>();	// size = 0
		queue.enqueue("hello,");	// size = 1
		queue.enqueue(" ");			// size = 2
		queue.enqueue("world!");	// size = 3
		queue.dequeue();			// size = 2
		queue.dequeue();			// size = 1
		queue.enqueue(" ");			// size = 2
		queue.enqueue("world!");	// size = 3
		queue.dequeue();			// size = 2?

		int size = queue.size();

		Assert.assertEquals(2, size);
	}
}