## Time & Space complexity analysis in class `BinarySearchTree`
#### 1. `public Node buildTree(Person[] people)`
```
	/**
	* Build a tree from a family tree @personData, the root of the family tree.
	* nlogn time
	*/
	public Node buildTree(Person[] people) {
			insert(people);
			return this.root;
	}
```

** O(nlogn) in time complexity **

This is to insert n `Person` objects into the tree. Since the data set is random, on average, the height of the tree will be logn. Each `insert` takes logn time.(More discussion later with `insertPerson`) Thus, it takes O(nlogn) time in sum. n denotes the length of the `people` array.

** O(n) in memory complexity **

Each person will create a `BSTNode`. 

```
	public class BSTNode extends Node{
		public BSTNode left;
		public BSTNode right;
		public Person data;
	
		public BSTNode(Person person) {
			this.data = person;
	}
```
This method create n `BSTNode` objects. An instance of this will take three references' space(each cost 8 bytes in 64-bit system and 4 bytes in 32-bit system) and some header bytes(12 bytes in a modern 64-bit JDK). The method doesn't take memory.  At last there is a parameter variable to hold the reference of `people`, which add 8 more bytes. In all, it's 44 bytes for each and 36*n bytes for a n-element array. n denotes the length of the `people` array.

#### 2. `public Node getRoot()`
```
	/**
	 * return the root of the tree
	 */
	public Node getRoot() {
		return this.root;
	}
```
Just return a member variable, so

** O(1) in time complexity **

** O(1) byte in memory complexity **

#### 3. `public void insertPerson(Person person)`
```
	/**
	 * insert only one person
	 */
	public void insertPerson(Person person) {
		if (this.root == null) {
			this.root = new BSTNode(person);
		}
		this._insertPerson(this.root, person);
		isHeightModified = true;
	}
```
** O(logn) in time complexity **

n describes how many `person` this tree currently holds. It takes the height of the tree to insert one person. In the worst case scenario where all the nodes are planted in one side, it takes n time. In the best case scenario where the current tree is completely balanced, it takes nlogn time. On average, it takes O(nlogn).

** O(1) byte in memory complexity **

Only one new object is to be created.

#### 4. `private void _insertPerson(BSTNode root, Person person)`
```
	/**
	 * real recursive insert
	 * @param root, under which root to insert
	 * @param person, which person to insert
	 */
	private void _insertPerson(BSTNode root, Person person) {

		if (person.dna.isGreaterThan(root.data.dna)) {
			// search right child
			if (root.right != null)
				// go further into the right and look for a insertion spot
				this._insertPerson(root.right, person);
			else {
				// insert here
				root.right = new BSTNode(person);
			}
		} else {
			// search left child
			if (root.left != null)
				// go further into the left and look for a insertion spot
				this._insertPerson(root.left, person);
			else {
				// insert here
				root.left = new BSTNode(person);
			}
		}
	}
```

** O(logn) in time complexity **

The time complexity analysis is the same with the last one `insertPerson`.

** O(logn) in memory complexity **

This recursive function takes the depth of the current node time. The worst case scenario is when all the nodes are constructed on one side, which forms a line and takes n memory. The best case scenario is when the tree is completely balanced, under which it takes logn memory. Since the data set is random, on average, the height of the tree will be logn.


#### 5. `public void insert(Person[] persons)`
```
	/**
	 * insert a bunch of people
	 */
	public void insert(Person[] persons) {
		for (int i = 0; i < persons.length; ++i) {
			this.insertPerson(persons[i]);
		}
	}
```
** O(nlogn) in time complexity **

The analysis is similar to 1. `buildTree`. Both insert an array of object with each taking logn time on average. 

** O(n) in memory complexity **
The same with 1. `buildTree`.

#### 6. `public void insertFamily(Person person)`

```
	/**
	 * insert the whole family, a family is defined by three generations, parent
	 * generation, current generation, children generation
	 */
	public void insertFamily(Person person) {
		if (person == null)
			return;
		insertPerson(person);
		// insert their family if there is any in the data set
		if (person.getParents() != null)
			for (Person parent : person.getParents()) {
				insertPerson(parent);
			}
		// insert their children if there is any in the data set
		if (person.getChildren() != null)
			for (Person child : person.getChildren()) {
				insertPerson(child);
			}
	}
```
** O(1) in time complexity **

Time complexity depends on how many people we are inserting here. The number is between 0 and 5 in our case, i.e., constant.

** O(1) in memory complexity **

Each insert takes a piece memory of `Person`, so it's constant.

#### 7. `public Person _lookup(BSTNode root, DNA dna)`
```
	/**
	 * real recursive lookup
	 * @param root, search this root
	 * @param dna, search by this dna
	 * @return, return the Person if found
	 */
	public Person _lookup(BSTNode root, DNA dna) {
		if (root == null)
			// didn't find it
			return null;
		if (root.data.dna.isEqualTo(dna)) {
			// found it!
			return root.data;
		}
		if (dna.isGreaterThan(root.data.dna)) {
			// search its right child
			return this._lookup(root.right, dna);
		} else {
			// search its left child
			return this._lookup(root.left, dna);
		}
	}
```
** O(logn) in time complexity **

Each lookup is similar to each insert(previously discussed) because they have to find the right place first.

** O(1) in memory complexity **

Lookup doesn't incur additional memory except for the parameters, so it's constant.

#### 8. `public int _getHeight(BSTNode root)`
```
	/**
	 * real recursive getHeight function
	 * @param root, the height of this root
	 * @return, the height of this root
	 */
	public int _getHeight(BSTNode root) {
		// null node does add to the height
		if (root == null)
			return 0;
		// the height of this node is the higher child's height plus 1
		return Math.max(_getHeight(root.left), _getHeight(root.right)) + 1;
	}
```
** ø(n) in time complexity **

To get the height of the tree(the depth of the deepest node), it has to touch every node in the tree. It's a tight bound, theta.

** O(logn) in memory complexity **

Since this is a recursive function, the stack memory is the main source, which depend on the depth of the node.
