package bTree;


/**
 * 
 * key-value pairs to be stored in the B-tree node
 */
public class Entry {
	private Comparable<?> key; // the key used to sort data, must implement Comparable interface
	private Object val;
	private BTreeNode next; // helper field to iterate over array entries
    public Entry(Comparable<?> key, Object val, BTreeNode next) {
        this.key  = key;
        this.val  = val; // only for external nodes
        this.next = next; // only for internal nodes
    }
	public Object getVal() {
		return val;
	}
	public void setVal(Object val) {
		this.val = val;
	}
	public Comparable<?> getKey() {
		return key;
	}
	
	public void setKey(Comparable<?> key) {
		this.key = key;
	}

	public BTreeNode getNext() {
		return next;
	}
	public void setNext(BTreeNode next) {
		this.next = next;	
	} 
}