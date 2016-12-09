import org.junit.Assert;
import org.junit.Test;

import com.matomatical.ads.Stack;
import com.matomatical.ads.EmptyException;

public class StackTest {

	@Test
	public void pushingThenPoppingShouldGiveSameElement(){
		Stack<String> stack = new Stack<String>();
		String element = "element";
		stack.push(element);

		String result = stack.pop();

		Assert.assertEquals(element, result);
	}

	@Test
	public void poppingShouldGiveTopMostElement(){
		Stack<String> stack = new Stack<String>();
		stack.push("first string added");
		stack.push("next string added");
		stack.push("third string added");
		String element = "final string added";
		stack.push(element);

		String result = stack.pop();

		// the most recently added element should be at the front

		Assert.assertEquals(element, result);
	}

	@Test
	public void emptyStackPopShouldFail(){
		Stack<String> stack = new Stack<String>();

		try {
			// try to remove from an empty stack
			// should throw an EmptyException
			stack.pop();
			Assert.fail("missing EmptyException");

		} catch (EmptyException e) {
			// success! passed the test!
		}
	}

	@Test
	public void nonEmptyStackPopShouldNotThrowException(){
		Stack<String> stack = new Stack<String>();
		stack.push("hi");

		try {
			// try to remove from a non-empty stack
			// should NOT throw an EmptyException
			stack.pop();
			
			// success! passed the test!

		} catch (EmptyException e) {
			Assert.fail("unecessary EmptyException");
		}
	}

	@Test
	public void nonEmptyStackPopsShouldNotThrowException(){
		Stack<String> stack = new Stack<String>();
		stack.push("hi");
		stack.push("");
		stack.pop(); // remove one of the two items, not both!

		try {
			// try to remove from a non-empty stack
			// should NOT throw an EmptyException
			stack.pop();
			
			// success! passed the test!

		} catch (EmptyException e) {
			Assert.fail("unecessary EmptyException");
		}
	}

	@Test
	public void newStackShouldBeEmpty(){
		Stack<String> stack = new Stack<String>();

		boolean empty = stack.isEmpty();

		Assert.assertTrue(empty);
	}

	@Test
	public void pushedStackShouldNotBeEmpty(){
		Stack<String> stack = new Stack<String>();
		stack.push("");

		boolean empty = stack.isEmpty();

		Assert.assertFalse(empty);
	}

	@Test
	public void poppedStackShouldBeEmpty(){
		Stack<String> stack = new Stack<String>();
		stack.push("");
		stack.pop();

		boolean empty = stack.isEmpty();

		Assert.assertTrue(empty);
	}

	@Test
	public void poppingSomeItemsShouldNotMakeStackEmpty(){
		Stack<String> stack = new Stack<String>();
		stack.push("hi");
		stack.push("");
		stack.pop(); // remove one of the two items, not both!

		boolean empty = stack.isEmpty();

		Assert.assertFalse(empty);
	}

	@Test
	public void newStackShouldHaveZeroSize(){
		Stack<String> stack = new Stack<String>();

		int size = stack.size();

		Assert.assertEquals(0, size);
	}

	@Test
	public void pushingElementShouldIncreaseSize(){
		Stack<String> stack = new Stack<String>();
		stack.push("hello, world!");

		int size = stack.size();

		Assert.assertEquals(1, size);
	}

	@Test
	public void pushingElementsShouldIncreaseSize(){
		Stack<String> stack = new Stack<String>();
		stack.push("hello,");
		stack.push(" ");
		stack.push("world!");

		int size = stack.size();

		Assert.assertEquals(3, size);
	}

	@Test
	public void pushingAndPoppingElementsShouldChangeSize(){
		Stack<String> stack = new Stack<String>();	// size = 0
		stack.push("hello,");	// size = 1
		stack.push(" ");		// size = 2
		stack.push("world!");	// size = 3
		stack.pop();			// size = 2
		stack.pop();			// size = 1
		stack.push(" ");		// size = 2
		stack.push("world!");	// size = 3
		stack.pop();			// size = 2?

		int size = stack.size();

		Assert.assertEquals(2, size);
	}
}