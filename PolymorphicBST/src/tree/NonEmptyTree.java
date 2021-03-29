package tree;

import java.util.Collection;

/**
 * This class represents a non-empty search tree. An instance of this class
 * should contain:
 * <ul>
 * <li>A key
 * <li>A value (that the key maps to)
 * <li>A reference to a left Tree that contains key:value pairs such that the
 * keys in the left Tree are less than the key stored in this tree node.
 * <li>A reference to a right Tree that contains key:value pairs such that the
 * keys in the right Tree are greater than the key stored in this tree node.
 * </ul>
 * 
 */
public class NonEmptyTree<K extends Comparable<K>, V> implements Tree<K, V> {

	/* Provide whatever instance variables you need */

	private K key;
	private V value;
	private Tree<K, V> left;
	private Tree<K, V> right;

	/**
	 * Only constructor we need.
	 * 
	 * @param key
	 * @param value
	 * @param left
	 * @param right
	 */
	public NonEmptyTree(K key, V value, Tree<K, V> left, Tree<K, V> right) {
		this.key = key;
		this.value = value;
		this.left = left;
		this.right = right;
	}

	public V search(K key) {
		int comparison = this.key.compareTo(key);

		if (comparison == 0) {
			return this.value;
		} else if (comparison > 0) {
			return this.left.search(key);
		} else if (comparison < 0) {
			return this.right.search(key);
		}

		return null; // Doesn't have any effect
	}

	public NonEmptyTree<K, V> insert(K key, V value) {
		int comparison = this.key.compareTo(key);

		if (comparison == 0) {
			this.value = value;
		} else if (comparison > 0) {
			this.left = this.left.insert(key, value);
		} else if (comparison < 0) {
			this.right = this.right.insert(key, value);
		}

		return this;
	}

	public Tree<K, V> delete(K key) {
		int comparison = this.key.compareTo(key);

		if (comparison == 0) {
			try {
				this.key = this.left.max();
				this.value = this.left.search(this.left.max());
				this.left = this.left.delete(this.left.max());
			} catch (TreeIsEmptyException e) {
				try {
					this.key = this.right.min();
					this.value = this.right.search(this.right.min());
					this.right = this.right.delete(this.right.min());
				} catch (TreeIsEmptyException t) {
					return EmptyTree.getInstance();
				}
			}
		} else if (comparison > 0) {
			this.left = this.left.delete(key);
		} else if (comparison < 0) {
			this.right = this.right.delete(key);
		}

		return this;
	}

	public K max() {
		try {
			return this.right.max();
		} catch (TreeIsEmptyException e) {
			return this.key;
		}
	}

	public K min() {
		try {
			return this.left.min();
		} catch (TreeIsEmptyException e) {
			return this.key;
		}
	}

	public int size() {
		return 1 + this.left.size() + this.right.size();
	}

	public void addKeysToCollection(Collection<K> c) { // In order
		this.left.addKeysToCollection(c);
		c.add(this.key);
		this.right.addKeysToCollection(c);
	}

	public Tree<K, V> subTree(K fromKey, K toKey) {
		Tree<K, V> subTree = EmptyTree.getInstance();

		int lowComparison = this.key.compareTo(fromKey);
		int highComparison = this.key.compareTo(toKey);

		if (lowComparison >= 0 && highComparison <= 0) {
			NonEmptyTree<K, V> tree = subTree.insert(this.key, this.value);
			tree.left = this.left.subTree(fromKey, toKey);
			tree.right = this.right.subTree(fromKey, toKey);

			return tree;
		} else if (lowComparison < 0) {
			return this.right.subTree(fromKey, toKey);
		} else if (highComparison > 0) {
			return this.left.subTree(fromKey, toKey);
		}

		return subTree;
	}

	public int height() {
		int leftHeight = 1 + this.left.height();
		int rightHeight = 1 + this.right.height();

		return leftHeight > rightHeight ? leftHeight : rightHeight;
	}

	public void inorderTraversal(TraversalTask<K, V> p) {
		this.left.inorderTraversal(p);
		p.performTask(this.key, this.value);
		this.right.inorderTraversal(p);
	}

	public void rightRootLeftTraversal(TraversalTask<K, V> p) {
		this.right.rightRootLeftTraversal(p);
		p.performTask(this.key, this.value);
		this.left.rightRootLeftTraversal(p);
	}
}