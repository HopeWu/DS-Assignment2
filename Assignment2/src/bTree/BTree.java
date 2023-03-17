package bTree;


import node.Node;
import personPackage.DNA;
import personPackage.Person;
import tree.Tree;


public class BTree extends Tree {
	BTreeHelper<String, Person> bTreeHelper;
	
	/**
	 * Initializes a B-tree using the given order m (must be even and greater than 2)
	 * @param m the order of the B-tree
	 */
	public BTree(int m) {
		super();
		this.bTreeHelper = new BTreeHelper<String, Person>(m);
	}
	
    /**
     * Builds an B-tree from an array of Person objects based on their DNAs.
     * @param personData An array of Person objects to be inserted into the tree.
     */
	@Override
	public Node buildTree(Person[] personData) {
		for (Person person : personData) {
			bTreeHelper.put(person.dna.get(), person);
		}
		return bTreeHelper.getRoot();
	}
	
	/**
     * Gets the root of the B-tree.
     */
	@Override
	public Node getRoot() {
		return bTreeHelper.getRoot();
	}
	
	/**
     * Inserts a single Person object into the B-tree. 
     * @param person The Person object to be inserted into the tree.
     */
	@Override
	public void insertPerson(Person person) {
		bTreeHelper.put(person.dna.get(), person);		
	}
	
	/**
     * Inserts an array of Person objects into the B-tree. 
     * @param persons An array of Person objects to be inserted into the tree.
     */
	@Override
	public void insert(Person[] persons) {
		for (Person person : persons) {
			bTreeHelper.put(person.dna.get(), person);
		}			
	}
	
	 /**
     * Inserts a person and his/her family members(spouse, parents/parents-in-law and children) into the B-tree
     * @param person The person who and whose family members to be inserted into the tree.
     */
	@Override
	public void insertFamily(Person person) {		
		bTreeHelper.put(person.dna.get(), person);
		if (person.spouse != null) {
			bTreeHelper.put(person.spouse.dna.get(), person.spouse);
			person.spouse.getParents().forEach(p -> bTreeHelper.put(p.dna.get(), p));
		}			
		person.getParents().forEach(p -> bTreeHelper.put(p.dna.get(), p));
		person.getChildren().forEach(p -> bTreeHelper.put(p.dna.get(), p));		
	}

	/**
	 * Searches for a person by the given gene 
	 * @param gene The DNA of the target person.
	 * @return The Person object with the given gene if it exists in the B-tree, otherwise null
	 */
	@Override
	public Person lookup(DNA dna) {
		return bTreeHelper.search(dna.get());
	}
	
	/**
     * Gets the height of the B-tree.
     */
	@Override
	public int getHeight() {
		return bTreeHelper.getHeight();
	}
	
	/**
	 * Returns a string representation of the B-tree, using indents to show different levels of the tree.
	 */
	public String toString() {
		return bTreeHelper.toString();
	}
}
