package redBlackTree;

import avlTree.AVLTree;
import binarySearchTree.BinarySearchTree;
import dataSet.DataScaleTooSmallException;
import dataSet.Dataset;
import personPackage.Person;

public class TestRedBlackTree {
	public static void main(String[] args) {
	    RedBlackTree familyTree = new RedBlackTree();

	    Dataset dataset = null;
		try {
			dataset = new Dataset(10000);
		} catch (DataScaleTooSmallException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Person[] people = dataset.getData();
		
		familyTree.insert(people);
	    int height = familyTree.getHeight();
	    System.out.println("Red: " + height);
	    
	    BinarySearchTree bst = new BinarySearchTree();


		bst.buildTree(people);
	    System.out.println("BST: " + bst.getHeight());
	}
	
	

}
