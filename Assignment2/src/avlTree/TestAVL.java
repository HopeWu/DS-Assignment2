package avlTree;

import dataSet.DataScaleTooSmallException;
import dataSet.Dataset;
import personPackage.Person;

public class TestAVL {
	public static void main(String[] args) {
		Dataset dataset = null;
		try {
			dataset = new Dataset(100);
		} catch (DataScaleTooSmallException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Person[] people = dataset.getData();
		for (int i = 0; i < people.length; ++i) {
			System.out.print(i + " ");
			System.out.println(people[i]);
		}
		 // Create a new AVLTree object
	    AVLTree tree = new AVLTree();
	    
	    Dataset dataset2 = null;
		try {
			dataset2 = new Dataset(100);
		} catch (DataScaleTooSmallException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    Person[] people2 = dataset2.getData();
	    
//	 // Build an AVL tree from the Person objects
//	    Node root = tree.buildTree(people);
//
//	    // Print the root node of the tree
//	    if (root != null) {
//	        System.out.println("Root node: " + root.getPerson().getName());
//
//	        // Get the height of the tree from the root node
//	        int height = root.getHeight();
//	        System.out.println("Tree height: " + height);
//	    } else {
//	        System.out.println("Tree is empty.");
//	    }
	    
	    // Insert the Person objects into the tree
	    tree.insert(people);
	    tree.insertPerson(people2[50]);
	    System.out.println(people2[50]);
	    System.out.println(tree.lookup(people2[50].dna));
	    tree.insertFamily(people2[51]);
	    System.out.println(tree.lookup(people2[51].dna));
	    System.out.println(tree.lookup(people2[51].spouse.dna));
	    
	    // Print the height of the tree
	    System.out.println("Tree height: " + tree.getHeight());
	    System.out.println(people[6].dna);
	    // Lookup a person by name and print their age
	    Person person = tree.lookup(people[6].dna);
	    if (person != null) {
	        System.out.println(person);
	    } else {
	        System.out.println("Person not found.");
	    }
	    
	}
}
