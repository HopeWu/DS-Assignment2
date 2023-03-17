package dataSet;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import personPackage.Person;

/**
 * A class to generate data before doing experiments.
 * @author Yan
 *
 */
public class DataGenerator {
	public static void main(String[] args) {
		int size = 100000;
		generateData(size);
	}

	/**
	 * Generates data of specified size
	 * @param size the size of the data to be generated
	 */
	private static void generateData(int size) {		
		long start0 = System.currentTimeMillis();		
		try {
			Dataset dataset = new Dataset(size);
			Person[] people = dataset.getData();
			long end0 = System.currentTimeMillis();
			long elapsedTime0 = end0 - start0;
			System.out.println(String.format("Generating data of size %d takes %d milliseconds.", size, elapsedTime0));
			DataGenerator.saveData(people, people.length);
		} catch (DataScaleTooSmallException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Saves data to files
	 * @param people an array of Person Objects to be saved
	 * @param size the length of the array
	 */
	private static void saveData(Person[] people, int size) {		
		String path = String.format("data/data_%d.txt", size);
		try {
			FileOutputStream fo = new FileOutputStream(path);
		    ObjectOutputStream oo = new ObjectOutputStream(fo);
		    for (Person person : people) {
		    	oo.writeObject(person);		    	
		    }	    
		    oo.close();
		    fo.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
