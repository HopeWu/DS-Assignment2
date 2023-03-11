package tree;

import node.Node;
import personPackage.Person;

public abstract class Tree {
	/**
	 * Used a bunch of PersonNodes to build one tree
	 * @personData, the root of the family tree
	 */
	public abstract Node buildTree(Person[] personData);
	
	public abstract Node getRoot();
	
	// insert the given person
	public abstract void insertPerson(Person person);
	
	// the parameter can be an array of person objects
	public abstract void insert(Person[] persons);
	
	// insert this person and their spouse and their children if any
	public abstract void insertFamily(Person person);
	
	// lookup one person by gene
	public abstract Person lookup(String gene);
	
	// get the height of this tree with the height being the depth of the deepest leaf node
	public abstract int getHeight();
}
