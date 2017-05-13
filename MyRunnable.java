package gens;

public class MyRunnable implements Runnable{
	
	private int id;
	private Chromosome c;
	private Pool p;
	private final boolean duplicate;
	private final int size;
	private final int min;
	private final int max;
	
	public MyRunnable(int id, Chromosome c, Pool p, boolean duplicate, int size, int min, int max){
		this.id = id;
		this.c = c;
		this.p = p;
		this.duplicate = duplicate;
		this.size = size;
		this.min = min;
		this.max = max;
	}
	
	public void run(){
		int currentFitness = this.p.getChromosome(id).getFitness(); // get fitness for existing Chromosome
		
		Chromosome temp = new Chromosome(this.duplicate, this.size, this.min, this.max); // create new child at random
		
		if (currentFitness <= temp.getFitness()) { // replaces the new old Chromosome with the child
			this.p.getPool()[this.id] = temp;
		}
	}

}
