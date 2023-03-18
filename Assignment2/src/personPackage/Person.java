package personPackage;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Person implements Serializable {
	
	private static final long serialVersionUID = -473474148924379651L;
	public String name;
	public Person spouse;
	public LocalDate birthday;
	public String birthplace;
	public String nationality;
	public String gender;
	public DNA dna;
	
	public ArrayList<Person> children;
	public ArrayList<Person> parents;
	
	public ArrayList<Person> getChildren() {
		return children;
	}
	public ArrayList<Person> getParents() {
		return parents;
	}

	
	
	public Person() {
		super();
	}

	public Person(String birthplace,
			String nationality, String dna) {
		super();
		this.birthplace = birthplace;
		this.nationality = nationality;
		this.dna = new DNA(dna);
	}
	
	/**
	 * lookup a family, a family is defined by three generations, 
	 * parent generation, current generation, children generation
	 * @param person
	 * @return a family tree
	 */
	public Person getFamily(Person person) {
		return this.parents.get(0);
	}

	public void addChildren(ArrayList<Person> children) {
		if (this.children == null) this.children = new ArrayList<Person>();
		for (Person child: children) {
			this.children.add(child);
		}
	}

	public void addParent(Person person) {
		if (this.parents == null) this.parents = new ArrayList<Person>();
		this.parents.add(person);
	}

	@Override
	public String toString() {
		String spouseName = this.spouse != null ? this.spouse.name : null;
		String firstChildBirthday = (this.children != null && this.children.get(0).birthday != null) ? this.children.get(0).birthday.toString() : null;
		String parentBirthday = this.parents != null ? this.parents.get(0).birthday.toString() : null;
		return "Person [name=" + name + ", spouse=" + spouseName + ", birthday=" + birthday + ", birthplace=" + birthplace
				+ ", nationality=" + nationality + ", gender=" + gender + ", dna=" + dna
				+ ", 1st child's birthday=" + firstChildBirthday
				+ ", parent's brithday=" + parentBirthday + "]";
	}
}