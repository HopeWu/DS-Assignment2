package avlTree;

import dataSet.DNAGen;
import java.util.LinkedList;
import java.util.Queue;

import node.Node;
import personPackage.Person;
import personPackage.DNA;
import tree.Tree;

public class AVLTree extends Tree{

	private avlTreeNode root; // Root node of the AVL tree

	/**
     * Constructor for AVLTree class.
     */
	public AVLTree() {
        this.root = null;
    }
	
	
	private avlTreeNode rightRotate(avlTreeNode y) {
		avlTreeNode x = y.getLeftChild();
		avlTreeNode z = x.getRightChild();

        x.setRightChild(y);
        y.setLeftChild(z);

        y.setHeight(Math.max(height(y.getLeftChild()), height(y.getRightChild())) + 1);
        x.setHeight(Math.max(height(x.getLeftChild()), height(x.getRightChild())) + 1);

        return x;
	}
	
	private avlTreeNode leftRotate(avlTreeNode x) {
		avlTreeNode y = x.getRightChild();
		avlTreeNode z = y.getLeftChild();

	    y.setLeftChild(x);
	    x.setRightChild(z);
	        
	    x.setHeight(Math.max(height(x.getLeftChild()), height(x.getRightChild())) + 1);
	    y.setHeight(Math.max(height(y.getLeftChild()), height(y.getRightChild())) + 1);

	    return y;
	}


	 /**
     * Insert a new Person object into the AVL tree.
     *
     * @param node the root node of the subtree to insert into
     * @param person the Person object to insert
     * @return the root node of the updated subtree
     */
    private avlTreeNode insert(avlTreeNode node, Person person) {
    	// If the node is null, create a new node with the given person
        if (node == null) {
            return new avlTreeNode(person);
        }

        // If the person's DNA is less than the current node's DNA, insert it into the left subtree
        if (person.dna.get().compareTo(node.getPerson().dna.get()) < 0) {
            node.setLeftChild(insert(node.getLeftChild(), person));
        }
        // Otherwise, insert it into the right subtree
        else if (person.dna.get().compareTo(node.getPerson().dna.get()) > 0){
            node.setRightChild(insert(node.getRightChild(), person));
        }
        else {
        	// Overwrite the existing node with the new node
            node.person = person;
        }
        // Update the height of the current node
        node.setHeight(Math.max(height(node.getLeftChild()), height(node.getRightChild())) + 1);

        // Get the balance factor of the current node
        int balance = getBalance(node);

        // Perform a right rotation if the left subtree is taller than the right subtree and the new person is inserted into the left subtree's left subtree
        if (balance > 1 && person.dna.get().compareTo(node.getLeftChild().getPerson().dna.get()) < 0) {
            return rightRotate(node);
        }

        // Perform a left rotation if the right subtree is taller than the left subtree and the new person is inserted into the right subtree's right subtree
        if (balance < -1 && person.dna.get().compareTo(node.getRightChild().getPerson().dna.get()) > 0) {
            return leftRotate(node);
        }

        // Perform a left-right double rotation if the left subtree is taller than the right subtree and the new person is inserted into the left subtree's right subtree
        if (balance > 1 && person.dna.get().compareTo(node.getLeftChild().getPerson().dna.get()) > 0) {
            node.setLeftChild(leftRotate(node.getLeftChild()));
            return rightRotate(node);
        }

        // Perform a right-left double rotation if the right subtree is taller than the left subtree and the new person is inserted into the right subtree's left subtree
        if (balance < -1 && person.dna.get().compareTo(node.getRightChild().getPerson().dna.get()) < 0) {
            node.setRightChild(rightRotate(node.getRightChild()));
            return leftRotate(node);
        }

        return node;
    }
    
    /**
     * Searches for a node in the AVL tree with the given DNA using a recursive
     * depth-first search. Returns the node with the given DNA, or null if no
     * such node exists in the tree.
     *
     * @param node The root node of the subtree to search.
     * @param gene The DNA of the person to search for.
     * @return The node containing the person with the given DNA, or null if no
     * such node exists in the tree.
     */
    private avlTreeNode search(avlTreeNode node, String gene) {
        if (node == null|| node.getPerson().dna.equals(gene)) {
            return node;
        }

        int cmp = gene.compareTo(node.getPerson().dna.get());
        if (cmp < 0) {
            return search(node.getLeftChild(), gene);
        } else if (cmp > 0) {
            return search(node.getRightChild(), gene);
        } else {
            return node;
        }
    }
    
    private int height(avlTreeNode node) {
        if (node == null) {
            return 0;
        }
        return node.getHeight();
    }

    private int getBalance(avlTreeNode node) {
        if (node == null) {
            return 0;
        }
        return height(node.getLeftChild()) - height(node.getRightChild());
    }
    
    /**
     * Builds an AVL tree from an array of Person objects. The tree is built
     * based on the DNA of each person, with persons whose DNA come
     * earlier in the alphabet being placed to the left of persons whose DNA
     * come later in the alphabet.
     * @param personData An array of Person objects to insert into the tree.
     */
	public Node buildTree(Person[] personData) {
		// TODO Auto-generated method stub
		insert(personData);
        return this.getRoot();
	}

	/**
     * Returns the root node of the AVL tree.
     * @return The root node of the AVL tree.
     */
	public Node getRoot() {
		// TODO Auto-generated method stub
		return root;
	}

	/**
     * Inserts an array of Person objects into the AVL tree.
     * @param persons An array of Person objects to insert into the tree.
     */
	public void insert(Person[] persons) {
		// TODO Auto-generated method stub
		for (Person person : persons) {
			insertPerson(person);
	    }
	}

	 /**
     * Inserts a family member and his/her spouse into the AVL tree
     * @param person The Person object to insert into the tree.
     */
	public void insertFamily(Person person) {
		// TODO Auto-generated method stub
		insertPerson(person);
        if (person.spouse!=null) {
        	insertPerson(person.spouse);
        }
	}

	/**
	 * Searches for a Person object in the AVL tree with the given DNA using a
	 * recursive depth-first search. Returns the Person object with the given DNA,
	 * or null if no such person exists in the tree.
	 * @param gene The DNA of the person to search for.
	 * @return The Person object with the given name, or null if no such person
	 * exists in the tree.
	 */
	public Person lookup(DNA dna) {
		// TODO Auto-generated method stub
		avlTreeNode node = search(root, dna.get());
		return (node == null) ? null : node.getPerson();
	}

	/**
     * Returns the height of the subtree rooted at this node.
     * @return The height of the subtree rooted at this node.
     */
	public int getHeight() {
		// TODO Auto-generated method stub
		return height(root);
	}

	
	/**
     * Inserts a single Person object into the AVL tree. 
     * @param person The Person object to insert into the tree.
     */
	@Override
	public void insertPerson(Person person) {
		// TODO Auto-generated method stub
		root = insert(root, person);
	}


}
