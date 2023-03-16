package redBlackTree;

import node.RedBlackNode;
import personPackage.Person;
import personPackage.DNA;
import tree.Tree;

public class RedBlackTree extends Tree{
	
	public RedBlackNode buildTree(Person[] personData) {
		insert(personData);
		return root;
	}

	public RedBlackNode getRoot() {
		return root;
	}

	public void insertPerson(Person person) {
		RedBlackNode node = new RedBlackNode(person);

	    if(root == null) {
	       root = node;
	       root.isBlack = true;
	       size++;
	       return;
	       }

	    insertPerson(root, node);
	    size++;
	}
	
	private void insertPerson(RedBlackNode parent, RedBlackNode newNode) {
		 // Can be >= if you adding duplicate keys
        if(newNode != null && parent != null){
        if(newNode.person.dna.isGreaterThan(parent.person.dna)) {
            if(parent.right == null) {
                parent.right = newNode;
                newNode.parent = parent;
                newNode.isLeftChild = false;
                return;
            }
        } else{
        	insertPerson(parent.right, newNode);
        if (parent.left == null) {
            parent.left = newNode;
            newNode.parent = parent;
            newNode.isLeftChild = true;
            return;
        }
        insertPerson(parent.left, newNode);
    }
        checkColor(newNode);
}
	}
	
	public void checkColor(RedBlackNode node){
        if(node == root) return;

        if(node != null && !node.isBlack && node.parent != null && !node.parent.isBlack){
            correctTree(node);
        }

        // After resolving violations, need to check whether introduced any new violations.
        if(node != null && node.parent != null)
        checkColor(node.parent);
    }

    public void correctTree(RedBlackNode node) {
        if(node.parent.isLeftChild){
            // aunt is parent.parent.right 
            if(node.parent.parent.right == null || node.parent.parent.right.isBlack){
                 rotate(node);
            }
            else if(node.parent.parent != null && node.parent.parent.right != null ) {
                // Color flip as the aunt is red
                node.parent.parent.right.isBlack = true;
                node.parent.parent.isBlack = false;
                node.parent.isBlack = true;
                return;
            }
        } else {
        // aunt is grandparent.left
        if(node.parent.parent.left == null || node.parent.parent.left.isBlack) {
             rotate(node);
        }
        else if(node.parent.parent.left != null ) {
            node.parent.parent.left.isBlack = true;
            node.parent.parent.isBlack = false;
            node.parent.isBlack = true;
            return;
        } 
        }
    }

    public void rotate(RedBlackNode node) {
        if(node.isLeftChild){
            if(node.parent.isLeftChild) {
                rightRotate(node.parent.parent);
                node.isBlack = false;
                node.parent.isBlack = true;
                if(node.parent.right != null){
                    node.parent.right.isBlack = false;
                }
                return;
            }
            rightLeftRotate(node.parent.parent);  
            // The node that we start with is the median value, hence set as black.
            node.isBlack = true;
            node.right.isBlack = false;
            node.left.isBlack = false;
            return;   
        } else {
            if(node.parent.isLeftChild) {
                leftRightRotate(node.parent.parent);
                node.isBlack = true;
                node.left.isBlack = false;
                node.right.isBlack = false;
                return;
            }
            leftRotate(node.parent.parent);
        }
    }

    public void leftRotate(RedBlackNode node){
    	RedBlackNode temp = node.right;
        node.right = temp.left;

        if(node.right != null) {
            node.right.parent = node;
            node.right.isLeftChild = false;
        }
        if (node.parent == null){
            // We are at the root node
            root = temp;
            temp.parent = null;
        } else {
            temp.parent = node.parent;
            if(node.isLeftChild){
                temp.isLeftChild = true;
                temp.parent.left = temp;
            } else {
                temp.isLeftChild = false;
                temp.parent.right = temp;
            }
        }
        temp.left = node;
        node.isLeftChild = true;
        node.parent = temp;
    }

    public void rightRotate(RedBlackNode node) {
    	RedBlackNode temp = node.left;
        node.left = temp.right;
        if(node.left != null){
            node.left.parent = node;
            node.left.isLeftChild = true;
        }
        if(node.parent == null){
            root = temp;
            temp.parent = null;
        } else {
            temp.parent = node.parent;
            if(node.isLeftChild){
                temp.parent.left = temp;
                temp.isLeftChild = true;
            } else {
                temp.parent.right = temp;
                temp.isLeftChild = false;
            }
        }
        temp.right = node;
        node.parent = temp;
    }

    public void leftRightRotate(RedBlackNode node) {
        leftRotate(node.left);
        rightRotate(node);
    }

    public void rightLeftRotate(RedBlackNode node) {
        rightRotate(node.right);
        leftRotate(node);
    }

	public void insert(Person[] persons) {		
		for(Person person : persons) {
			insertPerson(person);
		}	
	}

	public void insertFamily(Person person) {
		insertPerson(person);		
	}

	public Person lookup(String gene) {
		// TODO Auto-generated method stub
		return null;
	}

	public int getHeight() {
		 if(root == null) return 0;
        return height(root) - 1;
	}
	
	private int height(RedBlackNode node){
        if(node == null) return 0;
        int leftHeight = height(node.left) + 1;
        int rightHeight = height(node.right) + 1;

        if(leftHeight > rightHeight) return leftHeight;
        return rightHeight;
    }

	@Override
	public Person lookup(DNA dna) {
		// TODO Auto-generated method stub
		return null;
	}


	RedBlackNode root;
    int size;
}