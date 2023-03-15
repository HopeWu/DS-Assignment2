package dataSet;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

/**
 * To provide real name and gender services.
 * 
 * @author haopengwu
 *
 */
public class GenderNames {

	GenderNames() {
		loadFile();
	}

	public int size() {
		return genderNames.size();
	}

	// <name, gender>
	private static HashMap<String, String> genderNames;
	private static Random random = new Random();

	private static void loadFile() {
		if (genderNames != null)
			return;
		genderNames = new HashMap<String, String>();
		BufferedReader in = null;
		try {
			String dir = System.getProperty("user.dir");
			in = new BufferedReader(new FileReader("Assignment2/data/names_dataset.csv"));
			
			String line;
			String[] row;
			while ((line = in.readLine()) != null) {
				row = line.split(",");
				genderNames.put(row[0].trim(), row[1].trim());
			}
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 
	 * @return a random male name and their gender
	 */
	public Name getAMaleName() {
		int index = random.nextInt(size());
		while (!genderNames.get(genderNames.keySet().toArray()[index]).equals("m")) {
			index = random.nextInt(size());
		}
		String name = (String) genderNames.keySet().toArray()[index];
		String gender = genderNames.get(name);

		return new Name(name, gender);
	}

	/**
	 * 
	 * @return a random female name and their gender
	 */
	public Name getAFemaleName() {
		int index = random.nextInt(size());
		while (!genderNames.get(genderNames.keySet().toArray()[index]).equals("f")) {
			index = random.nextInt(size());
		}
		String name = (String) genderNames.keySet().toArray()[index];
		String gender = genderNames.get(name);

		return new Name(name, gender);
	}

	/**
	 * randomly get a name no matter if it's of male or female
	 * 
	 * @return
	 */
	public Name getAName() {
		int index = random.nextInt(size());

		String name = (String) genderNames.keySet().toArray()[index];
		String gender = genderNames.get(name);

		return new Name(name, gender);
	}
}
