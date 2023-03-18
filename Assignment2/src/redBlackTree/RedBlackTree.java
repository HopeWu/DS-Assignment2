package redBlackTree;

import personPackage.Person;
import binarySearchTree.BSTNode;
import binarySearchTree.BinarySearchTree;
import dataSet.DataScaleTooSmallException;
import dataSet.Dataset;
import personPackage.DNA;
import tree.Tree;

/**
 * 
 * @author Prateek Dash
 * 
 * Properties of Red black tree.
 * 
 * 1. Every node is either red or black.
 * 2. The root is black.
 * 3. Every leaf (NIL) is black.
 * 4. If a node is red, then both its children are black.
 * 5. For each node, all simple paths from the node to descendant leaves contain the
 *    same number of black nodes.
 *    
 *   Red Black Tree is a special case of binary search tree  
 *
 */

public class RedBlackTree extends Tree {
	
	public static void main(String[] args) {
		Dataset ds = null;
		try {
			ds = new Dataset(3000);
			
		} catch (DataScaleTooSmallException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Person[] people = ds.getData();
		BinarySearchTree bst = new BinarySearchTree();
		RedBlackTree rdt = new RedBlackTree();
		bst.buildTree(people);
		rdt.buildTree(people);
		System.out.println(bst.getHeight());
		System.out.println(rdt.getHeight());
		System.out.println(rdt._getHeight(rdt.root));
	}
	
	RedBlackNode root;
    
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
	       return;
	    }
	    
	    RedBlackNode current = root;
        RedBlackNode parent = null;
        
        while (current != null) {
        	parent = current;
            if (current.person.dna.isGreaterThan(person.dna)) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        
	    insertPerson(parent, node);
	}
	
	private void insertPerson(RedBlackNode parent, RedBlackNode newNode) {	
		if (newNode == root) {
	        return;
	    }

	    if (parent == null) {
	        root = newNode;
	        root.isBlack = true;
	        return;
	    }

	    // Can be >= if you're adding duplicate keys
	    if (newNode.person.dna.isGreaterThan(parent.person.dna)) {
	        parent.right = newNode;
	        newNode.isLeftChild = false;
	    } else {
	        parent.left = newNode;
	        newNode.isLeftChild = true;
	    }

	    newNode.parent = parent;
	    correctTree(newNode);
	}
	
	public void checkColor(RedBlackNode node){
		if (node == null) {
            System.out.println("Node is null");
            return;
        }
		
		if(node == root) return;
    
        if(node != null && !node.isBlack && node.parent != null && !node.parent.isBlack){
            correctTree(node);
            // After resolving violations, need to check whether introduced any new violations.
            checkColor(node.parent);
        }      
    }

    public void correctTree(RedBlackNode node) {
    	   if (node == null || node.parent == null) {
    	        if (node != null) {
    	            node.isBlack = true;
    	        }
    	        return;
    	    }

    	    if (node.parent.isBlack) {
    	        return;
    	    }

    	    RedBlackNode grandparent = node.parent.parent;
    	    if (grandparent == null) {
    	        return;
    	    }

    	    RedBlackNode uncle = grandparent.left == node.parent ? grandparent.right : grandparent.left;

    	    if (uncle != null && !uncle.isBlack) {
    	        node.parent.isBlack = true;
    	        uncle.isBlack = true;
    	        grandparent.isBlack = false;
    	        correctTree(grandparent);
    	    } else {
    	        boolean nodeIsLeftChild = node.parent != null && node.parent.isLeftChild;
    	        boolean parentIsLeftChild = grandparent != null && grandparent.isLeftChild;

    	        if (nodeIsLeftChild == parentIsLeftChild) {
    	            if (node.parent != null) {
    	                rotate(node.parent);
    	            }
    	        } else {
    	            if (node != null) {
    	                rotate(node);
    	               
    	            }
    	        }

    	        if (node.parent == null) {
        	        return;
        	    }
    	        else{
    	        	if(grandparent!=null) {
    	        	node.parent.isBlack = true;
        	        grandparent.isBlack = false;
    	        	}
    	        }
    	        
    	    }
    }

    public void rotate(RedBlackNode node) {
    	if (node == null || node.parent == null) {
            return;
        }

        boolean nodeIsLeftChild = node.parent != null && node.parent.isLeftChild;
        boolean parentIsLeftChild = node.parent.parent != null && node.parent.parent.isLeftChild;

        if (nodeIsLeftChild == parentIsLeftChild) {
            if (nodeIsLeftChild) {
                rightRotate(node.parent.parent);
            } else {
                leftRotate(node.parent.parent);
            }
        } else {
            if (nodeIsLeftChild) {
                leftRotate(node.parent);
                rightRotate(node.parent);
            } else {
                rightRotate(node.parent);
                leftRotate(node.parent);
            }
        }
     }
  

    public void leftRotate(RedBlackNode node){
    	if (node == null) {
            return;
        }
        
        RedBlackNode rightChild = node.right;
        if (rightChild == null) {
            return;
        }

        node.right = rightChild.left;
        if (rightChild.left != null) {
            rightChild.left.parent = node;
            rightChild.left.isLeftChild = false;
        }

        rightChild.parent = node.parent;

        if (node.parent == null) {
            root = rightChild;
        } else if (node.isLeftChild) {
            node.parent.left = rightChild;
            rightChild.isLeftChild = true;
        } else {
            node.parent.right = rightChild;
            rightChild.isLeftChild = false;
        }

        rightChild.left = node;
        node.parent = rightChild;
        node.isLeftChild = true;
    }

    public void rightRotate(RedBlackNode node) {
    	if (node == null) {
            return;
        }
        
        RedBlackNode leftChild = node.left;
        if (leftChild == null) {
            return;
        }

        node.left = leftChild.right;
        if (leftChild.right != null) {
            leftChild.right.parent = node;
            leftChild.right.isLeftChild = true;
        }

        leftChild.parent = node.parent;

        if (node.parent == null) {
            root = leftChild;
        } else if (node.isLeftChild) {
            node.parent.left = leftChild;
            leftChild.isLeftChild = true;
        } else {
            node.parent.right = leftChild;
            leftChild.isLeftChild = false;
        }

        leftChild.right = node;
        node.parent = leftChild;
        node.isLeftChild = false;    }

    public void leftRightRotate(RedBlackNode node) {
    	if(node == null || node.left == null) return;
        leftRotate(node.left);
        rightRotate(node);
    }

    public void rightLeftRotate(RedBlackNode node) {
    	if(node == null || node.right == null) return;
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
		return height(root);
	}
	
	private int height(RedBlackNode node){
		if (node == null) {
	        return 0;
	    }

	    int leftHeight = height(node.left);
	    int rightHeight = height(node.right);

	    return Math.max(leftHeight, rightHeight) + (node.isBlack ? 1 : 0);
    }

	@Override
	public Person lookup(DNA dna) {
		RedBlackNode searchNode = search(root, dna.get());
		return (searchNode == null) ? null : searchNode.person;
	}
	
	/**
     * Searches for a node in the Red Black tree with the given DNA using a recursive
     * depth-first search. Returns the node with the given DNA, or null if no
     * such node exists in the tree.
     *
     * @param node The root node of the subtree to search.
     * @param gene The DNA of the person to search for.
     * @return The node containing the person with the given DNA, or null if no
     * such node exists in the tree.
     */
    private RedBlackNode search(RedBlackNode node, String gene) {
        if (node == null|| node.person.dna.equals(gene)) {
            return node;
        }

        int cmp = gene.compareTo(node.person.dna.get());
        if (cmp < 0) {
            return search(node.left, gene);
        } else if (cmp > 0) {
            return search(node.right, gene);
        } else {
            return node;
        }
    }


    public int _getHeight(RedBlackNode root) {
		// null node does add to the height
		if (root == null)
			return 0;
		// the height of this node is the higher child's height plus 1
		return Math.max(_getHeight(root.left), _getHeight(root.right)) + 1;
	}
	
}