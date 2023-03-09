package person;

import java.time.LocalDate;
import java.util.ArrayList;

public class Person {
	public String name;
	public String lastName;
	public boolean isMarried;
	public Person Spouse;
	public LocalDate birthday;
	public String birthplace;
	public String nationality;
	public Gender gender;
	public String dna;
	
	ArrayList<Person> children;
	ArrayList<Person> parents;
	
	/**
	 * lookup a family, a family is defined by three generations, 
	 * parent generation, current generation, children generation
	 * @param person
	 * @return a family tree
	 */
	public Person getFamily(Person person) {
		return this.parents.get(0);
	}
}

enum Gender{
	MALE,
	FEMALE,
	OTHER
}