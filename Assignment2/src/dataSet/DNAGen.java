package dataSet;

import java.util.Random;

public class DNAGen {
	private static final Character[] ACGT = {'A','C','G','T'};
	private static final int length = 22;

	/**
	 * Do not allow creating instances
	 */
	private DNAGen() {
	}

	/**
	 * 
	 * @return a random dna
	 */
	static public String get() {
		String dna = "";
		Random random = new Random();
		for(int i = 0; i < length; ++i) {
			dna += ACGT[random.nextInt(ACGT.length)];
		}
		return dna;
	}
}
