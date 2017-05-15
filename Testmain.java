package gens;

public class Testmain {
	public static void main(String[] args){
		
		int min = -10;
		int max = 10;
		int range = (max - min) + 1;
		int size = 5;
		boolean duplicate = false;
		
		int[] Genes;
		
		Chromosome c = new Chromosome(false, 6, -5, 0);
		
		Genes = c.getGenes();
		
		for(int i = 0; i < Genes.length; i++){
			System.out.println(Genes[i]);
		}
	}
}
