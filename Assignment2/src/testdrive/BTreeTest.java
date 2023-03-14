package testdrive;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Random;

import bTree.BTree;
import node.Node;
import personPackage.Person;
import tree.Tree;

public class BTreeTest {
	public static void main(String[] args) {
		testBtree();
	}
	public static void testBtree() {
		int size = 100000;
		Person[] people = getDate(size);
		if (people == null) {
			return;
		}
		
		System.out.println("Data Size, Order, Build Tree, Search");
		
		for (int k = 4; k <= 100; k += 2) {
			Tree tree = new BTree(k);
			long start1 = System.currentTimeMillis();
			Node root = tree.buildTree(people); // build the B-tree
//			System.out.println(tree.toString());
			long end1 = System.currentTimeMillis();
			long elapsedTime1 = end1 - start1;
//			System.out.println(elapsedTime1);
//			System.out.println(tree.getHeight());			
//			System.out.println("k: " + k);

			
			long start2 = System.currentTimeMillis();
			Person target = tree.lookup(people[new Random().nextInt(size)].dna); // randomly select a person in the data to search for in the built tree
			long end2 = System.currentTimeMillis();
			long elapsedTime2 = end2 - start2;
//			System.out.println(target);
			
			System.out.println(size + ", " + k + ", " + elapsedTime1 + ", " + elapsedTime2);
			
//			int nullCount = 0;
//			for (int i = 0; i < people.length; i++) {
////				System.out.println(i);
////				System.out.println(people[i].dna);
//				Person person = tree.lookup(people[i].dna);
//				if (person == null) {
//					nullCount++;
//				}
//				System.out.println(person);
//			}
//			System.out.println(nullCount);
//			System.out.println("===========================");
		}		

	}

	/**
	 * Gets data from the previously generated file with the data size as the suffix
	 */
	private static Person[] getDate(int size) {
		String path = String.format("Assignment2/data/data_%d.txt", size);
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
		} catch (EOFException e) {
			System.out.println("Completed reading data.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
