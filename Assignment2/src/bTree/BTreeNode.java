package bTree;

import node.Node;

/**
 * 
 * Helper node data type of the B-tree 
 * 
 */
public class BTreeNode extends Node {
	private int childNum; // number of children
	private Entry[] children; // the array of children

    // create a node with k children
    public BTreeNode(int childNum, int m) {
        this.childNum = childNum;
        children = new Entry[m]; 
    }

	public int getChildNum() {
		return childNum;
	}

	public void setChildNum(int childNum) {
		this.childNum = childNum;
	}

	public Entry[] getChildren() {
		return children;
	}  
    
}
