package binarySearchTree;

import node.Node;
import personPackage.Person;
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
	 * Build a tree from a family tree @personData, the root of the family tree.
	 * nlogn time
	 */
	public Node buildTree(Person[] people) {
		// TODO Auto-generated method stub
		return null;
	}

	public Node getRoot() {
		// TODO Auto-generated method stub
		return null;
	}

	public void insertPerson(Person person) {
		// TODO Auto-generated method stub
		
	}

	public void insert(Person[] persons) {
		// TODO Auto-generated method stub
		
	}

	public void insertFamily(Person person) {
		// TODO Auto-generated method stub
		
	}

	public Person lookup(String gene) {
		// TODO Auto-generated method stub
		return null;
	}

	public int getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}
}

