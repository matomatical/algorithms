import org.junit.Assert;
import org.junit.Test;

import com.matomatical.ads.UnionFinder;

public class UnionFinderTest {
	
	@Test
	public void findShouldRejectUnconnectedNodes() {
		UnionFinder uf = new UnionFinder(2);
		
		Assert.assertFalse(uf.find(1, 0));
	}

	@Test
	public void findShouldAcceptConnectedNodes() {
		UnionFinder uf = new UnionFinder(2);
		
		uf.union(1, 0);
		
		Assert.assertTrue(uf.find(1, 0));
	}
	
	@Test
	public void findShouldAcceptIndirectlyConnectedNodes() {
		UnionFinder uf = new UnionFinder(3);
		
		uf.union(0, 1);
		uf.union(1, 2);

		Assert.assertTrue(uf.find(0, 2));
	}

	@Test
	public void findShouldAcceptSameNodes() {
		UnionFinder uf = new UnionFinder(2);
		
		Assert.assertTrue(uf.find(0, 0));
	}

	@Test
	public void findShouldNotDependOnOrderWhenTrue() {
		UnionFinder uf = new UnionFinder(2);
		uf.union(1, 0);
		Assert.assertTrue(uf.find(0, 1) == uf.find(1, 0));
	}

	@Test
	public void findShouldNotDependOnOrderWhenFalse() {
		UnionFinder uf = new UnionFinder(2);
		Assert.assertTrue(uf.find(0, 1) == uf.find(1, 0));
	}
}