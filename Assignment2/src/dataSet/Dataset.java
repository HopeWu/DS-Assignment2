package dataSet;

import java.util.*;
import java.time.LocalDate;

import personPackage.Person;

public class Dataset {

	// the number of the dataset, i.e. how many people there are in the dataset
	private int population;
	// how likely a man will have children, doesn't reflect the final proportion of
	// fathers
	private double relationRate;
	//
	private Random random;

	// generate an array to hold the data
	Person[] people;

	public static void main(String[] args) {
		/**
		 * For testing
		 */
		Dataset dataset = new Dataset();
		Person[] people = dataset.getData();
		for (int i = 0; i < people.length; ++i) {
			System.out.print(i+" ");
			System.out.println(people[i]);
		}
	}

	public Dataset() {
		super();
		this.population = 100;
		this.relationRate = 1;
		people = new Person[population];
		this.random = new Random();
	}

	public void setRelationRate(double relationRate) {
		// turn relationRate into a percentage
		while (relationRate > 1)
			relationRate /= 10;
		this.relationRate = relationRate;
	}

	private void setPopulation(int scale) {
		this.population = scale;
	}

	/**
	 * The returned data is a bunch of PersonNode, with their blood relationship
	 * built in. They don't necessarily share one single ancestor. In other words,
	 * they are a forest consist of many trees.
	 * 
	 * @return a bunch of PersonNode, with their blood relationship built in.
	 */

	public Person[] getData() {

		/**
		 * Create all the Person objects.
		 */
		int i = 0;
		for (i = 0; i < population; ++i) {
			people[i] = new Person(getABirthplace(), getANationality(), getADNA());
		}

		/**
		 * for naming service
		 */
		GenderNames gendernames = new GenderNames();

		/**
		 * Create the relationships.
		 */
		ArrayList<Person> children;
		LocalDate oldestBirthday;
		Person spouse;
		Person person;
		HashMap<Person, Boolean> hasParents = new HashMap<Person, Boolean>();
		// the first one sixth have no children
		int noChildren = population / 6;
		Name name1, name2;
		for (i = 0; i < population; ++i) {
			// skip the first one sixth who will have no children
			if (i < noChildren)
				continue;
			person = people[i];
			// skip a person who has a spouse because we have assigned their children
			if (person.spouse != null)
				continue;
			// choose a spouse for this parent
			spouse = chooseSpouse(i);
			// no spouse no children
			if (spouse == null) continue;
			// set the marriage relationship
			person.spouse = spouse;
			spouse.spouse = person;
			// choose children for this parent and set their birthdays
			children = getChildrenFor(i, hasParents);
			// if no children, skip
			if (children.isEmpty())
				continue;
			// get the birthday of the oldest from children
			oldestBirthday = maxBirthdayOf(children);
			// set the parent's birthday that is N(23, 6) years earlier than the oldest
			// child. as least 17 years older
			person.birthday = oldestBirthday.minusYears(Math.min((long) random.nextGaussian(23, 6), 18)).minusDays(random.nextLong(365));

			// set the couples' name and gender
			name1 = gendernames.getAMaleName();
			name2 = gendernames.getAFemaleName();
			person.name = name1.getName();
			person.gender = name1.getGender();
			spouse.name = name2.getName();
			spouse.gender = name2.getGender();
			// set the spouse's birthday to N(partner's birthday, 2)
			spouse.birthday = person.birthday.plusYears((long) (random.nextGaussian(0, 2))).minusDays(random.nextLong(365));
			// set the parents' children relationship
			person.addChildren(children);
			spouse.addChildren(children);
			for (Person child : children) {
				child.addParent(person);
				child.addParent(spouse);
				hasParents.put(child, true);
			}
		}

		/**
		 * Fill the names, genders, and birthdays.
		 */
		Name name;
		for (int j = 0; j < people.length; ++j) {
			if (people[j].birthday == null) {
				people[j].birthday = getABirthday();
			}
			if (people[j].name == null) {
				name = gendernames.getAName();
				people[j].name = name.getName();
				people[j].gender = name.getGender();
			}
		}

		return people;
	}

	/**
	 * 
	 * @param i the index of the person the spouse is of
	 * @return a person who has the next index
	 */
	private Person chooseSpouse(int i) {
		if (i >= population)
			return null;
		return this.people[i + 1];
	}

	/**
	 * get age of the oldest children
	 * 
	 * @param children
	 * @return
	 */
	private LocalDate maxBirthdayOf(ArrayList<Person> children) {
		LocalDate oldest = children.get(0).birthday;
		for (Person child : children) {
			if (child.birthday.isBefore(oldest))
				oldest = child.birthday;
		}
		return oldest;
	}

	/**
	 * Randomly get children for the this.people[i] from index (0, i) (exclusive i).
	 * 
	 * @param i,         the current person's index
	 * @param hasParents
	 * @return
	 */
	private ArrayList<Person> getChildrenFor(int i, HashMap<Person, Boolean> hasParents) {
		/**
		 * set the number of children, not necessarily this many
		 */
		int howMany;
		double odds = random.nextGaussian(0, 1);
		if (odds < 0) {
			// half of chance to get two children
			howMany = 2;
		} else if (odds >= 0 || odds < 1) {
			// 34% chance to get 1 child
			howMany = 1;
		} else {
			// 16% chance to get 3 child
			howMany = 3;
		}
		/**
		 * try to get the number of children
		 */
		// save children to return
		ArrayList<Person> children = new ArrayList<Person>();
		// max of tries to get the children
		int maxTries = 5;
		int time = 0;
		int childIndex;
		while (time < maxTries && howMany > 0) {
			childIndex = random.nextInt(i);
			if (hasParents.get(this.people[childIndex]) != null && hasParents.get(this.people[childIndex]) == true) {
				// try to find another one and consume one try
				++time;
				continue;
			} else {
				// Yeah! Got a child without any parent! Don't consume tries.
				howMany -= 1;
				children.add(this.people[childIndex]);
			}
		}

		/**
		 * set their birthdays
		 */

		/**
		 * set their ages first
		 */
		// set the gap, the minimum is 1
		int gap = Math.max((int) random.nextGaussian(2, 2), 1);
		ArrayList<Integer> age = new ArrayList<Integer>();
		// set the age of the first child
		age.add((int) Math.max(1, random.nextGaussian(2, 2)));
		for (int i1 = 1; i1 < children.size(); ++i1) {
			age.add(age.get(i1 - 1) + gap);
			gap = Math.max((int) random.nextGaussian(2, 2), 1);
		}
		/**
		 * set their birthdays
		 */
		LocalDate today = LocalDate.now();
		for (int j = 0; j < children.size(); ++j) {
			children.get(j).birthday = today.minusYears(age.get(j)).minusDays(random.nextLong(365));
		}
		return children;
	}

	/**
	 * Randomly get a DNA.
	 * 
	 * @return
	 */
	private String getADNA() {
		return DNA.get();
	}

	/**
	 * Randomly get a nationality.
	 * 
	 * @return
	 */
	private String getANationality() {
		return "Indian";
	}

	/**
	 * Randomly get a birthplace.
	 * 
	 * @return
	 */
	private String getABirthplace() {
		return "India";
	}

	/**
	 * Randomly get a birthday that is 1~120 years old
	 * 
	 * @return
	 */
	private LocalDate getABirthday() {
		LocalDate today = LocalDate.now();
		return today.minusYears(random.nextInt(120) + 1).minusDays(random.nextLong(365));
	}
}
