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
		int size = 10000;
		long start0 = System.currentTimeMillis();
		Dataset dataset = new Dataset(size);
		Person[] people = dataset.getData();
		long end0 = System.currentTimeMillis();
		long elapsedTime0 = end0 - start0;
		System.out.println(String.format("Generating data of size %d takes %d milliseconds.", size, elapsedTime0));
		DataGenerator.saveData(people, people.length);
	}
	
	public static void saveData(Person[] people, int size) {		
		String path = String.format("Assignment2/data/data_%d.txt", size);
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
