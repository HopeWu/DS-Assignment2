package binarySearchTree;

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
		Dataset dataset = new Dataset(100);
		Person[] people = dataset.getData();

		BinarySearchTree bst = new BinarySearchTree();
		bst.buildTree(people);

		/**
		 * try some lookups to verify
		 */
		Person person;
		person = bst.lookup(people[2].dna);
		System.out.println(people[2]);
		System.out.println(person);
	}

	private BSTNode root = null;

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
		this._insertPerson(root, person);
	}

	public void _insertPerson(BSTNode root, Person person) {
		if (this.root == null) {
			// if current node is null, insert into it
			root = new BSTNode(person);
			return;
		}

		if (person.dna.isGreaterThan(root.data.dna)) {
			// search right child
			this._insertPerson(root.right, person);
		} else {
			// search left child
			this._insertPerson(root.left, person);
		}
	}

	public void insert(Person[] persons) {
		// TODO Auto-generated method stub

	}

	public void insertFamily(Person person) {
		// TODO Auto-generated method stub

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
		if (root.data.dna.isGreaterThan(dna)) {
			// search its right child
			return this._lookup(root.right, dna);
		}else {
			// search its left child
			return this._lookup(root.left, dna);
		}
	}

	public int getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}
}
