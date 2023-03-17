package redBlackTree;

import node.Node;
import personPackage.Person;

public class RedBlackNode extends Node {
	public Person person;
	public RedBlackNode left;
	public RedBlackNode right;
	public RedBlackNode parent;
	public boolean isLeftChild;
	public boolean isBlack;
    public RedBlackNode(Person person){
    	this.person = person;
        left = right = parent = null;
        isBlack = false;
        isLeftChild = false;
    }
    @Override
    public String toString() {
        return "Node [person=" + person + ", isBlack=" + isBlack + "]";
    }
}