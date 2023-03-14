package binarySearchTree;

import dataSet.DataScaleTooSmallException;
import dataSet.Dataset;
import node.Node;
import personPackage.Person;
import personPackage.DNA;
import tree.Tree;

public class BinarySearchTree extends Tree {

	public static void main(String[] args) {
		/**
		 * get testing dataset
		 */
		Dataset dataset = null;
		try {
			dataset = new Dataset(6);
		} catch (DataScaleTooSmallException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Person[] people = dataset.getData();

		BinarySearchTree bst = new BinarySearchTree();
		bst.buildTree(people);

		/**
		 * try some lookups to verify
		 */
		Person person;
		System.out.println(people[2]);
		person = bst.lookup(people[2].dna);
		System.out.println(person);
		System.out.println(bst.getHeight());
	}

	private BSTNode root = null;
	int height = 0;
	boolean isHeightModified = false;

	/**
	 * Build a tree from a family tree @personData, the root of the family tree
	 */
	public Node buildTree(Person[] people) {
		for (int i = 0; i < people.length; ++i) {
			this.insertPerson(people[i]);
		}
		return this.root;
	}

	public Node getRoot() {
		return root;
	}

	public void insertPerson(Person person) {
		if (this.root == null) {
			this.root = new BSTNode(person);
		}
		this._insertPerson(this.root, person);
		isHeightModified = true;
	}

	private void _insertPerson(BSTNode root, Person person) {

		if (person.dna.isGreaterThan(root.data.dna)) {
			// search right child
			if (root.right != null)
				// go further into the right and look for a insertion spot
				this._insertPerson(root.right, person);
			else {
				// insert here
				root.right = new BSTNode(person);
			}
		} else {
			// search left child
			if (root.left != null)
				// go further into the left and look for a insertion spot
				this._insertPerson(root.left, person);
			else {
				// insert here
				root.left = new BSTNode(person);
			}
		}
	}

	public void insert(Person[] persons) {
		// unimplemented
		isHeightModified = true;
	}

	public void insertFamily(Person person) {
		// unimplemented
		isHeightModified = true;
	}

	public Person lookup(DNA dna) {
		return _lookup(root, dna);
	}

	public Person _lookup(BSTNode root,DNA dna) {
		if (root == null)
			// didn't find it
			return null;
		if (root.data.dna.isEqualTo(dna)) {
			// found it!
			return root.data;
		}
		if (dna.isGreaterThan(root.data.dna)) {
			// search its right child
			return this._lookup(root.right, dna);
		}else {
			// search its left child
			return this._lookup(root.left, dna);
		}
	}

	/**
	 * get height with cache
	 */
	public int getHeight() {
		// cache the height
		if (isHeightModified)
			this.height = this._getHeight(this.root);
		return this.height;
	}
	
	public int _getHeight(BSTNode root) {
		// null node does add to the height
		if (root == null) return 0;
		// the height of this node is the higher child's height plus 1
		return Math.max(_getHeight(root.left), _getHeight(root.right)) + 1;
	}
}
