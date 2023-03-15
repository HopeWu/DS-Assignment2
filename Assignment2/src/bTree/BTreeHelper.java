package bTree;


/**
 * This is an implementation of a B-tree using generic types. It requires that the key type implements
 * the Comparable interface and calls the compareTo() method to compare two keys. 
 * The time complexity of search, put operations is both log m(n) where n is the number of 
 * key-value pairs and m is the order of the B-tree. The get-size, get-height, and is-empty operations 
 * take constant time.
 */
public class BTreeHelper<Key extends Comparable<Key>, Value> {
	// order of the B-tree(must be even and greater than 2, default value 4). max number of children
	// of each B-tree node is M-1
	private int m = 4; 
	private BTreeNode root; // root of the B-tree
	private int height; // height of the B-tree
	private int size; // number of key-value pairs in the B-tree

	/**
	 * Non-parameterized Constructor.
	 */
	public BTreeHelper() {
		root = new BTreeNode(0, m);
	}

	/**
	 * Initializes an empty B-tree with the given order m.
	 */
	public BTreeHelper(int m) {
		this.m = m;
		root = new BTreeNode(0, m);
	}
	
	/**
	 * Gets the order of the B-tree.
	 */
	public int getM() {
		return m;
	}
	
	/**
	 * Gets the root of the B-tree.
	 */
	public BTreeNode getRoot() {
		return root;
	}

	/**
	 * Checks whether the B-tree is empty.
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Gets the number of key-value pairs in the B-tree.
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Gets the height of the B-tree.
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Searches the B-tree with the given key.
	 *
	 * @param key the key
	 * @return the value associated with the given key if the key is in the B-tree, 
	 *         otherwise null
	 * @throws IllegalArgumentException if the key is null
	 */
	public Value search(Key key) {
		if (key == null)
			throw new IllegalArgumentException("Cannot do search because the key is null");
		return search(root, key, height);
	}

	@SuppressWarnings("unchecked")
	private Value search(BTreeNode node, Key key, int height) {
		Entry[] children = node.getChildren();

		// external node
		if (height == 0) {
			for (int j = 0; j < node.getChildNum(); j++) {
				if (key.compareTo((Key) children[j].getKey()) == 0) // eq(key, children[j].getKey())
					return (Value) children[j].getVal();
			}
		}

		// internal node
		else {
			for (int j = 0; j < node.getChildNum(); j++) {
				if (j + 1 == node.getChildNum() || key.compareTo((Key) children[j + 1].getKey()) < 0) // key, children[j + 1].getKey()
					return search(children[j].getNext(), key, height - 1);
			}
		}
		return null;
	}

	/**
	 * Inserts the key-value pair into the B-tree, overwriting the old value
	 * with the new value if the key is already in the B-tree.
	 *
	 * @param key the key
	 * @param val the value
	 * @throws IllegalArgumentException if the key is null
	 */
	public void put(Key key, Value val) {
		if (key == null)
			throw new IllegalArgumentException("Cannot do insertion because the key is null");
		BTreeNode u = insert(root, key, val, height);
		size++;
		if (u == null)
			return;

		// need to split root
		BTreeNode t = new BTreeNode(2, m);
		t.getChildren()[0] = new Entry(root.getChildren()[0].getKey(), null, root);
		t.getChildren()[1] = new Entry(u.getChildren()[0].getKey(), null, u);
		root = t;
		height++;
	}

	@SuppressWarnings("unchecked")
	private BTreeNode insert(BTreeNode node, Key key, Value val, int height) {
		int j;
		Entry entry = new Entry(key, val, null);

		// external node
		if (height == 0) {
			for (j = 0; j < node.getChildNum(); j++) {
				if (key.compareTo((Key) node.getChildren()[j].getKey()) < 0)
					break;
			}
		}

		// internal node
		else {
			for (j = 0; j < node.getChildNum(); j++) {
				if ((j + 1 == node.getChildNum()) || key.compareTo((Key) node.getChildren()[j + 1].getKey()) < 0) {
					BTreeNode u = insert(node.getChildren()[j++].getNext(), key, val, height - 1);
					if (u == null)
						return null;
					entry.setKey(u.getChildren()[0].getKey());
					entry.setVal(null);
					entry.setNext(u);
					break;
				}
			}
		}

		for (int i = node.getChildNum(); i > j; i--) {
			node.getChildren()[i] = node.getChildren()[i - 1];
		}
		node.getChildren()[j] = entry;
		node.setChildNum(node.getChildNum() + 1);
		if (node.getChildNum() < m)
			return null;
		else
			return split(node);
	}

	/**
	 * Splits node in half
	 * @param node the node whose children
	 * @return the new node which contains the second half children of the previous node after splitting
	 */
	private BTreeNode split(BTreeNode node) {
		BTreeNode newNode = new BTreeNode(m / 2, m);
		node.setChildNum(m / 2);
		for (int j = 0; j < m / 2; j++)
			newNode.getChildren()[j] = node.getChildren()[m / 2 + j];
		return newNode;
	}

	/**
	 * Returns a string representation of the B-tree, using indents to show different levels of the tree.
	 */
	public String toString() {
		return toString(root, height, "") + "\n";
	}

	private String toString(BTreeNode node, int height, String indent) {
		StringBuilder s = new StringBuilder();
		Entry[] children = node.getChildren();

		if (height == 0) {
			for (int j = 0; j < node.getChildNum(); j++) {
				s.append(indent + children[j].getKey() + " " + children[j].getVal() + "\n");
			}
		} else {
			for (int j = 0; j < node.getChildNum(); j++) {
				if (j > 0)
					s.append(indent + "(" + children[j].getKey() + ")\n");
				s.append(toString(children[j].getNext(), height - 1, indent + "     "));
			}
		}
		return s.toString();
	}
}