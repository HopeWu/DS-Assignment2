package personPackage;

import java.time.LocalDate;
import java.util.ArrayList;

public class Person {
	
	public String name;
	public Person spouse;
	public LocalDate birthday;
	public String birthplace;
	public String nationality;
	public String gender;
	public String dna;
	
	ArrayList<Person> children;
	ArrayList<Person> parents;
	
	public Person() {
		super();
	}

	public Person(String birthplace,
			String nationality, String dna) {
		super();
		this.birthplace = birthplace;
		this.nationality = nationality;
		this.dna = dna;
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
		String firstChildBirthday = this.children != null ? this.children.get(0).birthday.toString() : null;
		String parentBirthday = this.parents != null ? this.parents.get(0).birthday.toString() : null;
		return "Person [name=" + name + ", spouse=" + spouseName + ", birthday=" + birthday + ", birthplace=" + birthplace
				+ ", nationality=" + nationality + ", gender=" + gender + ", dna=" + dna
				+ ", 1st child's birthday=" + firstChildBirthday
				+ ", parent's brithday=" + parentBirthday + "]";
	}
}