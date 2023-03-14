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
		if (this.gene.compareTo(dna2.gene) > 0) 
			return true;
		else
			return false;
	}
}
