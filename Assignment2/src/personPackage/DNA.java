package personPackage;

public class DNA {
	private String gene;
	
	public DNA(String gene) {
		this.gene = gene;
	}
	
	public String get() {
		return gene;
	}
	
	public boolean isGreaterThan(DNA dna2) {
		if (this.gene.compareToIgnoreCase(dna2.gene) > 0) 
			return true;
		else
			return false;
	}
	
	public boolean isEqualTo(DNA dna2) {
		return this.gene.equalsIgnoreCase(dna2.gene);
	}
	
	public static void main(String[] args) {
		// unit test
		DNA dna1 = new DNA("ABcD");
		DNA dna2 = new DNA("ABcD");
		System.out.println(dna1.isEqualTo(dna2));
	}
}
