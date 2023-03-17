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
			dataset = new Dataset(1000);
		} catch (DataScaleTooSmallException e) {
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
<<<<<<< HEAD
	 * Build a tree from a family tree @personData, the root of the family tree.
	 * nlogn time
=======
	 * Build a tree from a family tree @personData, the root of the family tree
>>>>>>> branch 'main' of https://github.com/HopeWu/DS-Assignment2.git
	 */
	public Node buildTree(Person[] people) {
		insert(people);
		return this.root;
	}

	/**
	 * return the root of the tree
	 */
	public Node getRoot() {
		return this.root;
	}

	/**
	 * insert only one person
	 */
	public void insertPerson(Person person) {
		if (this.root == null) {
			this.root = new BSTNode(person);
		}
		this._insertPerson(this.root, person);
		isHeightModified = true;
	}

	/**
	 * real recursive insert
	 * @param root, under which root to insert
	 * @param person, which person to insert
	 */
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

	/**
	 * insert a bunch of people
	 */
	public void insert(Person[] persons) {
		for (int i = 0; i < persons.length; ++i) {
			this.insertPerson(persons[i]);
		}
	}

	/**
	 * insert the whole family, a family is defined by three generations, parent
	 * generation, current generation, children generation
	 */
	public void insertFamily(Person person) {
		if (person == null)
			return;
		insertPerson(person);
		// insert their family if there is any in the data set
		if (person.getParents() != null)
			for (Person parent : person.getParents()) {
				insertPerson(parent);
			}
		// insert their children if there is any in the data set
		if (person.getChildren() != null)
			for (Person child : person.getChildren()) {
				insertPerson(child);
			}
	}

	/**
	 * lookup a person by dna
	 */
	public Person lookup(DNA dna) {
		return _lookup(root, dna);
	}

	/**
	 * real recursive lookup
	 * @param root, search this root
	 * @param dna, search by this dna
	 * @return, return the Person if found
	 */
	public Person _lookup(BSTNode root, DNA dna) {
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
		} else {
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

	/**
	 * real recursive getHeight function
	 * @param root, the height of this root
	 * @return, the height of this root
	 */
	public int _getHeight(BSTNode root) {
		// null node does add to the height
		if (root == null)
			return 0;
		// the height of this node is the higher child's height plus 1
		return Math.max(_getHeight(root.left), _getHeight(root.right)) + 1;
	}
}
