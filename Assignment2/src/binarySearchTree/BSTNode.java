package binarySearchTree;

import node.Node;
import personPackage.Person;

public class BSTNode extends Node{
	public BSTNode left;
	public BSTNode right;
	public Person data;

	public BSTNode(Person person) {
		this.data = person;
	}
}
