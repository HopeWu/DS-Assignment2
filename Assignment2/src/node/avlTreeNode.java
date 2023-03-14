package node;

import java.util.Objects;

import personPackage.Person;

/**
 * The AVLNode class represents a node in an AVL tree. Each node contains a
 * Person object and pointers to its left and right child nodes, as well as its
 * parent node and the height of its subtree.
 */
public class avlTreeNode extends Node {
		public Person person; // Person object stored in the node
		public avlTreeNode parent; // Parent of the node
		public avlTreeNode leftChild, rightChild; // Left and right child of the node
		public int height; // Height of the node
	
		
		/**
	     * Constructs a new AVLNode object with the given Person object and null
	     * child and parent pointers.
	     * @param person The Person object to store in the new node.
	     */
	 	public avlTreeNode(Person person) {
	        this.person = person;
	        this.parent = null;
	        this.leftChild = null;
	        this.rightChild = null;
	        this.height = 1; // Initialize the node height to 1
	 	}
	 	
	 	/**
	     * Returns the Person object stored in this node.
	     * @return The Person object stored in this node.
	     */
	    public Person getPerson() {
	        return person;
	    }
	    
	    /**
	     * Returns the parent node of this node.
	     * @return The parent node of this node.
	     */
	    public avlTreeNode getParent() {
	        return parent;
	    }
	    
	    /**
	     * Returns the left child node of this node.
	     * @return The left child node of this node.
	     */
	    public avlTreeNode getLeftChild() {
	        return leftChild;
	    }

	    /**
	     * Returns the right child node of this node.
	     * @return The right child node of this node.
	     */
	    public avlTreeNode getRightChild() {
	        return rightChild;
	    }

	    /**
	     * Sets the parent node of this node to the given AVLNode object.
	     * @param parent The new parent node to set for this node.
	     */
	    public void setParent(avlTreeNode parent) {
	        this.parent = parent;
	    }
	    
	    /**
	     * Sets the left child node of this node to the given AVLNode object.
	     * @param leftChild The new left child node to set for this node.
	     */
	    public void setLeftChild(avlTreeNode leftChild) {
	        this.leftChild = leftChild;
	        if (leftChild != null) {
	        	leftChild.setParent(this);
	        }
	    }

	    /**
	     * Sets the right child node of this node to the given AVLNode object.
	     * @param rightChild The new right child node to set for this node.
	     */
	    public void setRightChild(avlTreeNode rightChild) {
	        this.rightChild = rightChild;
	        if (rightChild != null) {
	            rightChild.setParent(this);
	        }
	    }
	    
	    /**
	     * Returns the height of the subtree rooted at this node.
	     * @return The height of the subtree rooted at this node.
	     */
	    public int getHeight() {
	        return height;
	    }
	    
	    /**
	     * Sets the height of the subtree rooted at this node to the given value.
	     * @param height The new height value to set for the subtree rooted at this
	     *               node.
	     */
	    public void setHeight(int height) {
	        this.height = height;
	    }	 
}
    

