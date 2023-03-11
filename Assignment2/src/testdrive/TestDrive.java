package testdrive;

import binarySearchTree.BinarySearchTree;
import dataSet.Dataset;
import node.Node;
import personPackage.Person;
import tree.Tree;

public class TestDrive {
	public static void main(String[] args) {
		
		// create Dataset object to generate test data
		Dataset dataset = new Dataset();
		// set the amount of data we are going to get
		//dataset.setPopulation(10000);
		// get the data, which is a family tree
		Person[] people = dataset.getData();
		
		Tree bst = new BinarySearchTree();
		
		bst.buildTree(people);
	}
}
