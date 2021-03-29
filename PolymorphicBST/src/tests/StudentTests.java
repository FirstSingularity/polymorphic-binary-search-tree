package tests;

import junit.framework.TestCase;
import tree.EmptyTree;
import tree.NonEmptyTree;
import tree.PlaceKeysValuesInArrayLists;
import tree.PolymorphicBST;
import java.util.NoSuchElementException;
import java.util.Set;
import org.junit.Test;

public class StudentTests extends TestCase {
	
	@Test
	public void testNonEmptyTreeContructor() {
		/**
		 * This tests:
		 * That the constructor
		 * does not throw errors
		 * NonEmptyTree.NonEmptyTree()
		 */
		
		try {
			NonEmptyTree<Integer, Integer> netree = new NonEmptyTree<Integer, Integer>(1, 1, EmptyTree.getInstance(), EmptyTree.getInstance());
		}catch(Exception e) {
			fail();
		}
	}
	
	@Test
	public void testPolymorphicBSTPutGet() {
		/**
		 * This tests:
		 * 
		 * PolymorphicBST.put()
		 * PolymorphicBST.get()
		 * NonEmptyTree.insert()
		 * NonEmptyTree.search()
		 * EmptyTree.insert()
		 * EmptyTree.search()
		 * 
		 */
		
		PolymorphicBST<String, String> ptree = new PolymorphicBST<String, String>();
		
		assertEquals(null, ptree.get("Love"));
		
		ptree.put("Love", "Ambition");
		ptree.put("Magic", "Dumbledore");
		ptree.put("Magic", "Severus");
		
		assertEquals("Severus", ptree.get("Magic"));
		
		ptree.put("Candle", "Hot");
		ptree.put("Water", "Bender");
		
		assertEquals("Ambition", ptree.get("Love"));
		assertEquals("Hot", ptree.get("Candle"));
		assertEquals(null, ptree.get("Jamaica"));
	}
	
	@Test
	public void testPolymorphicBSTSize() {
		/**
		 * This tests:
		 * PolymorphicBST.size()
		 * NonEmptyTree.size()
		 * EmptyTree.size()
		 */
		
		PolymorphicBST<Integer, String> ptree = new PolymorphicBST<Integer, String>();
		
		assertEquals(0, ptree.size());
		
		ptree.put(7, "Seven");
		ptree.put(7, "Eight");
		
		assertEquals(1, ptree.size());
		
		ptree.put(4, "Nine");
		ptree.put(90, "One");
		
		assertEquals(3, ptree.size());
	}
	
	@Test
	public void testPolymorphicBSTRemove() {
		/**
		 * This tests:
		 * PolymorphicBST.remove()
		 * NonEmptyTree.delete()
		 * EmptyTree.delete()
		 */
		
		PolymorphicBST<Integer, String> ptree = new PolymorphicBST<Integer, String>();
		
		ptree.put(2, "two");
		ptree.put(1, "three");
		ptree.put(3, "4");
		ptree.put(4, "4");
		ptree.put(0, "three");
		
		assertEquals("three", ptree.get(1));
		
		ptree.remove(1);
		
		assertEquals(null, ptree.get(1));
		assertEquals(4, ptree.size());
		
		ptree.remove(2);
		
		assertEquals(null, ptree.get(2));
		assertEquals("4", ptree.get(3));
	}
	
	@Test
	public void testPolymorphicBSTKeySet() {
		/**
		 * This tests:
		 * PolymorphicBST.keySet()
		 * NonEmptyTree.addKeysToCollection()
		 * EmptyTree.addKeysToCollection()
		 */
		
		PolymorphicBST<String, String> ptree = new PolymorphicBST<String, String>();
		
		ptree.put("Love", "Ambition");
		ptree.put("Magic", "Dumbledore");
		ptree.put("Snape", "Severus");
		
		Set<String> ptreeSet = ptree.keySet();
		
		assertTrue(ptreeSet.contains("Love"));
		assertTrue(ptreeSet.contains("Magic"));
		assertTrue(ptreeSet.contains("Snape"));
	}
	
	@Test
	public void testPolymorphicBSTGetMin() {
		/**
		 * This tests:
		 * PolymorphicBST.getMin()
		 * NonEmptyTree.min()
		 * EmptyTree.min()
		 */
		
		PolymorphicBST<Integer, String> ptree = new PolymorphicBST<Integer, String>();
		
		ptree.put(2, "two");
		ptree.put(1, "three");
		ptree.put(3, "4");
		ptree.put(4, "4");
		ptree.put(0, "three");
		
		assertTrue(0 == ptree.getMin());
		
		ptree.remove(0);
		
		assertTrue(1 == ptree.getMin());
	}
	
	@Test
	public void testPolymorphicBSTGetMax() {
		/**
		 * This tests:
		 * PolymorphicBST.getMax()
		 * NonEmptyTree.max()
		 * EmptyTree.max()
		 */
		
		PolymorphicBST<Integer, String> ptree = new PolymorphicBST<Integer, String>();
		
		ptree.put(2, "two");
		ptree.put(1, "three");
		ptree.put(3, "4");
		ptree.put(4, "4");
		ptree.put(0, "three");
		
		assertTrue(4 == ptree.getMax());
		
		ptree.remove(4);
		
		assertTrue(3 == ptree.getMax());
	}
	
	@Test
	public void testPolymorphicBSTSubMap() {
		/**
		 * This test:
		 * PolymorphicBST.subMap()
		 * NonEmptyTree.subTree()
		 * EmptyTree.subTree()
		 */
		
		PolymorphicBST<Integer, String> ptree = new PolymorphicBST<Integer, String>();
		
		ptree.put(2, "two");
		ptree.put(1, "three");
		ptree.put(3, "4");
		ptree.put(4, "4");
		ptree.put(0, "three");
		
		PolymorphicBST<Integer, String> subMap = ptree.subMap(1, 3);
		
		assertEquals("two", subMap.get(2));
		assertEquals("three", subMap.get(1));
		assertEquals("4", subMap.get(3));
		assertEquals(null, subMap.get(0));
		assertEquals(null, subMap.get(4));
	}
	
	@Test
	public void testPolymorphicBSTClear() {
		/**
		 * This tests:
		 * PolymorphicBST.clear()
		 */
		
		PolymorphicBST<Integer, String> ptree = new PolymorphicBST<Integer, String>();
		
		ptree.put(2, "two");
		ptree.put(1, "three");
		ptree.put(3, "4");
		ptree.put(4, "4");
		ptree.put(0, "three");
		
		assertEquals(5, ptree.size());
		
		ptree.clear();
		
		assertEquals(null, ptree.get(2));
		assertEquals(null, ptree.get(1));
		assertEquals(null, ptree.get(3));
		assertEquals(null, ptree.get(0));
		assertEquals(null, ptree.get(4));
		assertEquals(0, ptree.size());
	}
	
	@Test
	public void testPolymorphicBSTHeight() {
		/**
		 * This tests:
		 * PolymorphicBST.height()
		 * NonEmptyTree.height()
		 * EmptyTree.height()
		 */
		
		PolymorphicBST<Integer, String> ptree = new PolymorphicBST<Integer, String>();
		
		ptree.put(2, "two");
		ptree.put(1, "three");
		ptree.put(3, "4");
		ptree.put(4, "4");
		ptree.put(0, "three");
		
		assertEquals(3, ptree.height());
		
		ptree.remove(2);
		ptree.remove(3);
		
		assertEquals(2, ptree.height());
	}
	
	@Test
	public void testPolymorphicBSTInorderTraversal() {
		/**
		 * This tests:
		 * PolymorphicBST.inorderTraversal()
		 * NonEmptyTree.inorderTraversal()
		 * EmptyTree.inorderTraversal()
		 * PlaceKeysValuesInArrayLists.PlaceKeysValuesInArrayLists()
		 * PlaceKeysValuesInArrayLists.performTask()
		 * PlaceKeysValuesInArrayLists.getKeys()
		 * PlaceKeysValuesInArrayLists.getValues()
		 */
		
		PolymorphicBST<Integer, String> ptree = new PolymorphicBST<Integer, String>();
		PlaceKeysValuesInArrayLists<Integer, String> task = new PlaceKeysValuesInArrayLists<Integer, String>();
		
		assertEquals(0, task.getKeys().size());
		assertEquals(0, task.getValues().size());
		
		ptree.put(2, "two");
		ptree.put(1, "three");
		ptree.put(3, "4");
		ptree.put(4, "4");
		ptree.put(0, "three");
		
		ptree.inorderTraversal(task);
		
		assertTrue(task.getKeys().toString().equals("[0, 1, 2, 3, 4]"));
		assertTrue(task.getValues().toString().equals("[three, three, two, 4, 4]"));
	}
	
	@Test
	public void testPolymorphicBSTRightRootLeftTraversal() {
		/**
		 * This tests:
		 * PolymorphicBST.rightRootLeftTraversal()
		 * NonEmptyTree.rightRootLeftTraversal()
		 * EmptyTree.rightRootLeftTraversal()
		 */
		
		PolymorphicBST<Integer, String> ptree = new PolymorphicBST<Integer, String>();
		PlaceKeysValuesInArrayLists<Integer, String> task = new PlaceKeysValuesInArrayLists<Integer, String>();
		
		assertEquals(0, task.getKeys().size());
		assertEquals(0, task.getValues().size());
		
		ptree.put(2, "two");
		ptree.put(1, "three");
		ptree.put(3, "4");
		ptree.put(4, "4");
		ptree.put(0, "three");
		
		ptree.rightRootLeftTraversal(task);
		
		assertTrue(task.getKeys().toString().equals("[4, 3, 2, 1, 0]"));
		assertTrue(task.getValues().toString().equals("[4, 4, two, three, three]"));
	}
}