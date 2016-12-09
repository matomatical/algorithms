import org.junit.Assert;
import org.junit.Test;

import com.matomatical.ads.DynamicArray;
import com.matomatical.ads.IndexException;

public class DynamicArrayTest {

	@Test
	public void addingThenRemovingShouldGiveSameElement(){
		DynamicArray<String> a = new DynamicArray<String>();
		String element = "element";
		a.add(element);

		String result = a.remove();

		Assert.assertEquals(element, result);
	}

	@Test
	public void gettingShouldFindElementsInOrderAdded(){
		DynamicArray<String> a = new DynamicArray<String>();
		a.add("hello,"); a.add(" "); a.add("world!");

		String zero = a.get(0);
		String one = a.get(1);
		String two = a.get(2);

		Assert.assertEquals("hello,", zero);
		Assert.assertEquals(" ", one);
		Assert.assertEquals("world!", two);
	}

	@Test
	public void gettingWithinRangeShouldNotThrowAnException(){
		DynamicArray<String> a = new DynamicArray<String>();
		a.add("hello,"); a.add(" "); a.add("world!");

		try {
			a.get(0);
			a.get(1);
			a.get(2);
		} catch (IndexException e) {
			Assert.fail("unexpected IndexException");
		}
	}

	@Test
	public void gettingBelowZeroShouldThrowAnException(){
		DynamicArray<String> a = new DynamicArray<String>();
		a.add("hello,"); a.add(" "); a.add("world!");

		try {
			a.get(-1);
			
			Assert.fail("missing IndexException");

		} catch (IndexException e) {
			// pass :)

		} catch (Exception e) {
			// some other exception was thrown
			Assert.fail("hey! we expected an IndexException");
		}
	}

	@Test
	public void gettingOnePastEndShouldThrowAnException(){
		DynamicArray<String> a = new DynamicArray<String>();
		a.add("hello,"); a.add(" "); a.add("world!");

		try {
			a.get(3);
			
			Assert.fail("missing IndexException");
			
		} catch (IndexException e) {
			// pass :)

		} catch (Exception e) {
			// some other exception was thrown
			Assert.fail("hey! we expected an IndexException");
		}
	}
	
	@Test
	public void gettingPastEndButWithinCapacityShouldThrowAnException(){
		DynamicArray<String> a = new DynamicArray<String>();
		a.add("hello,"); a.add(" "); a.add("world!");
		a.add("hello,"); a.add(" "); a.add("world!");
		a.add("hello,"); a.add(" "); a.add("world!");

		try {
			a.get(10);
			
			Assert.fail("missing IndexException");
			
		} catch (IndexException e) {
			// pass :)

		} catch (Exception e) {
			// some other exception was thrown
			Assert.fail("hey! we expected an IndexException");
		}
	}

	@Test
	public void settingShouldAffectValueFromRemove() {
		DynamicArray<String> a = new DynamicArray<String>();
		a.add("hello,"); a.add(" "); a.add("world!");
		
		a.set(2, "matt!");
		String name = a.remove();

		Assert.assertEquals("matt!", name);
	}

	@Test
	public void settingShouldAffectValueFromGet() {
		DynamicArray<String> a = new DynamicArray<String>();
		a.add("hello,"); a.add(" "); a.add("world!");
		
		a.set(2, "matt!");
		String name = a.get(2);

		Assert.assertEquals("matt!", name);
	}

	@Test
	public void settingWithinRangeShouldNotThrowAnException() {
		DynamicArray<String> a = new DynamicArray<String>();
		a.add("hello,"); a.add(" "); a.add("world!");
		
		try {
			a.set(0, null);
			a.set(1, null);
			a.set(2, null);
		} catch (IndexException e) {
			Assert.fail("unexpected IndexException");
		}
	}

	@Test
	public void settingBelowZeroShouldThrowAnException(){
		DynamicArray<String> a = new DynamicArray<String>();
		a.add("hello,"); a.add(" "); a.add("world!");

		try {
			a.set(-1, "Hi!");
			
			Assert.fail("missing IndexException");

		} catch (IndexException e) {
			// pass :)

		} catch (Exception e) {
			// some other exception was thrown
			Assert.fail("hey! we expected an IndexException");
		}
	}

	@Test
	public void settingOnePastEndShouldNotThrowAnException(){
		DynamicArray<String> a = new DynamicArray<String>();
		a.add("hello,"); a.add(" "); a.add("world!");

		try {
			a.set(3, "!!");
			
		} catch (IndexException e) {
			Assert.fail("unexpected IndexException, should have added item");
		}
	}
	
	@Test
	public void settingPastEndButWithinCapacityShouldThrowAnException(){
		DynamicArray<String> a = new DynamicArray<String>();
		a.add("hello,"); a.add(" "); a.add("world!");
		a.add("hello,"); a.add(" "); a.add("world!");
		a.add("hello,"); a.add(" "); a.add("world!");

		try {
			a.set(10, "out of bounds");
			
			Assert.fail("missing IndexException");
			
		} catch (IndexException e) {
			// pass :)

		} catch (Exception e) {
			// some other exception was thrown
			Assert.fail("hey! we expected an IndexException");
		}
	}

	@Test
	public void lengthShouldInitiallyBeZero(){
		DynamicArray<String> a = new DynamicArray<String>();

		int length = a.length();
		
		Assert.assertEquals(0, length);
	}

	@Test
	public void capacityShouldNotBeZeroInitially() {
		DynamicArray<String> a = new DynamicArray<String>();

		int capacity = a.capacity();
		
		Assert.assertNotEquals(0, capacity);
	}

	@Test
	public void capacityShouldNotEndUpZeroAfterRemovals() {
		DynamicArray<String> a = new DynamicArray<String>();
		a.add(".");
		a.add(".");
		a.add(".");
		a.remove();
		a.remove();
		a.remove();

		int capacity = a.capacity();
		
		Assert.assertNotEquals(0, capacity);
	}
	

	@Test
	public void ifInitialCapacityIsOneThenFirstAddShouldDouble() {
		DynamicArray<String> a = new DynamicArray<String>();

		if(a.capacity() == 1){
			a.add(".");

			int capacity = a.capacity();

			Assert.assertEquals(2, capacity);
		}
	}

	@Test
	public void addingToOneBelowCapacityShouldNotResize(){
		DynamicArray<String> a = new DynamicArray<String>();
		// we want to start away from initial capacity of 1 for this test
		a.add("."); a.add("."); a.add("."); a.add("."); a.add("."); a.add(".");

		int capacity = a.capacity();
		while (a.length() < capacity - 1) {
			a.add("adding");
		}
		int newCapacity = a.capacity();

		Assert.assertEquals(capacity, newCapacity);
	}

	@Test
	public void addingToCapacityShouldDoubleSize(){
		DynamicArray<String> a = new DynamicArray<String>();
		// we want to start away from initial capacity of 1 for this test
		a.add("."); a.add("."); a.add("."); a.add("."); a.add("."); a.add(".");

		int capacity = a.capacity();
		while (a.length() < capacity) {
			a.add("adding");
		}
		int newCapacity = a.capacity();

		Assert.assertEquals(2 * capacity, newCapacity);
	}
	
	@Test
	public void removingToHalfCapacityShouldNotResize(){
		DynamicArray<String> a = new DynamicArray<String>();
		// we want to start away from initial capacity of 1 for this test
		a.add("."); a.add("."); a.add("."); a.add("."); a.add("."); a.add(".");
		a.add("."); a.add("."); a.add("."); a.add("."); a.add("."); a.add(".");

		int capacity = a.capacity();
		while (a.length() > capacity / 2) {
			a.remove();
		}
		int newCapacity = a.capacity();

		Assert.assertEquals(capacity, newCapacity);
	}

	@Test
	public void removingToOneAboveQuarterCapacityShouldNotResize(){
		DynamicArray<String> a = new DynamicArray<String>();
		// we want to start away from initial capacity of 1 for this test
		a.add("."); a.add("."); a.add("."); a.add("."); a.add("."); a.add(".");
		a.add("."); a.add("."); a.add("."); a.add("."); a.add("."); a.add(".");

		int capacity = a.capacity();
		while (a.length() > capacity / 4 + 1) {
			a.remove();
		}
		int newCapacity = a.capacity();

		Assert.assertEquals(capacity, newCapacity);
	}

	@Test
	public void removingToQuarterCapacityShouldHalveSize(){
		DynamicArray<String> a = new DynamicArray<String>();
		// we want to start away from initial capacity of 1 for this test
		a.add("."); a.add("."); a.add("."); a.add("."); a.add("."); a.add(".");
		a.add("."); a.add("."); a.add("."); a.add("."); a.add("."); a.add(".");

		int capacity = a.capacity();
		while (a.length() > capacity / 4) {
			a.remove();
		}
		int newCapacity = a.capacity();

		Assert.assertEquals(capacity / 2, newCapacity);
	}
}