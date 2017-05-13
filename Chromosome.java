package gens;
import java.util.ArrayList;

/*
 * The Chromosome object represents a solution, its genes represent a possible solution
 * while the fitness represent how good is that solution
*/
public class Chromosome {
	
	private int[] Genes;
	private int Size;
	private int Fitness;
	
	/*
	 * The fitness gets calculated as soon as the chromosome is created.
	 */
	public Chromosome (int[] genes){
		this.Genes = genes;
		this.Size = genes.length;
		this.Fitness = setFitness();
	}
	
	public Chromosome (boolean duplicate, int size, int min, int max) {
		int[] genes = new int[size];
		if (duplicate == true) {
			for (int i = 0; i < size; i++) {
				genes[i] = (int)(Math.random() * max) + min;
			}
		}
		/*
		 * To allow the randomness of non repetitive numbers from a range MIN to MAX
		 * create ArrayList containing MIN to MAX in order, then the algorithm picks
		 * 
		 * Manually tested the case where MIN is higher than 0 
		 * Manually tested the case where MIN is 0
		 * Manually tested the case where MIN is lower than 0
		 * 
		 * As long as the correct size is specified for the formula : MAX - MIN == SIZE - 1; it'll work.
		 */
		else {
			if (max - min != size - 1){
				System.out.println("The size you entered is invalid for the MAX and MIN you provide, please reboot with proper values. Ex : MAX - MIN == size - 1");
				System.exit(0);
			}
			else {
			ArrayList<Integer> list = new ArrayList<Integer>();
			for (int i = min; i <= max; i++) {
				list.add(new Integer(i));
			}
			for (int i = 0; i <= (max-min); i++) {
				int index = (int) (Math.random() * list.size()) + min;
				index -= min;
				genes[i] = list.get(index);
				list.remove(index);
			}
		}
		this.Genes = genes;
		this.Size = size;
		this.Fitness = setFitness();
		}
		
	}
	
	/*
	 * IMPORTANT : change this method to accommodate your specific problem.
	 * This method is used to calculate the fitness of the solution
	 * Works best if highest fitness is better
	 */
	private int setFitness(){
		int[] sum = new int[this.Genes.length];
		int[] sub = new int[this.Genes.length];
		
		int fitness = 0;
		
		for(int i = 0; i < this.Genes.length; i++){	
			sum[i] = i + this.Genes[i];
			sub[i] =  this.Genes[i] - i;
		}
		
		for(int i = 0; i<this.Genes.length; i++){
			for(int j = 0; j<this.Genes.length; ++j){
				if(i == j){j++;}
				if(j == this.Genes.length){break;}
				
				if(sum[i] == sum[j]){fitness--;}
				if(sub[i] == sub[j]){fitness--;}
			}
		}
		
		return fitness/2;
	}
	
	public int[] getGenes(){
		return this.Genes;
	}
	
	public int getFitness(){
		return this.Fitness;
	}
	
	public int Size(){
		return this.Size;
	}
}