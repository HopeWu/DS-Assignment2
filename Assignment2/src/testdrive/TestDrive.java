package testdrive;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Random;
import avlTree.AVLTree;
import bTree.BTree;
import binarySearchTree.BinarySearchTree;
import dataSet.DataScaleTooSmallException;
import dataSet.Dataset;
import personPackage.Person;
import redBlackTree.RedBlackTree;
import tree.Tree;

public class TestDrive {
	public static void main(String[] args) {
//		checkTreeCorrectness();
//		testOrder(); // just for B-tree
		experimentAll();
	}
	
	/**
	 * Experiments with all type of trees
	 */
	private static void experimentAll() {		
		System.out.println("Type, Data Size, Height, Build Tree (ms), Memory (bytes), Search 10000, Insert 10000");
		for (int size = 100000; size <= 1000000; size += 100000) {
			Person[] people = readData(size);
			if (people == null) {
				return;
			}
	
			Tree avl = new AVLTree();
			Tree bst = new BinarySearchTree();
			Tree bTree = new BTree(14); 
//			Tree redBlackTree = new RedBlackTree();
			
			experimentOne(avl, "AVL Tree", size, people);
			experimentOne(bst, "Binary Search Tree", size, people);
			experimentOne(bTree, "B-Tree", size, people);
//			experimentOne(redBlackTree, "Red-Black Tree", size, people);
		}
		
	}
	/**
	 * Experiments with one type of tree	
	 * @param tree the tree to be experimented with
	 * @param type the type of the tree
	 * @param size data size
	 * @param people the experimental data
	 */
	private static void experimentOne(Tree tree, String type, int size, Person[] people) {
		StringBuilder sb = new StringBuilder();
		// for memory consumption check
		Runtime r = Runtime.getRuntime();
		r.gc(); // run the garbage collector before experiment
		long startMem = r.totalMemory() - r.freeMemory(); // free memory in the beginning
		
		long startTime1 = System.currentTimeMillis();
		tree.buildTree(people); // build the tree
		long endIime1 = System.currentTimeMillis();
		long elapsedTime1 = endIime1 - startTime1;

		long endMem = r.totalMemory() - r.freeMemory(); // free memory in the end
		long mem= endMem - startMem ; // memory consumption for the tree
		
		sb.append(type).append(", ").append(size).append(", ").append(tree.getHeight()).append(", ")
		.append(elapsedTime1).append(", ").append(mem).append(", ");
		

		final int ITERATIONS = 5;
		long startTime2 = System.currentTimeMillis();
		for (int count = 0; count < ITERATIONS; count++) {
			// search for a specified number of entries and record the total time
			for (int i = 0; i < 10000; i++) {
				// randomly select a person in the data to search for in the built tree
				Person target = tree.lookup(people[new Random().nextInt(size)].dna);
				assert target != null;
			}
		}

		long endTime2 = System.currentTimeMillis();
		long elapsedTime2 = (endTime2 - startTime2) / ITERATIONS;
		sb.append(elapsedTime2).append(", ");
		
		// insert 10000 new data into the tree			
		try {
			Dataset dataset = new Dataset(10000);
			Person[] newPeople = dataset.getData();	
			long startTime3 = System.currentTimeMillis();	
			tree.insert(newPeople);
			long endTime3 = System.currentTimeMillis();
			long elapsedTime3 = endTime3 - startTime3;
			sb.append(elapsedTime3);
		} catch (DataScaleTooSmallException e) {
			e.printStackTrace();
		}
		
		System.out.println(sb.toString());
//		System.out.println(type + ", " + size + ", " + tree.getHeight() + ", " + elapsedTime1 + ", " + mem + ", " + elapsedTime2 + ", " + elapsedTime3);
	}
	
	/**
	 * Reads data from the previously generated file with the given size
	 * @param size the size of data that is needed (no more than 1000000)
	 */
	private static Person[] readData(int size) {
		String path = "data/data_1000000.txt";
	    // read data from previously generated data file
		try {
			FileInputStream fi = new FileInputStream(path);
			ObjectInputStream oi = new ObjectInputStream(fi);
			
			Person[] people = new Person[size];
			int count = 0;
			
			// Read objects
			while (count < size) {
				Person person = (Person) oi.readObject();
				people[count] = person;
				count++;
			}
			oi.close();
			fi.close();
			return people;			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Experiments with the order of the B-tree.
	 */
	public static void testOrder() {
		int size = 1000000;
		Person[] people = readData(size);
		if (people == null) {
			return;
		}
		System.out.println("Data Size, Order, Height, Build Tree (ms), Search 10000 (ms)");
		
		for (int k = 4; k <= 100; k += 2) {
			Tree tree = new BTree(k);
			
			long startTime1 = System.currentTimeMillis();
			tree.buildTree(people); // build the B-tree
			long endTime1 = System.currentTimeMillis();
			long elapsedTime1 = endTime1 - startTime1;
			
			long startTime2 = System.currentTimeMillis();
			// search for 10000 entries and record the total time
			for (int i = 0; i < 10000; i++) {
				// randomly select a person in the data to search for in the built tree
				Person target = tree.lookup(people[new Random().nextInt(size)].dna);
				assert target != null;
			}
			long endTime2 = System.currentTimeMillis();
			long elapsedTime2 = endTime2 - startTime2;
			
			System.out.println(size + ", " + k + ", " + tree.getHeight() + ", " + elapsedTime1 + ", " + elapsedTime2);			
		}		
	}
	
	/**
	 * Checks whether all the inserted entries can be found by search operations to verify 
	 * the correctness of the tree implementation. (Should be run before doing experiments)
	 */
	public static void checkTreeCorrectness() {
		int size = 10000;
		Person[] people = readData(size);
		if (people == null) {
			return;
		}
		// add all the Tree Objects that need to be checked to the array.
		Tree[] treeArr = new Tree[] {new AVLTree(), new BinarySearchTree(), new BTree(14), new RedBlackTree()};
		for (Tree tree : treeArr) {
			System.out.println(String.format("Start checking %s ...", tree.getClass().getSimpleName()));
			tree.buildTree(people); // build the tree
			int nullCount = 0;
			for (int i = 0; i < people.length; i++) {
				Person person = tree.lookup(people[i].dna);
				if (person == null) {
					nullCount++;
				}
			}
			assert nullCount == 0;
//			System.out.println(nullCount);
			System.out.println("Check completed, no error found.");
		}				
	}
}
