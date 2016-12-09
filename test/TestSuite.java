import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.BeforeClass;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	// INSERT YOUR TEST CLASSES HERE, SEPARARTED BY COMMAS
	UnionFinderTest.class,
	BinarySearcherTest.class,
	LinkedListTest.class,
	DoublyLinkedListTest.class,
	LinkedListIteratorTest.class,
	DoublyLinkedListIteratorTest.class,
})

/** TestSuite; junit test class to run all unit tests
 * @author Matt Farrugia
 */
public class TestSuite {
	// empty, put tests in the test suite classes
}
