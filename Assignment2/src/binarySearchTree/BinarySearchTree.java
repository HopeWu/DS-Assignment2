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
	
	public Person lookup(DNA dna) {
		// TODO Auto-generated method stub
		return null;
	}

	private BSTNode root = null;
	
	/**
	 * Build a tree from a family tree
	 * @personData, the root of the family tree
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
			root = new BSTNode(person);
			return;
		}
		
		root.insert(person);
	}

	public void insert(Person[] persons) {
		// TODO Auto-generated method stub
		
	}

	public void insertFamily(Person person) {
		// TODO Auto-generated method stub
		
	}


	public int getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}
}

