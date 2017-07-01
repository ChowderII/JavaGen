package gens;

import java.util.Random;

/*
 * The Chromosome object represents a solution, its genes represent a possible solution
 * while the fitness represent how good is that solution
*/
public class Chromosome {

	private int[] Genome;
	private int Size;
	private int NumberGenes;
	private int SizeGenes;
	private int Fitness;
	
	/*
	 * The fitness gets calculated as soon as the chromosome is created.
	 */
	public Chromosome (int NumberGenes, int SizeGenes){ // initialise the chromosome with a random genome, used in creating the pool
		this.NumberGenes = NumberGenes;
		this.SizeGenes = SizeGenes;
		this.Size = NumberGenes*SizeGenes;
		
		int[] temp = new int[this.Size];
		
		Random r = new Random();
		
		for (int i = 0; i < this.Size; i++){ // random values of 1 or 0
			temp[i] = r.nextInt(2);
		}
		
		this.Genome = temp;
		this.Fitness = setFitness();
	}
	
	public Chromosome (int[] Genome, int NumberGenes){ // this chromosome is the product of a genetic operator, we already know the genome
		this.Genome = Genome;
		this.Size = Genome.length;
		this.NumberGenes = NumberGenes;
		this.SizeGenes = Genome.length/NumberGenes;
		this.Fitness = setFitness();
	}
	
	/*
	 * IMPORTANT : change this method to accommodate your specific problem.
	 * This method is used to calculate the fitness of the solution
	 * Works best if perfect fitness is 0
	 * lower is better
	 */
	private int setFitness() { // sets the fitness of the newly created chromosome, can only be called at the creation of the chromosome
		int[] Genes = getReadableGenome(this.Genome);
		int fitness = Genes.length;

		
		int[] sum = new int[Genes.length];
		int[] sub = new int[Genes.length];
		
		
		for(int i = 0; i < Genes.length; i++){	
			sum[i] = i + Genes[i];
			sub[i] =  Genes[i] - i;
		}
		
		for(int i = 0; i<Genes.length; i++){
			for(int j = 0; j<Genes.length; ++j){
				if(i == j){j++;}
				if(j == Genes.length){break;}
				if(Genes[j] == Genes[i]){fitness++;break;} // testing for duplicate numbers
				if(Genes[j] >= Genes.length){fitness++;break;} // testing for numbers too high
				
				if(sum[i] == sum[j]){fitness--;}
				if(sub[i] == sub[j]){fitness--;}
			}
		}
		
		return fitness;
	}
	
	// converts the stream of bits into a human readable array of integers, can also be useful for setFitness
	public int[] getReadableGenome(int[] Genome){
		int[] Genes = new int[8];
		int indI = 0;
		int expo = this.SizeGenes-1;
		for (int i = 0; i < this.Size; i++){
			Genes[indI] += (int) Genome[i] * (Math.pow(2,expo));
			expo--;
			if(expo == -1){
				expo = this.SizeGenes-1;
				indI++;
			}
		}
		return Genes;
	}
	
	public void printResult() {
		int[] g = getReadableGenome(this.Genome);
		
		System.out.print("[");
		for (int i = 0; i < this.NumberGenes; i++){
			if(i==this.NumberGenes-1){
				System.out.print(g[i] + "]");
				break;}
			System.out.print(g[i] + " ");
		}
	}
	
	public int[] getGenome(){
		return this.Genome;
	}
	
	public int getFitness(){
		return this.Fitness;
	}
	
	public int Size(){
		return this.Size;
	}
}